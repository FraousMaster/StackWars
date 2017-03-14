import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;
/**
 * 
 * @author Johannes Edenholm, Hugo Frost, Arvid Wiklund
 * a menu Gui class for one of the many menus.
 * interacts with several other menu classes depending on what JButton has been pressed. 
 *
 */
public class LobbyMenu extends JPanel {

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
	/**
	 * Constructor for this class
	 * from determines whether to create a server host and client or simply a Client.
	 */
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
		createGUI();
	}

	/**
	 * Gets arrayList of players from server
	 * @param players
	 */
	public void setPlayers(ArrayList<String> players){
		int i = 0;
		for(String x : players){
			setLabels(x, i);
			i++;
		}
	}
	/**
	 * Set player names to the labels, y determines which position.
	 * @param x
	 * @param y
	 */
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
	/**
	 * Adds and creates GUI for the JPanel
	 * Camefrom determines which GUI to create.
	 */
	private void createGUI(){
		
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
	/**
	 * Removes the current GUI for this JPanel
	 */
	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	/**
	 * Checks if start has been pressed
	 * @return
	 */
	public boolean startPressed(){
		return isPressed;
	}
	/**
	 * Starts a new game
	 */
	public void startGame(){	
		 game = new Game();
	}
	/**
	 * Returns the game object
	 * @return
	 */
	public Game returnGame(){
		return game;
	}
	/**
	 * Returns the stateobject of game.
	 * @return
	 */
	public GameState returnState(){
		return game.getState();
		
	}
	
	 /**
     * 
     * @author Johannes Edenholm
     * Handler class, takes care of actionEvents
     */
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
