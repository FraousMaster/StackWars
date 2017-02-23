import java.io.*;
import java.net.*;
import java.util.LinkedList;

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
<<<<<<< HEAD
	private boolean Lobby = true;
	private MulticastSocket multiSocket;
	
=======
	private boolean gameGo = true;
>>>>>>> origin/JohannesBranchv2
	@SuppressWarnings("static-access")
	public Client(String IP, String name, LobbyMenu menu) throws SocketException{
		this.IP_ADDRESS = IP;
		this.name = name;
		this.menu = menu;
		players = new LinkedList<String>();
<<<<<<< HEAD
		clientSocket = new DatagramSocket();
=======
		clientSocket = new DatagramSocket();	
		
		
>>>>>>> origin/JohannesBranchv2
	}
	
	 public void run(){
		 InetAddress host;
		 
		
			try {
				
				
				host = InetAddress.getByName(IP_ADDRESS);
				clientSocket.setSoTimeout(500);
				SendMessage = name;
				sendData = SendMessage.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, 1203);
			 	clientSocket.send(sendPacket);
				System.out.println("This was sent from clientsocket: " + SendMessage);
		
				try{
				while(Lobby){
				
				DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivethis);
				byte[] data = receivethis.getData();
				messageReceived = new String(data, 0, receivethis.getLength());
				new Player(messageReceived).start();
<<<<<<< HEAD
				
=======
		
>>>>>>> origin/JohannesBranchv2
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
<<<<<<< HEAD
}
	 
=======
			
}
	 public void sendData(String x) throws SocketTimeoutException, SocketException, UnknownHostException{
		 try{
			 
			clientSocket.setSoTimeout(500);
			SendMessage = x;
			sendData = SendMessage.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(IP_ADDRESS), 1203);
		 	clientSocket.send(sendPacket);
			System.out.println("This was sent from clientsocket: " + SendMessage);
		
		 }catch (IOException e) {
			 e.printStackTrace();
		 }
		 
		 
		 
	 }
>>>>>>> origin/JohannesBranchv2

	 private void multicastInit() throws UnknownHostException{
		 DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		 String INET_ADDR = "230.0.0.1";
		 InetAddress address;
	
		try {
			address = InetAddress.getByName(INET_ADDR);

			 multiSocket = new MulticastSocket(8888);
			 multiSocket.joinGroup(address);
<<<<<<< HEAD
			 while(Lobby){
=======
			 
			
			 while(gameGo){
>>>>>>> origin/JohannesBranchv2
				 	receiveData = receivePacket.getData();
				 	multiSocket.receive(receivePacket);
					messageReceived = new String(receiveData, 0 ,receivePacket.getLength() );

					System.out.println("This was received from multi: " + messageReceived);	
<<<<<<< HEAD
					new Player(messageReceived).run();
					
					
					if(menu.startPressed()){
						System.out.println(" entered startpressed" + menu.startPressed());
				
						//new Game();
	
					}
=======
					if(!(messageReceived.equals("start"))){
					new Player(messageReceived).start();
					}
					else if(messageReceived.equals("start")){
						
						System.out.println("entered start");
						new Game();
					}
				
						
						
					
>>>>>>> origin/JohannesBranchv2
			 }
		}catch (IOException e) {
			e.printStackTrace();
		} 
	 }
 
	public class Player extends Thread {
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
	
