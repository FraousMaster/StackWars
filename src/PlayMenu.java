import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayMenu extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton joinButton;
	private JButton hostButton;
	private JButton backButton;
	
	public PlayMenu(){
		setLayout(new GridBagLayout());
		buttons();	
	}
	
	private void buttons(){
		GridBagConstraints c = new GridBagConstraints();
		joinButton = new JButton("Join");
		hostButton = new JButton("Host");
		backButton = new JButton("back");
		
		joinButton.addActionListener(new Handler());
		hostButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());

		joinButton.setPreferredSize(new Dimension(100, 50));
		hostButton.setPreferredSize(new Dimension(100, 50));
		backButton.setPreferredSize(new Dimension(100, 50));

		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(joinButton, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(hostButton, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
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
				StartMenu menu = new StartMenu();
				add(menu);
			}
			else if(e.getSource() == joinButton ){
				remove();
				LobbyMenu menu = new LobbyMenu(1);
				add(menu);	
			
			}
			else if(e.getSource() == hostButton ){
				remove();
				LobbyMenu menu = new LobbyMenu(0);
				add(menu);	
				
			}
				
		}
	}
}

