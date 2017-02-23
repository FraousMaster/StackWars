 import java.awt.*;
import java.awt.event.*;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.swing.*;

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
<<<<<<< HEAD
	private boolean started = false;
=======
	private boolean isPressed = false;
>>>>>>> origin/JohannesBranchv2
	
	public LobbyMenu(int from, GameMenu menu) throws UnknownHostException{
		String IP = null;
		this.lMenu = menu;
		this.cameFrom = from;
		setLayout(new GridBagLayout());
		create();
		
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
	}

	
	public void setPlayers(String name){
		if(p1.getText().equals("Waiting...")){
			p1.setText(name);	
		}

	    else if(p2.getText().equals("Waiting...")){
			p2.setText(name);	
		}

	  else if(p3.getText().equals("Waiting...")){
			p3.setText(name);	
		}
	else if(p4.getText().equals("Waiting...")){
			p4.setText(name);	
		}
	}
	
	private void create(){
		GridBagConstraints c = new GridBagConstraints();
		p1 = new JLabel("Waiting...");
		p2 = new JLabel("Waiting...");
		p3 = new JLabel("Waiting...");
		p4 = new JLabel("Waiting...");
		start = new JButton("Start");
		back = new JButton("Back");
		
		start.addActionListener(new Handler());
		back.addActionListener(new Handler());
		
		start.setPreferredSize(new Dimension(100, 50));
		back.setPreferredSize(new Dimension(100, 50));
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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		add(start, c);
		
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
		isPressed = true;
	
		System.out.println(isPressed);
		return isPressed;
		
	}

	public boolean startPressed(){
		started = true;
		return started;
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
				 menu = new GameMenu(1);
				}
				add(menu);	
			}
			else if(e.getSource() == start)
			{
				startPressed();
<<<<<<< HEAD
=======
				try {
					client.sendData("start");
				} catch (SocketTimeoutException | SocketException | UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
>>>>>>> origin/JohannesBranchv2
			}
				
		}

	}
}
