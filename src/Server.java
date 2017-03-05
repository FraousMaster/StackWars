import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.*;
import Global.Resources;

public class Server extends Thread {

    protected DatagramSocket serverSocket = null;
    protected byte[] receiveData = new byte[2000];
    protected byte[] sendData = new byte[2000];
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
    private int count = 0;
    private ArrayList<Ant> ants = new ArrayList<>();
    private ArrayList<Stack> stacks = new ArrayList<>();
    private ArrayList<Roads> roads = new ArrayList<>();
    private ArrayList<String> stacksToUpdate = new ArrayList<>();
    private HashMap<Point, ArrayList<Roads>> allRoads = new HashMap<>();
    private String temp = "";
    private int countPlayersStarted = 0;
    private String sendMessage;
    private boolean first = true;
    
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

    private void sendData(){
		 try {
		 	sendData = sendMessage.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
    		if(messageReceived.contains("success"))
        	{
        		countPlayersStarted++;
        		if(countPlayersStarted >= players.size())
        		{
        			System.out.println("WE GET ONE SUCCESS!");
        			started = true;
        		}
        		if(first)
            	{
            		for(String s :Resources.getAllStacks())
            		{
                    	Stack stack = new Stack(s);
                    	stacks.add(stack);
            		}
            		first = false;	
            	}
        		
        		String a = messageReceived.replace("success", "");
        		Stack tempS = new Stack(a);
        		for(Stack stack : stacks)
        		{
        			if(stack.getX() == tempS.getX() && stack.getY() == tempS.getY())
        			{
        				stacks.set(stacks.indexOf(stack), tempS);
        			}
        		}
        	}
    		else if(started){
    			count++;
    			
    			if(count == 10)
	                inLobby = false;
    			
    			String s = "";
    			for(Stack stack : stacks)
    			{
    				if(stack.getOwnedBy() != 0)
    				{
    					s += stack.toString() + "&";
    					//System.out.println(s);
    				}
    			}
    			
    			sendMessage = "started" + s;
            }
    		else
    			sendMessage = "Start";
    	}
    	else
    	{
    		 if(messageReceived.equals("OK")) {
                starting = true;
                sendMessage = "Start";
    		 }
    		 else{
	            if(players.contains(messageReceived)){
	                String y = new String();
	                
	                for(String x : players) {
	                   y += x + "&";

	                }
	                sendMessage = y;
	            }
	            else {
	                players.add(messageReceived);
	                for(int i = 0; i <= players.size(); i++)
	                {
	                	int playerID = i;
	                	sendMessage = ("setplayer" + playerID);
	                }	
	            }
	        }
    	}
    }

    private void gameRunning() {
    		
        if(messageReceived.equals("OK")) {
        	sendMessage = temp;
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
        else if (!(messageReceived.equals(players.getLast()) || messageReceived.contains("success") || messageReceived.equals("start") || messageReceived.equals(null)
                || messageReceived == null || messageReceived.equals("OK") || check(messageReceived) || messageReceived.equals("waiting")))
        {
        	Ant dummy = new Ant(messageReceived);
            ants.add(dummy);
            int xS = Resources.getAntXOffset(dummy.getCurrentMapObject());
            int yS = Resources.getAntYOffset(dummy.getCurrentMapObject());
            for(Stack s : stacks)
            {	
            	if((dummy.getPosX() - xS) == s.getX() && (dummy.getPosY() - yS) == s.getY())
            	{
            		s.decreasePopulation(dummy);
            	}
            }
            sendMessage = temp;
        }
        if (!(ants.isEmpty())) {
            try 
            {
            	antCalculations();
            	//System.out.println(getAllStacks());
            	temp += "s" + getAllStacks();
                sendData = temp.getBytes();
                sendMessage = temp;
            	sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        	sendMessage = "b";
    }
    
    private void antCalculations()
    {
    	temp = "";
        for (int i = 0; i < ants.size(); i++) {
            Ant a = ants.get(i);
            
    		String[] antValues = a.toString().split(":");
    		int type = Integer.parseInt(antValues[3]);
            int x = Integer.parseInt(antValues[0]);
            if(type == 4)
            {
            	x += 8;
            }
            if(type == 9)
            {
            	x -= 8;
            }
            
            antValues[0] = x + "";
            int b = Integer.parseInt(antValues[1]);
            if(type == 3)
            {
            	b += 8;
            }
            if(type == 8)
            {
            	b -= 8;
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
            a = new Ant(dummy);
            ants.set(i, a);
            	
            antsCollide(a);
            
            if(checkCollide(a, x, b))
            	ants.remove(a);
            
            temp += "&" + a.toString() + "&";
        }
    }
    
    private String getAllStacks()
    {
    	String stacksInString = "";
    	for(Stack s : stacks)
    	{
    		stacksInString += s.toString() + "&";
    	}
    	
    	return stacksInString;
    }
    
    private void antsCollide(Ant a)
    {
    	ArrayList<Ant> temp = ants;
    	Ant antTemp = null;
    	for(Ant check : temp)
    	{
    		int owner1 = a.getOwnedBy();
    		int owner2 = check.getOwnedBy();
    		
    		if(owner1 != owner2)
    		{
    			
        		int type2 = check.getCurrentMapObject();
        		int type1 = a.getCurrentMapObject();
        		
        		if((type1 == 3 && type2 == 8) || (type1 == 8 && type2 == 3))
        		{
        			int y1 = a.getPosY();
            		int y2 = check.getPosY();
        			
        			if(type1 == 3)
        			{
        				if((y2- 7) > y1)
        				{
        					antTemp = check;
        					break;
        				}
        			}
        			else
        			{
        				if(y1 < (y2 - 7))
        				{
        					antTemp = check;
        					break;
        				}
        			}
        		}
        		if((type1 == 4 && type2 == 9) || (type1 == 4 && type2 == 9))
        		{
        			
        			int x1 = a.getPosX();
        			int x2 = check.getPosX();
        			if(type1 == 4)
        			{
        				if(x1 > (x2 - 36))
        				{
        					antTemp = check;
        					break;
        				}
        			}
        			else
        			{
        				if((x2 + 36) < x1)
        				{
        					antTemp = check;
        					break;
        				}
        			}
        		}
    		}
    	}
    	if(antTemp != null)
    	{
    		temp.remove(antTemp);
    		temp.remove(a);
    	}
    	ants = temp;
    }
    private boolean checkCollide(Ant a, int x, int y)
    {
    	int xBlock = Resources.getScalingFactorX();
    	int yBlock = Resources.getScalingFactorY() - 3;
    	for(Stack s : stacks){
    		int type = a.getCurrentMapObject();
			if(type == 3 || type == 8)
    		{
    			int y1 = a.getPosY();
        		int y2 = s.getY();
    			
    			if(type == 3)
    			{
    				
    				if((y1 + 30) >= y2 && y1 <= (y2 + yBlock))
    				{
    					System.out.println((y1 + 30) + " , "  + y2 + " , " + y1 + " , " + (y2 + yBlock));
    					if(s.getOwnedBy() == a.getOwnedBy())
    					{
    						s.increasePopulation();
    						return true;
    					}
    					else {
							s.decreasePopulation(a);
							return true;
						}
    				}
    			}
    			else
    			{
    				if(y1 <= (y2 + yBlock) && y1 >= y2)
    				{
    					if(s.getOwnedBy() == a.getOwnedBy())
    					{
    						s.increasePopulation();
    						return true;
    					}
    					else {
							s.decreasePopulation(a);
							return true;
						}
    				}
    			}
    		}
    		if(type == 4 || type == 9)
    		{
    			
    			int x1 = a.getPosX();
    			int x2 = s.getX();
    			if(type == 4)
    			{
    				if((x1 + 30) >= x2 && x1 <= (x2 + xBlock))
    				{
    					if(s.getOwnedBy() == a.getOwnedBy())
    					{
    						s.increasePopulation();
    						return true;
    					}
    					else {
							s.decreasePopulation(a);
							return true;
						}
    				}
    			}
    			else
    			{
    				if(x1 <= (x2 + xBlock) && x1 > x2)
    				{
    					
    					if(s.getOwnedBy() == a.getOwnedBy())
    					{
    						s.increasePopulation();
    						return true;
    					}
    					else {
							s.decreasePopulation(a);
							return true;
						}
    				}
    			}
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

