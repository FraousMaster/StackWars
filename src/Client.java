import java.io.*;
import java.net.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Random;

public class Client extends Thread{
	private static String IP_ADDRESS;
	private static String name;
	private static byte[] sendData = new byte[16];
	private static byte[] receiveData = new byte[200];
	private DatagramSocket clientSocket;
	private String SendMessage;
	private LobbyMenu menu;
	private String messageReceived;
	private LinkedList<String> players ;
<<<<<<< HEAD
	private boolean gameGo = true;
=======
	private boolean inLobby = true;
>>>>>>> afe5b08f11e0ccad67de3673eaafa60098e0ca0a
	
	@SuppressWarnings("static-access")
	public Client(String IP, String name, LobbyMenu menu) throws SocketException{
		this.IP_ADDRESS = IP;
		this.name = name;
		this.menu = menu;
		players = new LinkedList<String>();
		clientSocket = new DatagramSocket();	    
	}
	
	 public void run(){
		 InetAddress host;
		
			try {
				
				host = InetAddress.getByName(IP_ADDRESS);
<<<<<<< HEAD
				System.out.println(host);
				clientSocket.setSoTimeout(500);
=======
>>>>>>> afe5b08f11e0ccad67de3673eaafa60098e0ca0a
				SendMessage = name;
				sendData = SendMessage.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, 1203);
			 	clientSocket.send(sendPacket);
				System.out.println("This was sent from clientsocket: " + SendMessage);
<<<<<<< HEAD

				try{
				while(true){
				
				DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivethis);
				byte[] data = receivethis.getData();
				messageReceived = new String(data, 0, receivethis.getLength());
				new Player(messageReceived).start();

				}
				} catch (SocketTimeoutException e) {
					System.out.println("socket timeout");
					multicastInit();
				    }
				} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
	 }
=======
		
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
					DatagramPacket checkPacket = new DatagramPacket(sendData, sendData.length, host, 1203);
				 	clientSocket.send(checkPacket);
				 	
				 	SendMessage = "started?";
					sendData = SendMessage.getBytes();
					DatagramPacket startPacket = new DatagramPacket(sendData, sendData.length, host, 1203);
				 	clientSocket.send(startPacket);
					//System.out.println("This was sent from clientsocket: " + SendMessage);
					
					int nrOfPlayers = 0;
					for(String x : players){
						nrOfPlayers++;
					}
					
					if( menu.startPressed()){
						SendMessage = "start";
						sendData = SendMessage.getBytes();
						DatagramPacket GamePacket = new DatagramPacket(sendData, sendData.length, host, 1203);
					 	clientSocket.send(GamePacket);
						System.out.println(nrOfPlayers);
					}
					
					System.out.println(messageReceived);
					
					if(messageReceived.equals("start")){
						System.out.println("START PRESSED");
						menu.startGame();
						inLobby = false;
					}
					
					sleep(250);
						
					
				}} catch (IOException | InterruptedException e) {
		e.printStackTrace();
		}
			System.out.println("left loop");
			
			
 }
>>>>>>> afe5b08f11e0ccad67de3673eaafa60098e0ca0a

	 private void multicastInit() throws UnknownHostException{
		 DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		 String INET_ADDR = "224.3.0.0";
		 InetAddress address;
	
		try {
			address = InetAddress.getByName("224.3.0.0");


			 MulticastSocket multiSocket = new MulticastSocket(8888);
<<<<<<< HEAD
			 System.out.println(address);
			 multiSocket.joinGroup(address);
			 while(gameGo){
				 System.out.println("1");
				 System.out.println("2");
				 multiSocket.receive(receivePacket);

				 System.out.println("3");
				 receiveData = receivePacket.getData();
				 messageReceived = new String(receiveData, 0 ,receivePacket.getLength() );

					System.out.println("This was received from multi: " + messageReceived);	
					new Player(messageReceived).start();
=======
			 multiSocket.joinGroup(address);
			
			 while(true){
				 System.out.println("1");
				 	multiSocket.receive(receivePacket);
				 	receiveData = receivePacket.getData();
					messageReceived = new String(receiveData, 0 ,receivePacket.getLength() );

					System.out.println("This was received from multi: " + messageReceived);	
					new Player(messageReceived);
>>>>>>> afe5b08f11e0ccad67de3673eaafa60098e0ca0a
			 }
		}catch (IOException e) {
			e.printStackTrace();
		} 
	 }
 
<<<<<<< HEAD
	public class Player extends Thread {
=======
	public class Player {
>>>>>>> afe5b08f11e0ccad67de3673eaafa60098e0ca0a
		String name;
		
		public Player(String player){
			this.name = player;
		    players.add(name);
		    System.out.println("entered player : " + name);
		    System.out.println("linkedlist in client : " + players);
		    }
		
		public void run() 
		{
			menu.setPlayers(name);
		}
		
	}
}