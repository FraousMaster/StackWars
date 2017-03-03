import java.io.*;
import java.net.*;
import java.util.*;
import Global.Resources;

public class Client extends Thread{
	private static String IP_ADDRESS;
	private static String name;
	private static byte[] sendData = new byte[1000];
	private static byte[] receiveData = new byte[1000];
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
				sleep(33);
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
			 SendMessage = "success";
			 sendData = SendMessage.getBytes();
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
			 SendMessage = "OK";	
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

		if(!(messageReceived.equals("Start"))){
			if(!messageReceived.equals("")){
				ants = freshList(messageReceived);
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
	
	
	private ArrayList<Ant> freshList(String x) throws IOException{
		ArrayList<Ant> temp = new ArrayList<Ant>();
		if(temp.isEmpty()){
			String[] newString = x.split("&");
		  	for(String z : newString)
		  	{
		  		if( !(z.equals("") || z == "") )
		  		{
		  			temp.add(new Ant(z));
		  		}
		  	}
		}
		else if (!(check(x))){
			String[] newString = x.split("&");
			for(String z : newString)
			{
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