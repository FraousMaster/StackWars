import java.io.*;
import java.net.*;
import java.util.*;
import Global.Resources;

public class Client extends Thread{
	private static String IP_ADDRESS;
	private static String name;
	private static byte[] sendData = new byte[2000];
	private static byte[] receiveData = new byte[2000];
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
			// TODO Auto-generated catch block
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
				//echo("client received "+messageReceived);
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
				sleep(132);
			}	
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	 
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
	 
	 
	 private void sendData(){
		 try {
		 	sendData = SendMessage.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, PORT);
			gameSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
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
		//echo("TEMP :"+ temp);
		return temp;
	 }

	private void gameRunning() throws IOException{
		
		if(messageReceived.contains("started"))
		{
			
			String a = messageReceived.replace("started", "");
			String[] arr = a.split("&");
			
			if(!(a == null || a == "" || a.equals(null) || a.equals("")))
			{
				for(int i = 0; i < arr.length; i++)
				{
					//System.out.println(arr[i]);
					Stack enemyStack = new Stack(arr[i]);
					if(enemyStack.getOwnedBy() != Resources.getMyPlayerID())
					{
						//System.out.println(arr[0]);
						for(Stack s : state.getStacks())
						{
							if(enemyStack.getX() == s.getX() && enemyStack.getY() == s.getY())
							{
								int temp = state.getStacks().indexOf(s);
								state.getStacks().set(temp, enemyStack);
							}
						}
					}
				}
			}
			started = true;
		}
		else
			SendMessage = "waiting".toString();
		
		if(started)
		{
			//System.out.println("HELOOW WORLD CAN U SEE ME!");
			if(!(messageReceived.equals("Start") || messageReceived.contains("started"))){
				if(messageReceived.equals("b"))
				{
					state.updateAllAnts(null);
				}
				else if(!messageReceived.equals("")){
					String[] a = messageReceived.split("s");
					state.updateAllStacks(a[1]);
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
				SendMessage = x;
				state.getAntsToUpload().clear();
			}
			else
			{
				SendMessage = "OK".toString();
			}
		}
	}
	
	
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