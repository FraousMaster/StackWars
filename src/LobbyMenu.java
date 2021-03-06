import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;
import Global.Resources;

public class LobbyMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel p1;
	private JLabel p2;
	private JLabel p3;
	private JLabel p4;
	private JButton start;
	private JButton back;
	private int cameFrom;
	private Server server;
	private Client client;
	private GameMenu lMenu;
	private boolean isPressed = false;
	private Game game;
	private boolean connection = false;
	
	public LobbyMenu(int from, GameMenu menu) throws UnknownHostException{
		String IP = null;
		this.lMenu = menu;
		this.cameFrom = from;
		setLayout(new GridBagLayout());
		
		if(from == 0){ 
		try {
			IP = lMenu.returnIP();
			server = new Server();
			server.start();
			client = new Client(IP , menu.getName(), this);
			client.start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
		if(from == 1){
			IP = lMenu.returnIP();
			try {
				client = new Client(IP , menu.getName(), this);
			} catch (SocketException e) {
				e.printStackTrace();
			}
			client.start();
		
			
		}
		create();
	}

	
	public void setPlayers(ArrayList<String> players){
		int i = 0;
		for(String x : players){
			setLabels(x, i);
			i++;
		}
		
	}
	private void setLabels(String x, int y){
		
	    if(y == 0){
	    	p1.setText(x);
	    }
	    if(y == 1){
	    	p2.setText(x);
	    }
	    if(y == 2){
	    	p3.setText(x);
	    }
	    if(y == 3){
	    	p4.setText(x);
	    }
	}
	
	private void create(){
		
		ImageIcon crt = new ImageIcon("Graphics/Buttons/Start.png");
		ImageIcon bck = new ImageIcon("Graphics/Buttons/back.png");
		
		GridBagConstraints c = new GridBagConstraints();
		p1 = new JLabel("Waiting...");
		p2 = new JLabel("Waiting...");
		p3 = new JLabel("Waiting...");
		p4 = new JLabel("Waiting...");
		
		
		if(cameFrom == 0){
		start = new JButton("Start");
		start.setIcon(crt);
		start.setIconTextGap(-6);	
		}
		
		back = new JButton("Back");
		back.setIcon(bck);
		
		
		if(cameFrom == 0)
		start.addActionListener(new Handler());
		
		back.addActionListener(new Handler());
		
		if(cameFrom == 0)
		start.setPreferredSize(new Dimension(100, 40));
		back.setPreferredSize(new Dimension(100, 40));
		back.setIconTextGap(-6);	
	
		p1.setPreferredSize(new Dimension(100, 50));
		p2.setPreferredSize(new Dimension(100, 50));
		p3.setPreferredSize(new Dimension(100, 50));
		p4.setPreferredSize(new Dimension(100, 50));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(p1, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(p2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(p3, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(p4, c);
		
		if(cameFrom == 0){
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		add(start, c);
		}
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		add(back, c);	
	}
	
	
	public void update(){
		this.revalidate();
		this.repaint();
	}
	

	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	public boolean startPressed(){
		return isPressed;
		
	}
	
	public void startGame(){
		System.out.println("STARTING GAME");
		
		 game = new Game();
		 
	}
	
	public Game returnGame(){
		return game;
	}
	
	public GameState returnState(){
		return game.getState();
		
	}
	
	public boolean closeConnection(){
		return connection;
		
		
	}
	
	public class Handler implements ActionListener {
		GameMenu menu;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == back){
				remove();
				if(cameFrom == 0){
				 menu = new GameMenu(0);
				}
				else{
					System.out.println("back pressed");
				 menu = new GameMenu(1);
				 connection = true;
				}
				add(menu);	
			}
			else if(e.getSource() == start)
			{
				isPressed = true;
			}
				
		}

	}
}
