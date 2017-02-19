import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.*;

public class LobbyMenu extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField ipField;
	private JTextField nameField;
	@SuppressWarnings("rawtypes")
	private JComboBox antBox;
	private JButton mapButton;
	private JButton gameButton;
	private JButton backButton;
	private JButton joinButton;
	private String IP_ADDRESS;
	private String NAME;
	private String antNr;
	private final String[] ants = {"Ants","5","6","7","8","9","10"};


	
	public LobbyMenu(int to){
		setLayout(new GridBagLayout());
		if(to == 0){
		hostSettings();
		}
		else{
		joinSettings();
		
		}
	}

	public String getName(){
		return NAME;
	}
	
	private void joinSettings(){
		GridBagConstraints c = new GridBagConstraints();
		ipField = new JTextField("Ip Address");
		nameField = new JTextField("Name");
		joinButton = new JButton("Join");
		backButton = new JButton("Back");
		
		ipField.addActionListener(new Handler());
		nameField.addActionListener(new Handler());
		joinButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());
		
		ipField.setPreferredSize(new Dimension(100, 50));
		nameField.setPreferredSize(new Dimension(100, 50));
		joinButton.setPreferredSize(new Dimension(100, 50));
		backButton.setPreferredSize(new Dimension(100, 50));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(ipField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(nameField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(joinButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(backButton, c);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void hostSettings(){
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		ipField = new JTextField("Ip Address");
		nameField = new JTextField("Name");
		antBox = new JComboBox(ants);
		mapButton = new JButton("Map");
		gameButton = new JButton("Create Game");
		backButton = new JButton("Back");
		
		antBox.addActionListener(new Handler());
		mapButton.addActionListener(new Handler());
		gameButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());
		
		ipField.setPreferredSize(new Dimension(100, 50));
		nameField.setPreferredSize(new Dimension(100, 50));
		antBox.setPreferredSize(new Dimension(100, 50));
		mapButton.setPreferredSize(new Dimension(100, 50));
		gameButton.setPreferredSize(new Dimension(150, 50));
		backButton.setPreferredSize(new Dimension(100, 50));

		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(ipField, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(nameField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(antBox, c);
		
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
	
	private void setIP(String input){
		this.IP_ADDRESS = input;
	}
	
	public String returnIP(){
		return IP_ADDRESS;
	}
	
	private void returnName(String input){
		this.NAME = input;
		
	}
	
	private void returnAnts(String input){
		this.antNr = input;
	}
	
	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	private void createHost() throws UnknownHostException{
		WaitMenu menu = new WaitMenu(0, this );
		add(menu);
		
	}
	
    private void createClient() throws UnknownHostException{
    	WaitMenu menu = new WaitMenu(1, this );	
		add(menu);
		
	}
	
	public class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backButton){
				remove();
				PlayMenu menu = new PlayMenu();
				add(menu);
			}
			else if(e.getSource() == antBox ){
				String value = antBox.getSelectedItem().toString();
				returnAnts(value);	
			}	
			else if(e.getSource() == gameButton ){
				String input2 = nameField.getText();
				returnName(input2);
				remove();
				try {
					createHost();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
			else if(e.getSource() == joinButton ){
				String input2 = nameField.getText();
				returnName(input2);
				String input = ipField.getText();
				setIP(input);
				remove();
				try {
					createClient();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
				
		
			
			
		}
	}
}


	

