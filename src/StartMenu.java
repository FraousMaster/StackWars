import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class StartMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton startButton;
	private JButton optionsButton;
	private JButton creditsButton;
	private JButton exitButton;
	
	public StartMenu(){	
		setLayout(new GridBagLayout());
		buttons();		
	}
	
	private void buttons(){
		GridBagConstraints c = new GridBagConstraints();
		
		startButton = new JButton("Start");
		optionsButton = new JButton("Options");
		creditsButton = new JButton("Credits");
		exitButton = new JButton("Exit");
		
		startButton.addActionListener(new Handler());
		optionsButton.addActionListener(new Handler());
		creditsButton.addActionListener(new Handler());
		exitButton.addActionListener(new Handler());
		
		startButton.setPreferredSize(new Dimension(100, 50));
		optionsButton.setPreferredSize(new Dimension(100, 50));
		creditsButton.setPreferredSize(new Dimension(100, 50));
		exitButton.setPreferredSize(new Dimension(100, 50));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(startButton, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(optionsButton, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(creditsButton,c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(exitButton, c);
}
	
	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}

	public class Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == startButton){
				remove();
				PlayMenu menu = new PlayMenu();
				add(menu);
			}
			else if(e.getSource() == optionsButton){
				remove();
				SettingsMenu menu = new SettingsMenu();
				add(menu);
			}
			else if(e.getSource() == creditsButton){
<<<<<<< HEAD
				new Credits();
=======
				new Game();
>>>>>>> origin/JohannesBranchv2
			}
			else if(e.getSource() == exitButton){
					System.exit(0);
				}
			}
		}

	}



