import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import Global.Resources;

public class StartMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton startButton;
	private JButton optionsButton;
	private JButton creditsButton;
	private JButton exitButton;
<<<<<<< HEAD

	public StartMenu(){
=======
	
	public StartMenu(){	
		
>>>>>>> Devel
		setLayout(new GridBagLayout());
		buttons();
	}

	private void buttons(){
		ImageIcon str = new ImageIcon("Graphics/Buttons/Start.png");
		ImageIcon opt = new ImageIcon("Graphics/Buttons/Options.png");
		ImageIcon cre = new ImageIcon("Graphics/Buttons/Credits.png");
		ImageIcon ext = new ImageIcon("Graphics/Buttons/EXIT.png");
		GridBagConstraints c = new GridBagConstraints();

		startButton = new JButton("Start");
		optionsButton = new JButton("Options");
		creditsButton = new JButton("Credits");
		exitButton = new JButton("Exit");
<<<<<<< HEAD

=======
		
		startButton.setIcon(str);
		optionsButton.setIcon(opt);
		creditsButton.setIcon(cre);
		exitButton.setIcon(ext);
		
		exitButton.setIconTextGap(-5);
		creditsButton.setIconTextGap(-5);
		optionsButton.setIconTextGap(-5);
		startButton.setIconTextGap(-5);
		
>>>>>>> Devel
		startButton.addActionListener(new Handler());
		optionsButton.addActionListener(new Handler());
		creditsButton.addActionListener(new Handler());
		exitButton.addActionListener(new Handler());
<<<<<<< HEAD

		startButton.setPreferredSize(new Dimension(100, 50));
		optionsButton.setPreferredSize(new Dimension(100, 50));
		creditsButton.setPreferredSize(new Dimension(100, 50));
		exitButton.setPreferredSize(new Dimension(100, 50));

=======
		
		startButton.setPreferredSize(new Dimension(100, 40));
		optionsButton.setPreferredSize(new Dimension(100, 40));
		creditsButton.setPreferredSize(new Dimension(100, 40));
		exitButton.setPreferredSize(new Dimension(100, 40));
		

		startButton.setBorder(new RoundedBorder(10)); //10 is the radius

		optionsButton.setBorder(new RoundedBorder(10)); //10 is the radius
	
		creditsButton.setBorder(new RoundedBorder(10)); //10 is the radius

		exitButton.setBorder(new RoundedBorder(10)); //10 is the radius
		
>>>>>>> Devel
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
				remove();
				Credits menu = new Credits();
				add(menu);
			}
			else if(e.getSource() == exitButton){
				System.exit(0);
			}
		}
<<<<<<< HEAD
	}
=======
}
>>>>>>> Devel

}