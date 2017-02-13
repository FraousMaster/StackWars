 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WaitMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel p1;
	private JLabel p2;
	private JLabel p3;
	private JLabel p4;
	private JButton start;
	private JButton back;
	private int cameFrom;
	
	public WaitMenu(int from){
		this.cameFrom = from;
		setLayout(new GridBagLayout());
		create();
		
	}
	
	private void create(){
		GridBagConstraints c = new GridBagConstraints();
		p1 = new JLabel("You...");
		p2 = new JLabel("Waiting...");
		p3 = new JLabel("Waiting...");
		p4 = new JLabel("Waiting...");
		start = new JButton("Start");
		back = new JButton("Back");
		
		start.addActionListener(new Handler());
		back.addActionListener(new Handler());
		
		start.setPreferredSize(new Dimension(100, 50));
		back.setPreferredSize(new Dimension(100, 50));
		p1.setPreferredSize(new Dimension(100, 50));
		p2.setPreferredSize(new Dimension(100, 50));
		p3.setPreferredSize(new Dimension(100, 50));
		p4.setPreferredSize(new Dimension(100, 50));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(p1, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(p2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(p3, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(p4, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		add(start, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		add(back, c);
		
		
		
	}
	

	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	public class Handler implements ActionListener {
		LobbyMenu menu;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == back){
				remove();
				if(cameFrom == 0){
				 menu = new LobbyMenu(0);
				}
				else{
				 menu = new LobbyMenu(1);
				}
				add(menu);	
			}
			else if(e.getSource() == start)
			{
				new Game();
			}

		}

	}
}
