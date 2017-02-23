 import java.awt.*;
import java.awt.event.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import javax.swing.*;

public class LobbyMenu extends JPanel {
<<<<<<< HEAD
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
		
=======

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
	private boolean started = false;
	
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
<<<<<<< HEAD
		add(gameButton, c);

>>>>>>> 9b7b0c82e943d14319b62ef8609f8c30d4dbd02a
=======
		add(start, c);
		
>>>>>>> Devel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		add(back, c);	
<<<<<<< HEAD
	}
<<<<<<< HEAD
	public void update(){
		this.revalidate();
		this.repaint();
	}
	

	public void remove(){
=======

	private void returnIP(String input) {
		this.IP_ADDRESS = input;
		System.out.println(IP_ADDRESS);

	}

	private void returnName(String input) {
		this.NAME = input;
		System.out.println(NAME);
	}

	private void returnAnts(String input) {
		this.antNr = input;
		System.out.println(antNr);
=======
	}
	public void update(){
		this.revalidate();
		this.repaint();
>>>>>>> Devel
	}
	

<<<<<<< HEAD
	public void remove() {
>>>>>>> 9b7b0c82e943d14319b62ef8609f8c30d4dbd02a
=======
	public void remove(){
>>>>>>> Devel
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
<<<<<<< HEAD
=======

<<<<<<< HEAD
	public class Handler implements ActionListener {
>>>>>>> 9b7b0c82e943d14319b62ef8609f8c30d4dbd02a




	public class Handler implements ActionListener {
		GameMenu menu;
		@Override
		public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
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
				new Game();
			}
				
=======
			if (e.getSource() == backButton) {
				remove();
				PlayMenu menu = new PlayMenu();
				add(menu);
			} else if (e.getSource() == ipField) {
				String input = ipField.getText();
				returnIP(input);
			} else if (e.getSource() == nameField) {
				String input = nameField.getText();
				returnName(input);
			} else if (e.getSource() == antBox) {
				String value = antBox.getSelectedItem().toString();
				returnAnts(value);
=======
	public boolean startPressed(){
		started = true;
		return started;
	}
>>>>>>> Devel



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
			}
<<<<<<< HEAD
>>>>>>> 9b7b0c82e943d14319b62ef8609f8c30d4dbd02a
=======
				
>>>>>>> Devel
		}

	}
}
