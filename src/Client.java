 import java.io.*;
import java.net.*;
import java.util.*;
import Global.Resources;

/**
 * 
 * Client for the game, interacts with the server and gameState. Sends data and recieves data.
 * @author Arvid Wiklund, Hugo Frost, Linus Nilsson, Johannes Edenholm
 *
 */
public class Client extends Thread{
	private static String IP_ADDRESS;
	private static String name;
	private static byte[] sendData = new byte[2000];
	private static byte[] receiveData = new byte[2000];
	private DatagramSocket gameSocket = null ;
	private String SendMessage;
	private LobbyMenu menu;
	private String messageReceived;
	private ArrayList<String> players ;
	private boolean inLobby = true;
	private final int PORT = 1203;
	private InetAddress host;
	private ArrayList<Ant> ants;
	private GameState state;
	private boolean started = false;
	private boolean first = true;

	
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
			e.printStackTrace();
		}
	}
	
	 public void run(){
		try {
			SendMessage = name;
			sendData();
			while(true)
			{
				recData();
				System.out.println("client received "+messageReceived);

				if(inLobby)
				{
					inLobby();
				}
				else
				{
					gameRunning();
				}
				sendData();
				//echo("client sent : "+SendMessage);
				sleep(33);
			}	
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	 /**
	  * Depending on what message server receives from the client
	  * makes a choice and sends a response to the client.
	  * Also checks if the host has clicked on start.
	  * @throws IOException
	  */
	 private void inLobby() throws IOException
	 {
		 if(messageReceived.equals("Start"))
		 {
			 System.out.println("TRYING TO START GAME!");
			 menu.startGame();
			 menu.returnGame();
			 state = menu.returnState();
			 inLobby = false;
			 SendMessage = "success" + Resources.getMyStack();
			 sendData = SendMessage.getBytes();
		 }
		 else if(messageReceived.contains("setplayer"))
		 {
			 String playerID = messageReceived.replace("setplayer", "");
			 System.out.println(playerID);
			 Resources.setMyPlayerID(playerID);
			 System.out.println(Resources.getMyPlayerID());
		 }
		 else if(messageReceived.equals("OK")){
			 SendMessage = "OK";
		 }
		 else
		 {
			 SendMessage = name;
			 if(!(players.contains(messageReceived)))
			 {
				 players = playerList(messageReceived);
				 menu.setPlayers(players);
			 }
		 }
		 if(menu.startPressed()){
			 if(first)
			 {
				 SendMessage = "OK";
				 first = false;
			 }
		 }
	 }
	 
	 /**
	  * Receives the data
	  */
	 private void recData(){
		 try {
		 	DatagramPacket receivethis = new DatagramPacket(receiveData, receiveData.length);
		 	gameSocket.receive(receivethis);
			byte[] data = receivethis.getData();
			messageReceived = new String(data, 0, receivethis.getLength());
		 } catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 
	 /**
	  * Sends the data
	  */
	 private void sendData(){
		 try {
		 	sendData = SendMessage.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
			gameSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
	 }
	 
	 /**
	  * 
	  * @param x
	  * @return ArrayList<String> is a list of all players in game. This list is constantly updated and replaced.
	  * @throws IOException
	  */
	 private ArrayList<String> playerList(String x) throws IOException{
		 ArrayList<String> temp = new ArrayList<String>();

		String[] newString = x.split("&");
				  
		for(String z : newString){
	  		temp.add(z);
  		}
		return temp;
	 }

	 /**
	  * Handles all the received data.
	  * When start has been pressed we are in gameRunning
	  * Updates ants on map, checks for collisions, sets so players owns a stack/stacks.
	  * @throws IOException
	  */
	private void gameRunning() throws IOException{
		
		if(messageReceived.equals("started"))
		{
			System.out.println("STARTING");
			started = true;
		}
		else
		{
			SendMessage = "waiting".toString();
			System.out.println("IN WAITINT");
		}
		if(started)
		{
			if(!(messageReceived.equals("Start") || messageReceived.contains("started"))){
				if(messageReceived.equals("b"))
				{
					state.updateAllAnts(null);
				}
				else if(!messageReceived.equals("")){
					String[] a = messageReceived.split("s");
					if(a.length > 1){
						state.updateAllStacks(a[1]);
					}
					if(a[0] != "")
						ants = freshList(a[0]);
					
					state.updateAllAnts(ants);
				}
			}
			if(!state.getAntsToUpload().isEmpty())
			{
				String x = "";
				for(Ant s : state.getAntsToUpload())
				{
					x += s.toString();
				}
				if(!state.getStacksToUpdate().isEmpty())
				{
					x += "s";
					for(String s : state.getStacksToUpdate())
					{
						x += s;
						
					}
				}
				SendMessage = x;
				state.getAntsToUpload().clear();
				state.getStacksToUpdate().clear();
			}
			else
			{
				SendMessage = "OK".toString();
			}
		}
	}
	
	/**
	 * 
	 * @param x
	 * @return ArrayList<Ant> Converts message received from server to Ants.
	 * @throws IOException
	 */
	private ArrayList<Ant> freshList(String x) throws IOException{
		ArrayList<Ant> temp = new ArrayList<Ant>();
		if(temp.isEmpty()){
			String[] newString = x.split("&");
		  	for(String z : newString)
		  	{
		  		if( !(z.equals("") || z == "") )
		  		{
		  			if(!z.contains("s"))
		  			{
		  				//System.out.println("Ants: " + z);
		  				temp.add(new Ant(z));
		  			}
		  				
		  		}
		  	}
		}
		else if (!(check(x))){
			String[] newString = x.split("&");
			for(String z : newString)
			{
				//System.out.println("Ants2: " + z);
				temp.add(new Ant(z));
			}
		}
		return temp;
	}
			
	 /**
	  * 
	  * @param s
	  * @return boolean Returns true if a version of that ant already exists. 
	  */
	 private boolean check(String s)
	 {
		 boolean exists = false;
		 for(Ant x : ants){
			 if( x.toString().equals(s))
			 {
				 exists = true;
			 }	
		 }
		 return exists;
	 }
}