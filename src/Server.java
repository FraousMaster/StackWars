import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread{
	
	protected DatagramSocket serverSocket = null; 
	protected byte[] receiveData  = new byte[16];
	protected byte[] sendData  = new byte[16];
	private int port;
	private InetAddress IPAddress;
	private LinkedList<String> players ;
	private String messageReceived;
	
	protected boolean gameRunning = true;
	
	public Server() throws Exception{
		serverSocket = new DatagramSocket(1203);
		players = new LinkedList<String>();

	}

		public void run(){
			System.out.println("Hello world");
			
			try{
				while(true){
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				byte[] data = receivePacket.getData();
				 messageReceived = new String(data, 0, receivePacket.getLength());
				 if(!(messageReceived.equals("start"))){
					 new Player(messageReceived).start();
				 }
				
				
				IPAddress = receivePacket.getAddress();
				port = receivePacket.getPort();
				System.out.println("RECEIVED MSG: " + messageReceived);
				
				InetAddress IPAddress = receivePacket.getAddress();
				for(String x : players){
				sendData = x.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
				System.out.println("THIS WAS SENT FROM serverSocket: " + x);
				}
				
				
				multicastInit();
				
				Thread.sleep(1000);
				}
			}
			catch(IOException | InterruptedException e){
					System.out.print(e);
			
		}
	}
		
		private void multicastInit() throws SocketException, UnknownHostException{
			
			try {
						
				DatagramSocket socket = new DatagramSocket();
				InetAddress group = InetAddress.getByName("230.0.0.1");

				sendData = messageReceived.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, group, 8888);
				socket.send(sendPacket); 	
	
				System.out.println("SEND MSG: " + messageReceived);
						
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	}
		public class Player extends Thread{
			String name;
			
			public Player(String player) throws InterruptedException{
				this.name = player;
			    players.add(name);
			    System.out.println("LINKEDLIST : " + players);
			    sleep(250);
			    }
			
			public void run(){
			
				
				
			}
			
		}
}

	
	
