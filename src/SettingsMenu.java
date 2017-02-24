import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SettingsMenu extends JPanel {

<<<<<<< HEAD

	/**
	 * 
	 */
=======
>>>>>>> Devel
	private static final long serialVersionUID = 1L;
	private JButton backButton;
	@SuppressWarnings("rawtypes")
	private JComboBox volume;
	private final String[] volSet = {"Volume","1","2","3","4","5"};
	private String VOL;
	
	public SettingsMenu(){
		setLayout(new GridBagLayout());
		buttons();	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void buttons(){
		GridBagConstraints c = new GridBagConstraints();
		volume = new JComboBox(volSet);
		backButton = new JButton("Back");
		
		
		volume.addActionListener(new Handler());
		backButton.addActionListener(new Handler());
		volume.setPreferredSize(new Dimension(100, 50));
		backButton.setPreferredSize(new Dimension(100, 50));

		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(volume, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(backButton, c);
	}
	
	private void returnVol(String input){
		this.VOL = input;
		System.out.println(VOL);
	}
	
	private void remove(){
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
			else if(e.getSource() == volume ){
				String value = volume.getSelectedItem().toString();
				returnVol(value);
			
			}
				
		}
	}
	
}
