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
	private ArrayList<String> players ;
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
		players = new ArrayList<String>();
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
					echo("client received "+messageReceived);
					
					if(messageReceived.equals("Start")){
						inLobby = false;
						menu.startGame();
						game =	menu.returnGame();
						state = menu.returnState();
						gameRunning();
						System.out.println("START PRESSED");
						SendMessage = "success";
						sendData = SendMessage.getBytes();
						
						
						
						
					}
					else if(messageReceived.equals("OK")){
						
						inLobby = false;
						
					}
					else{
						
						SendMessage = name;
						if(!(players.contains(messageReceived))){
						 players = playerList(messageReceived);
						 menu.setPlayers(players);
						}
					}
					if(menu.startPressed()){
						SendMessage = "OK";	
					}
					
					//System.out.println(messageReceived);
					
					sendData = SendMessage.getBytes();
					DatagramPacket GamePacket = new DatagramPacket(sendData, sendData.length, host, PORT);
					gameSocket.send(GamePacket);
					echo("client sent : "+SendMessage);
					
					sleep(250);
						
					
				}
		} catch (IOException | InterruptedException e) {
		e.printStackTrace();
		}
			System.out.println("left loop");
			System.out.println("Entering game");
			gameRunning();
			
			
 }
	 
	 private void echo(String s){
		 System.out.println(s);
		 
	 }
	 
	 private ArrayList<String> playerList(String x) throws IOException{
		 ArrayList<String> temp = new ArrayList<String>();

		String[] newString = x.split("&");
				  
		for(String z : newString){
	  		temp.add(z);
  		}
		echo("TEMP :"+ temp);
		return temp;
	 }
			
	 
	 
	 
	 
	private void gameRunning(){

		try {
			
			

			while (true) {
			
				SendMessage = "OK";
				sendData = SendMessage.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
				gameSocket.send(sendPacket);
				
				echo("in client gamerunning while loop");
				DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
				echo("in client gamerunning while loop");
				try{
					gameSocket.setSoTimeout(1000);
				gameSocket.receive(receivethis);
				} catch(IOException e){
					e.printStackTrace();
					continue;
				}
				echo("in client gamerunning while loo2p");
				receiveData = receivethis.getData();
				echo("in client gamerunning while loo444p");
				messageReceived = new String(receiveData, 0, receivethis.getLength());
				System.out.println("received in gamerunning : "+messageReceived);
				
				if(!(messageReceived.equals("Start"))){
					if(messageReceived.equals(null)){
						System.out.println(messageReceived+" : Rasdasd");
						ants = freshList(messageReceived);
						state.updateAllAnts(ants);
					}
				}
				
				/*else if((state.getAntsToUpload() == null || state.getAntsToUpload().isEmpty() || state.getAntsToUpload().equals(null))) {
					for (Ant x : state.getAntsToUpload()) {
						SendMessage = x.toString();
						sendData = SendMessage.getBytes();
						
						//System.out.println("sent from client : " + SendMessage);
					}
					state.getAntsToUpload().clear();
				} */
				else{
					SendMessage = "OK".toString();
					sendData = SendMessage.getBytes();
					
					//System.out.println("sent from client : " + SendMessage);
				}
				sleep(33);
			
				//DatagramPacket sendMe = new DatagramPacket(sendData, sendData.length, host, PORT);
				//gameSocket.send(sendMe);
			
				echo("in client gamerunning while loo444p444");
			}

			
			
		}catch(IOException | InterruptedException e){
			e.printStackTrace();
	}
}
	
	
	
	
 
	 private ArrayList<Ant> freshList(String x) throws IOException{
		 ArrayList<Ant> temp = new ArrayList<Ant>();
		
		  
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
}