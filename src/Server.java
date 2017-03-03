import java.io.*;
import java.net.*;
import java.util.*;

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
    private ArrayList<Ant> ants = new ArrayList<>();
    private String temp = "";

    public Server() throws Exception {
        serverSocket = new DatagramSocket(1203);
        players = new LinkedList<>();
        ants = new ArrayList<>();

    }

    public void run() {
        System.out.println("Hello world");

        try {
            while (true) {
            	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                byte[] data = receivePacket.getData();
                messageReceived = new String(data, 0, receivePacket.getLength());
                IPAddress = receivePacket.getAddress();
                port = receivePacket.getPort();
                if(inLobby)
                {
                	currentModeInLobby();
                }
                else
                {
                	//System.out.println("in game running SERVER");
                	gameRunning();
                }
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            System.out.print(e);
        }
      

    }
    private void currentModeInLobby()
    {
        if(started){
           
            System.out.println("trying to go into game bitch");
            inLobby = false;
        }
        else if(messageReceived.equals("OK")) {
            started = true;
            sendData = "OK".getBytes();
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
                sendData = messageReceived.getBytes();
            }
        }
    	
    }

    private void gameRunning() {
        if(messageReceived.equals("OK")) {
            sendData = temp.getBytes();
           
        } else if (!(messageReceived.equals(players.getLast()) || messageReceived.equals("success") || messageReceived.equals("start") || messageReceived.equals("started?") || messageReceived.equals(null)
                || messageReceived == null || messageReceived.equals("OK"))){
            ants.add(new Ant(messageReceived));
            sendData = temp.getBytes();
           
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
                    sendData =temp.getBytes();
                }
                sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean check(String s) {
        boolean exists = false;
        for (Ant x : ants) {
            if (x.toString().equals(s)) {
                exists = true;
            }
        }
        return exists;

    }
}

