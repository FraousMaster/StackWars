import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server extends Thread{
	
	protected DatagramSocket serverSocket = null; 
	protected byte[] receiveData  = null;
	protected byte[] sendData  = null;
	private String HOST;
	private WaitMenu menu;
	
	protected boolean gameRunning = true;
	
	public Server() throws Exception{
		this("GuestUser", null);
	}
	public Server(String clientName, WaitMenu menu) throws Exception{
		
	    this.HOST = clientName;
		this.menu = menu;
		serverSocket = new DatagramSocket(1201);
	}
		public void run()
		{
			
			
			System.out.println("Hello world");
			receiveData = new byte[64];
			sendData = new byte[64];
			while(gameRunning)
			{
			
				try
				{
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					serverSocket.receive(receivePacket);
					String messageReceived = new String(receivePacket.getData());
					System.out.println("RECEIVED MSG: " + messageReceived);
					
					InetAddress IPAddress = receivePacket.getAddress();
					int port = receivePacket.getPort();
					
					
					//sendData = HOST.getBytes();
					//DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					//serverSocket.send(sendPacket);
					
					sendData = messageReceived.getBytes();
					DatagramPacket sendPacket2 = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(sendPacket2);
					
					menu.setP2(messageReceived);
					menu.update();
					
				}
				catch(IOException e)
				{
					System.out.print("Something went wrong!");
					gameRunning = false;
				}
			}
			System.out.println("Watiing for data....");
			
		}
		
	public class ServerThread extends Thread{
			
			public ServerThread(){
				
				
			}
			
			public void run(){
				
				
			}
			
		}
		
	
	
}