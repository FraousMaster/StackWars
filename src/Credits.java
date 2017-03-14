import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Global.Resources;


/**
 * Creates credit class for menu
 * @author Johannes
 *
 */
public class Credits extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton start;
	private JButton back;
	
	

    public Credits(){
    	setLayout(new GridBagLayout());
    	createGUI();	
    }

    /**
     * Creates Gui for this class, does not 
     * really do anything usefull at the moment, you can quickstart the game here.
     */
    private void createGUI(){
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
    /**
     * removes the current Gui.
     */
	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
    
	/**
	 * Handler class for buttons
	 * @author Johannes
	 *
	 */
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
