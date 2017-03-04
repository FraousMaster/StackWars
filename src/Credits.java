import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Global.Resources;

public class Credits extends JPanel {
	
	private JButton start;
	private JButton back;
	
	

    public Credits(){
    	setLayout(new GridBagLayout());
		buttons();	
    }

    private void buttons(){
    	JLabel x = new JLabel("We ");
    	JLabel z = new JLabel("are ");
    	JLabel a = new JLabel("awesome");
    	
    	
    	start = new JButton("Start");
		back = new JButton("Back");
		start.addActionListener(new Handler());
		back.addActionListener(new Handler());
		
		add(start);
		add(back);
    	
    	add(x);
    	add(z);
    	add(a);
    	
    	
    	
    }
    
	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
    
    public class Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == start){
				new Game();
			}
		
			else if(e.getSource() == back){
					remove();
					StartMenu menu = new StartMenu();
					add(menu);
				}
			}
		}
    
    
    
}
