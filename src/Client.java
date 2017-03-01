import java.io.*;
import java.net.*;
import java.util.*;


public class Client extends Thread{
	private static String IP_ADDRESS;
	private static String name;
	private static byte[] sendData = new byte[1000];
	private static byte[] receiveData = new byte[1000];
	private DatagramSocket clientSocket;
	private String SendMessage;
	private LobbyMenu menu;
	private String messageReceived;
	private LinkedList<String> players ;
	private boolean inLobby = true;
	private final int PORT = 1203;
	private InetAddress host;
	private ArrayList<Ant> ants;
	private Game game ;
	private GameState state;
	
	@SuppressWarnings("static-access")
	public Client(String IP, String name, LobbyMenu menu) throws SocketException{
		this.IP_ADDRESS = IP;
		this.name = name;
		this.menu = menu;
		players = new LinkedList<String>();
		clientSocket = new DatagramSocket();	 
		ants = new ArrayList<Ant>();
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
						state = menu.returnState();
						inLobby = false;
					}
					
					sleep(250);
						
					
				}} catch (IOException | InterruptedException e) {
		e.printStackTrace();
		}
			System.out.println("left loop");
			System.out.println("Entering game");
			gameRunning();
			
 }

	 private void gameRunning() {
		 
		 try{
			 
			 while(true){
				 sleep(50);
				
				 	SendMessage = "update ants";
				 	sendData = SendMessage.getBytes();
				 	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
				 	clientSocket.send(sendPacket);
				 
					DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivethis);
					byte[] data = receivethis.getData();
					messageReceived = new String(data, 0, receivethis.getLength());
					//System.out.println("client got : " + messageReceived);
					
					if(messageReceived.equals("No ants") || messageReceived.equals("Give ants")){
						
						for(Ant x : state.getAnts()){
						 	SendMessage = x.toString();
							sendData = SendMessage.getBytes();
						 	DatagramPacket sendMe = new DatagramPacket(sendData, sendData.length, host, PORT);
						 	clientSocket.send(sendMe);
						 	//System.out.println("sent from client : " + SendMessage );
						 	}
						
					}
					else if(!(messageReceived.equals(players.getLast()) || messageReceived.equals("start"))) {
						if(ants.isEmpty()){
							 ants.add(new Ant(messageReceived));
							 state.updateAllAnts(ants); 
						}
						else if (!(check(messageReceived))){
							 ants.add(new Ant(messageReceived));
							 state.updateAllAnts(ants);
						}
					}
				
				// System.out.println("IN CLIENT " + ants);
			
		 }
		 
		 
		 }catch(IOException | InterruptedException e){
			 e.printStackTrace();
		 }
	
	 }
 
	 private boolean check(String s){
		 boolean exists = false;
		 for(Ant x : ants){
			 if( x.toString().equals(s)){
				 exists = true;
			 }	
		 }
		 return exists;
		 
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