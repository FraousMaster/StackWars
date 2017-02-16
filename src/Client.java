import java.io.*;
import java.net.*;
import java.util.LinkedList;
public class Client{
	private static String IP_ADDRESS;
	private static	String name;
	private static byte[] sendData = new byte[64];
	private static byte[] receiveData = new byte[64];
	private DatagramSocket clientSocket;
	private String SendMessage;
	private WaitMenu menu;
	private String messageReceived;
	private LinkedList<DatagramPacket> queue ;
	private String HOST;
	
	@SuppressWarnings("static-access")
	public Client(String IP, String name, WaitMenu menu){
		this.IP_ADDRESS = IP;
		this.name = name;
		this.menu = menu;
		HOST = menu.getHOST();


	}
	
	 public void run(){
		 
		InetAddress host;
		try {
			clientSocket = new DatagramSocket();
		
			host = InetAddress.getByName("65.99.151.33");
			SendMessage = name;
			sendData = SendMessage.getBytes();
		
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, 1201);
			clientSocket.send(sendPacket);
			menu.setHOST(HOST);
			clientSocket.setSoTimeout(500);
			
			while(true){
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			clientSocket.receive(receivePacket);
			messageReceived = new String(receivePacket.getData());
			
			
			menu.setP2(messageReceived);
			menu.update();
			
			System.out.println("This was received: " + messageReceived);
			System.out.println("This was sent: " + SendMessage);
			}
			
		
			
				
			
			
			
				
				}catch(IOException e){
					System.out.println(e);
				}
		
}
	
	public void RecieveData() throws IOException{
		
		clientSocket = new DatagramSocket();
		
		
		
		}
		
		
		
		
}
	
