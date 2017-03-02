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
	private ArrayList<Ant> ants = new ArrayList<>();
	private String temp = "";
	
	public Server() throws Exception{
		serverSocket = new DatagramSocket(1203);
		players = new LinkedList<>();
		ants = new ArrayList<>();
		
	}

		public void run(){
			System.out.println("Hello world");
			
			try{
				while(inLobby){
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					serverSocket.receive(receivePacket);
					byte[] data = receivePacket.getData();
					messageReceived = new String(data, 0, receivePacket.getLength());
					System.out.println("Hello alltid i lobby");
					IPAddress = receivePacket.getAddress();
					port = receivePacket.getPort();
					//System.out.println("RECEIVED MSG: " + messageReceived);
					InetAddress IPAddress = receivePacket.getAddress();
				
					if(messageReceived.equals("start")){
						System.out.println("Hello if start");
						started = true;
						sendData = messageReceived.getBytes();
						DatagramPacket startPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
						serverSocket.send(startPacket);
					}
					else if(started &&  messageReceived.equals("started?")){
						System.out.println("Hello started?");
						sendData = "start".getBytes();
						DatagramPacket startPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
						serverSocket.send(startPacket);
						inLobby = false;
						}
					else if(!(messageReceived.equals("update") || messageReceived.equals("start") || messageReceived.equals("started?"))){
						System.out.println("Hello something that is nothing??");
						new Player(messageReceived).start();
						for(String x : players){
							sendData = x.getBytes();
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
							serverSocket.send(sendPacket);
							//System.out.println("THIS WAS SENT FROM serverSocket: " + x);
						}
					}
					else if(messageReceived.equals("update")){
						System.out.println("Hello update");
						sendData = players.getLast().getBytes();
						DatagramPacket sendUpdate = new DatagramPacket(sendData, sendData.length, IPAddress, port);
						serverSocket.send(sendUpdate);
						//System.out.println("THIS WAS SENT FROM serverSocket: " +  players.getLast());
					}
					sleep(250);
				}

			}
			catch(IOException | InterruptedException e){
				System.out.print(e);
			}
            gameRunning();

	}
		
	private void gameRunning() {

        try {
            while (true) {
                DatagramPacket oKPacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(oKPacket);
                receiveData = oKPacket.getData();
                messageReceived = new String(receiveData, 0, oKPacket.getLength());

                if (messageReceived.equals("OK")) {
                    sendData = temp.getBytes();
                    DatagramPacket sendUpdate = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendUpdate);
                }
                else if(!(messageReceived.equals("update") || messageReceived.equals("start") || messageReceived.equals("started?") || messageReceived.equals(null)
                        || messageReceived == null|| messageReceived.equals("OK"))){
                    //System.out.println("SERVER ANT : " + messageReceived.toString());
                    ants.add(new Ant(messageReceived));
                    sendData = temp.getBytes();
                    DatagramPacket sendUpdate = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendUpdate);
                }

                if (!(ants.isEmpty())) {
                    try {
                        temp = "";
                        for (int i = 0; i < ants.size(); i++) {
                            Ant a = ants.get(i);
                            System.out.println("This is the ant at index " + i + " and its value " + a);
                            String[] antValues = a.toString().split(":");
                            int x = Integer.parseInt(antValues[0]);
                            x += 1;
                            antValues[0] = x + "";
                            int b = Integer.parseInt(antValues[1]);
                            b += 1;
                            antValues[1] = b + "";
                            String dummy = "";
                            boolean first = true;
                            for (String s : antValues) {
                                if (first) {
                                    dummy += s;
                                    first = false;
                                } else {
                                    dummy += ":" + s;
                                }

                                //System.out.println("This is my new position " + dummy);
                            }
                            a = new Ant(dummy);
                            ants.set(i, a);
                            temp += "&" + a.toString() + "&";
                        }
                        sleep(33);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }


        } catch (Exception e) {
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
