import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import javax.swing.*;
import Global.Resources;

public class GameMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField ipField;
	private JTextField nameField;
	@SuppressWarnings("rawtypes")
	private JComboBox antBox;
	private JComboBox mapButton;
	private JButton gameButton;
	private JButton backButton;
	private JButton joinButton;
	private String IP_ADDRESS;
	private String NAME;
	private String antNr;
	private final String[] ants = {"Ants","5","6","7","8","9","10"};
	private final String[] maps = {"Maps","1","2","3","4","5"};
	private int goTo;


	
	public GameMenu(int to){
		this.goTo = to;
		
		setLayout(new GridBagLayout());
		settings();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void settings(){
		
		GridBagConstraints c = new GridBagConstraints();
		
		if(goTo == 1)
		ipField = new JTextField("Ip Address");
		
		nameField = new JTextField("Name");
		
		if(goTo == 0){
		antBox = new JComboBox(ants);
		mapButton = new JComboBox(maps);
		gameButton = new JButton("Create Game");
		}
		
		backButton = new JButton("Back");
		
		if(goTo == 1)
		joinButton = new JButton("Join");
		
		if(goTo == 0){
		antBox.addActionListener(new Handler());
		mapButton.addActionListener(new Handler());
		}
		
		if(goTo == 0)
		gameButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());
		
		if(goTo == 1){
		joinButton.addActionListener(new Handler());
		ipField.setPreferredSize(new Dimension(100, 50));
		}
		
		nameField.setPreferredSize(new Dimension(100, 50));
		
		if(goTo == 0){
		antBox.setPreferredSize(new Dimension(100, 50));
		mapButton.setPreferredSize(new Dimension(100, 50));
		gameButton.setPreferredSize(new Dimension(150, 50));
		}
		
		backButton.setPreferredSize(new Dimension(100, 50));
		
		if(goTo == 1)
		joinButton.setPreferredSize(new Dimension(100, 50));

		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		if(goTo == 1)
		add(ipField, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(nameField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		if(goTo == 0)
		add(antBox, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		if(goTo == 0)
		add(mapButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		if(goTo == 0)
		add(gameButton, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		add(backButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		if(goTo == 1)
		add(joinButton, c);
	}
	
	public String getName(){
		return NAME;
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
		LobbyMenu menu = new LobbyMenu(0, this );
		add(menu);
		
	}
	
    private void createClient() throws UnknownHostException{
    	LobbyMenu menu = new LobbyMenu(1, this );	
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


	

