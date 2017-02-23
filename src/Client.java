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
	private boolean gameGo = true;
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
				
				clientSocket.setSoTimeout(500);
				SendMessage = name;
				sendData = SendMessage.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, 1203);
			 	clientSocket.send(sendPacket);
				System.out.println("This was sent from clientsocket: " + SendMessage);
		
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

	 private void multicastInit() throws UnknownHostException{
		 DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		 String INET_ADDR = "230.0.0.1";
		 InetAddress address;
	
		try {
			address = InetAddress.getByName(INET_ADDR);

			 MulticastSocket multiSocket = new MulticastSocket(8888);
			 multiSocket.joinGroup(address);
			 while(gameGo){
				 	receiveData = receivePacket.getData();
				 	multiSocket.receive(receivePacket);
					messageReceived = new String(receiveData, 0 ,receivePacket.getLength() );

					System.out.println("This was received from multi: " + messageReceived);	
					new Player(messageReceived).run();
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
	
