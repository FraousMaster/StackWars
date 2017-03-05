import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.*;
import Global.Resources;

public class Server extends Thread {

    protected DatagramSocket serverSocket = null;
    protected byte[] receiveData = new byte[1000];
    protected byte[] sendData = new byte[1000];
    private int port;
    @SuppressWarnings("unused")
    private InetAddress IPAddress;
    private LinkedList<String> players;
    private String messageReceived;
    @SuppressWarnings("unused")
    private final int MAX_PLAYERS = 4;
    private boolean started = false;
    protected boolean gameIsRunning = true;
    protected boolean inLobby = true;
    private boolean starting = false;
    private ArrayList<Ant> ants = new ArrayList<>();
    private ArrayList<Stack> stacks = new ArrayList<>();
    private ArrayList<Roads> roads = new ArrayList<>();
    private HashMap<Point, ArrayList<Roads>> allRoads = new HashMap<>();
    private String temp = "";
    private int countPlayersStarted = 0;

    public Server() throws Exception {
        serverSocket = new DatagramSocket(1203);
        players = new LinkedList<>();
        ants = new ArrayList<>();      
        stacks = new ArrayList<>();
    }

    public void run() {
        System.out.println("Hello world");

        try {
            while (true) {
            	recData();
            	//System.out.println(messageReceived);
                if(inLobby)
                {
                	currentModeInLobby();
                }
                else
                {
                	gameRunning();
                }
                sendData();
            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    private void sendData() throws IOException{
    	 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
         serverSocket.send(sendPacket);
    }
    
    private void recData() throws IOException{
    	
    	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        byte[] data = receivePacket.getData(); 
        messageReceived = new String(data, 0, receivePacket.getLength());
       
        IPAddress = receivePacket.getAddress();
        port = receivePacket.getPort();
	
    }
    
    
    private void currentModeInLobby()
    {
    	if(starting)
    	{
    		if(messageReceived.equals("success"))
        	{
        		System.out.println("WE GET ONE SUCCESS!");
        		countPlayersStarted++;
        		if(countPlayersStarted >= players.size())
        		{
        			started = true;
        		}
        	}
    		else if(started){
                //System.out.println("trying to go into game bitch");
                inLobby = false;
                sendData = "started".getBytes();
                for(String s :Resources.getAllStacks())
        		{
                	Stack stack = new Stack(s);
                	stacks.add(stack);
        		}
                for(String s : Resources.getAllRoads())
                {
                	Roads road = new Roads(s);
                	roads.add(road);
                }
            }
    		else
    			sendData = "Start".getBytes();
    	}
    	else
    	{
    		 if(messageReceived.equals("OK")) {
                starting = true;
                sendData = "Start".getBytes();
    		 }
    		 else{
	            if(players.contains(messageReceived)){
	                String y = new String();
	                
	                for(String x : players) {
	                   y += x + "&";

	                }
	                sendData = y.getBytes();
	            }
	            else {
	                players.add(messageReceived);
	                for(int i = 0; i <= players.size(); i++)
	                {
	                	int playerID = i;
	                	sendData = ("setplayer" + playerID).getBytes();
	                }	
	            }
	        }
    	}
    }

    private void gameRunning() {
        if(messageReceived.equals("OK")) {
            sendData = temp.getBytes();
            //System.out.println("HELLOW WORLD CAN U SEE ME");
        }
        else if(messageReceived.contains("setplayerstack"))
        {
        	String playerStack = messageReceived.replace("setplayerstack", "");
        	Stack tempStack = new Stack(playerStack);
        	for(Stack stack : stacks)
        	{
        		if(stack.getX() == tempStack.getY() && stack.getY() == tempStack.getY())
        		{
        			int index = stacks.indexOf(stack);
        			stacks.set(index, tempStack);
        		}
        		
        	}
        }
        else if (!(messageReceived.equals(players.getLast()) || messageReceived.equals("success") || messageReceived.equals("start") || messageReceived.equals(null)
                || messageReceived == null || messageReceived.equals("OK") || check(messageReceived)))
        {
            ants.add(new Ant(messageReceived));
            sendData = temp.getBytes();
        }
        if (!(ants.isEmpty())) {
            try {
                temp = "";
                for (int i = 0; i < ants.size(); i++) {
                    Ant a = ants.get(i);
                    
                    //System.out.println("This is the ant at index " + i + " and its value " + a);
            		String[] antValues = a.toString().split(":");
            		int type = Integer.parseInt(antValues[3]);
                    int x = Integer.parseInt(antValues[0]);
                    if(type == 4)
                    {
                    	x += 4;
                    }
                    if(type == 9)
                    {
                    	x -= 4;
                    }
                    
                    antValues[0] = x + "";
                    int b = Integer.parseInt(antValues[1]);
                    if(type == 3)
                    {
                    	b += 4;
                    }
                    if(type == 8)
                    {
                    	b -= 4;
                    }
                    
                    		
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
                    }
                   // System.out.println("This is my new position " + dummy);
                    a = new Ant(dummy);
                    ants.set(i, a);
                    if(checkCollide(x, b, a.getCurrentMapObject()))
                		ants.remove(a);
                    temp += "&" + a.toString() + "&";
                    sendData =temp.getBytes();
                    
                   
                    	
                    	
                 
                }
                sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        	sendData = "b".getBytes();
    }
    
    private boolean checkCollide(int x, int y, int type)
    {
    	int xOff = Resources.getAntXOffset(type);
    	int yOff = Resources.getAntYOffset(type);
    	int xBlock = Resources.getScalingFactorX();
    	int yBlock = Resources.getScalingFactorY();
    	for(Stack s : stacks){
             
         	int xPos = s.getX();
         	int yPos = s.getY();
         	int xEndPos = xPos + xBlock;
         	int yEndPos = yPos + yBlock;
         	System.out.println("XPOS: " + xPos + " YPOS : " + yPos + " ," +  x + " , " + y + " ,  " + xEndPos + " ,  " + yEndPos);
         	if(x > xPos && x < xEndPos && y > yPos && y < yEndPos)
         	{
             	System.out.println("COLLIDEEE!!!");
             	return true;
     		}
         	
         }
    	 return false;
    }
    private boolean check(String s)
    {
        boolean exists = false;
        for(String x : players){
            if( x.toString().equals(s))
            {
                exists = true;
            }
        }
        return exists;
    }
}

