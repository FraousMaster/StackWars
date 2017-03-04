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
	private JButton backButton1;
	private JButton backButton2;
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
		
		ImageIcon crt = new ImageIcon("Graphics/Buttons/creategame.png");
		ImageIcon bck = new ImageIcon("Graphics/Buttons/bigBack.png");
		ImageIcon jin = new ImageIcon("Graphics/Buttons/join.png");
		ImageIcon sbck = new ImageIcon("Graphics/Buttons/back.png");
		
		GridBagConstraints c = new GridBagConstraints();
		
		if(goTo == 1){
		ipField = new JTextField("Ip Address");
		joinButton = new JButton("Join");
		joinButton.setIcon(jin);
		joinButton.setIconTextGap(-5);
		backButton1 = new JButton("Back");
		backButton1.setIcon(sbck);
		backButton1.setIconTextGap(-5);
		backButton1.setPreferredSize(new Dimension(100, 40));
		backButton1.addActionListener(new Handler());
		joinButton.addActionListener(new Handler());
		ipField.setPreferredSize(new Dimension(100, 50));
		joinButton.setPreferredSize(new Dimension(100, 40));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		if(goTo == 1)
		add(joinButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		add(backButton1, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		if(goTo == 1)
		add(ipField, c);
		
		}
		
		if(goTo == 0){
		antBox = new JComboBox(ants);
		mapButton = new JComboBox(maps);
		gameButton = new JButton("Create Game");
		gameButton.setIcon(crt);
		gameButton.setIconTextGap(-6);	
		backButton2 = new JButton("Back");
		backButton2.setIcon(bck);
		backButton2.setIconTextGap(-5);
		backButton2.setPreferredSize(new Dimension(150, 40));
		antBox.addActionListener(new Handler());
		mapButton.addActionListener(new Handler());
		gameButton.addActionListener(new Handler());
		backButton2.addActionListener(new Handler());
		antBox.setPreferredSize(new Dimension(100, 40));
		mapButton.setPreferredSize(new Dimension(100, 40));
		gameButton.setPreferredSize(new Dimension(150, 40));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		add(backButton2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		if(goTo == 0)
		add(gameButton, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		if(goTo == 0)
		add(mapButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		if(goTo == 0)
		add(antBox, c);
		}
		
		nameField = new JTextField("Name");
		nameField.setPreferredSize(new Dimension(100, 50));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(nameField, c);
		
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
			if(e.getSource() == backButton1 || e.getSource() == backButton2){
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


	

