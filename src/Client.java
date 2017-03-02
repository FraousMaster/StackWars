import java.io.*;
import java.net.*;
import java.util.*;


public class Client extends Thread{
	private static String IP_ADDRESS;
	private static String name;
	private static byte[] sendData = new byte[1000];
	private static byte[] receiveData = new byte[1000];
	//private DatagramSocket clientSocket = null;
	private DatagramSocket gameSocket = null ;
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
		gameSocket = new DatagramSocket();	 
		ants = new ArrayList<Ant>();
		try {
			host = InetAddress.getByName(IP_ADDRESS);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public void run(){
		
			try {
				
				SendMessage = name;
				sendData = SendMessage.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
				gameSocket.send(sendPacket);
				//System.out.println("This was sent from clientsocket: " + SendMessage);
		
				while(inLobby){
					DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
					gameSocket.receive(receivethis);
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
					gameSocket.send(checkPacket);
				 	
				 	SendMessage = "started?";
					sendData = SendMessage.getBytes();
					DatagramPacket startPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
					gameSocket.send(startPacket);
					//System.out.println("This was sent from clientsocket: " + SendMessage);
					
					int nrOfPlayers = 0;
					for(@SuppressWarnings("unused") String x : players){
						nrOfPlayers++;
					}
					
					if( menu.startPressed()){
						SendMessage = "start";
						sendData = SendMessage.getBytes();
						DatagramPacket GamePacket = new DatagramPacket(sendData, sendData.length, host, PORT);
						gameSocket.send(GamePacket);
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
						
					
				}
		} catch (IOException | InterruptedException e) {
		e.printStackTrace();
		}
			System.out.println("left loop");
			System.out.println("Entering game");
			gameRunning();
			
			
 }

	 private void gameRunning() {
	
		
		 try{
			System.out.println(gameSocket.isClosed());
			System.out.println(gameSocket.isConnected());
				 
				 SendMessage = "OK";
				 sendData = SendMessage.getBytes();
				 DatagramPacket sendOK = new DatagramPacket(sendData, sendData.length, host, PORT);
				 gameSocket.send(sendOK);	
				 	
				 while(true){
					 
			     //System.out.println("in while");
				 DatagramPacket receiveAntReq = new DatagramPacket(receiveData, receiveData.length);
				 
				 gameSocket.receive(receiveAntReq);
				 System.out.println("received packet");
				 receiveData = receiveAntReq.getData();
				 messageReceived = new String(receiveData, 0, receiveAntReq.getLength());	

				    if(messageReceived.equals("Give ants")){
				    	//System.out.println("client received give ants");
							while(true){
								System.out.print("in while loop");
				    	
								for(Ant x : state.getAntsToUpload()){
							 	SendMessage = x.toString();
								sendData = SendMessage.getBytes();
							 	DatagramPacket sendMe = new DatagramPacket(sendData, sendData.length, host, PORT);
							 	gameSocket.send(sendMe);
							 	System.out.println("sent from client : " + SendMessage );
							 	
							 	}
								state.getAntsToUpload().clear();
						
				    
					     SendMessage = "update ants";
						 sendData = SendMessage.getBytes();
						 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
						 gameSocket.send(sendPacket);
					     System.out.println("client got : " + messageReceived);
					     
					     DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
					     gameSocket.receive(receivethis);
						 receiveData = receivethis.getData();
						 messageReceived = new String(receiveData, 0, receivethis.getLength());	

						 ants = freshList(messageReceived);
						 	
						 state.updateAllAnts(ants);
					     System.out.println("IN CLIENT " + ants);
					     
					     sleep(33);
				    }
		   }
	 }
		 
		 }catch(IOException | InterruptedException e){
			 e.printStackTrace();
		 }
	
	 }
 
	 private ArrayList<Ant> freshList(String x) throws IOException{
		 ArrayList<Ant> temp = new ArrayList<Ant>();
		
		  if(!(x.equals(players.getLast()) || x.equals("start") || x.equals("Give ants")) || x.equals(null) || x == null || x.equals("OK") ) {
			  if(temp.isEmpty()){
				  
				  String[] newString = x.split("&");
				  
				  	for(String z : newString){
				  		if( !(z.equals("") || z == "") ){
				  		temp.add(new Ant(z));
				  		}
				  		
				  	}
			  }
			  else if (!(check(x))){
				  String[] newString = x.split("&");
				  	for(String z : newString){
				  		temp.add(new Ant(z));
				  			}
			  			}
		  		}
			return temp;
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