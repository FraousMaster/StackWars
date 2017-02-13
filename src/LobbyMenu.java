import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LobbyMenu extends JPanel {

	private JButton ipButton;
	private JButton nameButton;
	private JButton antButton;
	private JButton mapButton;
	private JButton gameButton;
	private JButton backButton;
	private JButton joinButton;

	
	public LobbyMenu(int x){
		setLayout(new GridBagLayout());
		if(x == 0){
		settings();
		}
		else{
		joinSettings();
		}
	}
	private void joinSettings(){
		GridBagConstraints c = new GridBagConstraints();
		ipButton = new JButton("Ip Address");
		nameButton = new JButton("Name");
		joinButton = new JButton("Join");
		backButton = new JButton("Back");
		
		ipButton.addActionListener(new Handler());
		nameButton.addActionListener(new Handler());
		joinButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());
		
		ipButton.setPreferredSize(new Dimension(100, 50));
		nameButton.setPreferredSize(new Dimension(100, 50));
		joinButton.setPreferredSize(new Dimension(100, 50));
		backButton.setPreferredSize(new Dimension(100, 50));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(ipButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(nameButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(joinButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(backButton, c);
	}
	
	private void settings(){
		GridBagConstraints c = new GridBagConstraints();
		
		ipButton = new JButton("Ip Address");
		nameButton = new JButton("Name");
		antButton = new JButton("Ants");
		mapButton = new JButton("Map");
		gameButton = new JButton("Create Game");
		backButton = new JButton("Back");
		
		ipButton.addActionListener(new Handler());
		nameButton.addActionListener(new Handler());
		antButton.addActionListener(new Handler());
		mapButton.addActionListener(new Handler());
		gameButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());
		
		ipButton.setPreferredSize(new Dimension(100, 50));
		nameButton.setPreferredSize(new Dimension(100, 50));
		antButton.setPreferredSize(new Dimension(100, 50));
		mapButton.setPreferredSize(new Dimension(100, 50));
		gameButton.setPreferredSize(new Dimension(150, 50));
		backButton.setPreferredSize(new Dimension(100, 50));

		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(ipButton, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(nameButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(antButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(mapButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		add(gameButton, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		add(backButton, c);
	}
	
	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	public class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backButton){
				remove();
				PlayMenu menu = new PlayMenu();
				add(menu);
			}
			else if(e.getSource() == gameButton ){
					
			
			}
			else if(e.getSource() == antButton ){
				
				
			}
			else if(e.getSource() == antButton ){
				
				
			}	
			else if(e.getSource() == antButton ){
				
				
			}	
			else if(e.getSource() == antButton ){
				
				
			}
				
				
		}
	}
	
}
