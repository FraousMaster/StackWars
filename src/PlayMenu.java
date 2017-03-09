import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Global.Resources;

public class PlayMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton joinButton;
	private JButton hostButton;
	private JButton backButton;
	/**
	 * Constructor for this class
	 */
	public PlayMenu(){
		setLayout(new GridBagLayout());
		createGUI();	
	}
	/**
	 * Adds and creates GUI for the JPanel
	 */
	private void createGUI(){
		ImageIcon str = new ImageIcon("Graphics/Buttons/join.png");
		ImageIcon opt = new ImageIcon("Graphics/Buttons/Host.png");
		ImageIcon cre = new ImageIcon("Graphics/Buttons/back.png");
		
		
		GridBagConstraints c = new GridBagConstraints();
		joinButton = new JButton("Join");
		hostButton = new JButton("Host");
		backButton = new JButton("Back");
		
		joinButton.setIcon(str);
		hostButton.setIcon(opt);
		backButton.setIcon(cre);
		
		joinButton.setIconTextGap(-6);
		hostButton.setIconTextGap(-6);
		backButton.setIconTextGap(-6);
		
		joinButton.addActionListener(new Handler());
		hostButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());

		joinButton.setPreferredSize(new Dimension(100, 40));
		hostButton.setPreferredSize(new Dimension(100, 40));
		backButton.setPreferredSize(new Dimension(100, 40));

		
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
	/**
	 * Removes the current GUI for this JPanel
	 */
	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	 
	/**
     * 
     * @author Johannes Edenholm
     * Handler class, takes care of actionEvents
     */
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
				GameMenu menu = new GameMenu(1);
				add(menu);	
			
			}
			else if(e.getSource() == hostButton ){
				remove();
				GameMenu menu = new GameMenu(0);
				add(menu);	
				
			}
				
		}
	}
}

