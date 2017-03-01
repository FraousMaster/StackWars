import java.io.*;
import java.net.*;
import java.util.*;


public class Client extends Thread{
	private static String IP_ADDRESS;
	private static String name;
	private static byte[] sendData = new byte[16];
	private static byte[] receiveData = new byte[16];
	private DatagramSocket clientSocket;
	private String SendMessage;
	private LobbyMenu menu;
	private String messageReceived;
	private LinkedList<String> players ;
	private boolean inLobby = true;
	private final int PORT = 1203;
	private InetAddress host;
	@SuppressWarnings("unused")
	private ArrayList<Ant> ants;
	private Game game ;
	
	@SuppressWarnings("static-access")
	public Client(String IP, String name, LobbyMenu menu) throws SocketException{
		this.IP_ADDRESS = IP;
		this.name = name;
		this.menu = menu;
		players = new LinkedList<String>();
		clientSocket = new DatagramSocket();	    
	}
	
	 public void run(){
		
			try {
				
				host = InetAddress.getByName(IP_ADDRESS);
				SendMessage = name;
				sendData = SendMessage.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
			 	clientSocket.send(sendPacket);
				//System.out.println("This was sent from clientsocket: " + SendMessage);
		
				while(inLobby){
					DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivethis);
					byte[] data = receivethis.getData();
					messageReceived = new String(data, 0, receivethis.getLength());
					if(players.isEmpty()){
						new Player(messageReceived);
					}
					else if(!( messageReceived.equals("update"))){
						if(!(messageReceived.equals(players.getLast()) || messageReceived.equals(players.getFirst()) || messageReceived.equals("start"))){
							new Player(messageReceived);
						}
					}
					
					SendMessage = "update";
					sendData = SendMessage.getBytes();
					DatagramPacket checkPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
				 	clientSocket.send(checkPacket);
				 	
				 	SendMessage = "started?";
					sendData = SendMessage.getBytes();
					DatagramPacket startPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
				 	clientSocket.send(startPacket);
					//System.out.println("This was sent from clientsocket: " + SendMessage);
					
					int nrOfPlayers = 0;
					for(@SuppressWarnings("unused") String x : players){
						nrOfPlayers++;
					}
					
					if( menu.startPressed()){
						SendMessage = "start";
						sendData = SendMessage.getBytes();
						DatagramPacket GamePacket = new DatagramPacket(sendData, sendData.length, host, PORT);
					 	clientSocket.send(GamePacket);
						//System.out.println(nrOfPlayers);
					}
					
					//System.out.println(messageReceived);
					
					if(messageReceived.equals("start")){
						System.out.println("START PRESSED");
						menu.startGame();
						game =	menu.returnGame();
						inLobby = false;
					}
					
					sleep(250);
						
					
				}} catch (IOException | InterruptedException e) {
		e.printStackTrace();
		}
			System.out.println("left loop");
			System.out.println("Entering game");
			try {
				gameRunning();
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
 }

	 private void gameRunning() throws StreamCorruptedException{
		 try{
			 
			
		    
			 
			 while(true){
				 ants = game.getState().getAnts();
				 System.out.println("IN CLIENT " + ants);
				 
				
				 
				 for(Ant ants : ants ){
					 
					 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					 ObjectOutputStream os = new ObjectOutputStream(outputStream);
					 os.writeObject(ants);
					 byte[] data = outputStream.toByteArray();
					 DatagramPacket packet = new DatagramPacket(data, data.length, host, PORT);
					 clientSocket.send(packet);
				 }
				 
				
				     
					/* SendMessage = "";
					 int xPos = x.getPosX();
					 int yPos = x.getPosY();
					 SendMessage += Integer.toString(xPos);
					 SendMessage += ", ";
					 SendMessage += Integer.toString(yPos);
					 System.out.println("sent message :" + SendMessage); */
					
				
				 
		 //SendMessage = "update game";
		 //sendData = SendMessage.getBytes();
		 //DatagramPacket checkPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
		 //	clientSocket.send(checkPacket);
		// 	System.out.println("sent data :" + SendMessage);
		 
		// DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
		// 	clientSocket.receive(receivethis);
		 //	byte[] data = receivethis.getData();
		// 	messageReceived = new String(data, 0, receivethis.getLength());
		 //	System.out.println("received data :" + messageReceived);
		 
		 sleep(5000);
		 //receive ant positions, update ants
		 
		 
		 		//ants.removeAll(ants);
			 
			 }
		 
		 
		 }catch(InterruptedException | IOException e){
			 e.printStackTrace();
		 }
	
	 }
 
	public class Player {
		String name;
		
		public Player(String player){
			this.name = player;
		    players.add(name);
		    System.out.println("entered player : " + name);
		    System.out.println("linkedlist in client : " + players);
		    run();
		    }
		
		public void run() 
		{
			menu.setPlayers(name);


		}
		
	}
}