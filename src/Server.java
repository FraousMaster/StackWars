import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread{
	
	protected DatagramSocket serverSocket = null; 
	protected byte[] receiveData  = new byte[1000];
	protected byte[] sendData  = new byte[1000];
	private int port;
	@SuppressWarnings("unused")
	private InetAddress IPAddress;
	private LinkedList<String> players ;
	private String messageReceived;
	@SuppressWarnings("unused")
	private final int MAX_PLAYERS = 4;
	private boolean started = false;
	protected boolean gameIsRunning = true;
	protected boolean inLobby = true;
	
	public Server() throws Exception{
		serverSocket = new DatagramSocket(1203);
		players = new LinkedList<String>();

	}

		public void run(){
			System.out.println("Hello world");
			
			try{
				while(inLobby){
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				byte[] data = receivePacket.getData();
				 messageReceived = new String(data, 0, receivePacket.getLength());
				 
				
				
				IPAddress = receivePacket.getAddress();
				port = receivePacket.getPort();
				//System.out.println("RECEIVED MSG: " + messageReceived);
				InetAddress IPAddress = receivePacket.getAddress();
				
				if(messageReceived.equals("start")){
					started = true;
					sendData = messageReceived.getBytes();
					DatagramPacket startPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(startPacket);
					}
					else if(started &&  messageReceived.equals("started?") ){
						sendData = "start".getBytes();
						DatagramPacket startPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
						serverSocket.send(startPacket);
						inLobby = false;
						}
						else if(!(messageReceived.equals("update") || messageReceived.equals("start") || messageReceived.equals("started?") )){
							new Player(messageReceived).start();
							for(String x : players){
								sendData = x.getBytes();
								DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
								serverSocket.send(sendPacket);
								//System.out.println("THIS WAS SENT FROM serverSocket: " + x);
									}
							}
							else if(messageReceived.equals("update")){
							sendData = players.getLast().getBytes();
							DatagramPacket sendUpdate = new DatagramPacket(sendData, sendData.length, IPAddress, port);
							serverSocket.send(sendUpdate);
							//System.out.println("THIS WAS SENT FROM serverSocket: " +  players.getLast()); 	
				}
				
				
			}
				
			}catch(IOException | InterruptedException e){
					System.out.print(e);
			
		}
			System.out.println("ended loop");
			System.out.println("entering game-loop");
			gameRunning();
	}
		
		private void gameRunning() {
			Ant ant = null;
			
			try{
				while(gameIsRunning){
					
					DatagramPacket incomingPacket = new DatagramPacket(receiveData, receiveData.length);
					serverSocket.receive(incomingPacket);
					byte[] data = incomingPacket.getData();
					ByteArrayInputStream in = new ByteArrayInputStream(data);
					ObjectInputStream is = new ObjectInputStream(in);
					
					try {
					Ant ants = (Ant) is.readObject();
					System.out.println("Student object received = "+  ants);
					}catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					

		      System.out.println("IN SERVER :");
		      System.out.println(ant);
			
			
			if(messageReceived.equals("update game")){
				sleep(1);
				//System.out.println("SERVER : :"+messageReceived);
				//send position of all ants moving on map
				
			}
				
		}
			
			}catch(IOException | InterruptedException e){
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

public class Ants{

	
	
}
}