import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SettingsMenu extends JPanel {

	private JButton AudioButton;
	private JButton backButton;
	
	public SettingsMenu(){
		setLayout(new GridBagLayout());
		buttons();	
	}
	
	private void buttons(){
		GridBagConstraints c = new GridBagConstraints();
		AudioButton = new JButton("Audio");
		backButton = new JButton("Back");
		
		AudioButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());
		AudioButton.setPreferredSize(new Dimension(100, 50));
		backButton.setPreferredSize(new Dimension(100, 50));

		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(AudioButton, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
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
			else if(e.getSource() == AudioButton ){
					
			
			}
				
		}
	}
	
}
