import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Global.Resources;

public class Credits extends JPanel {

    public Credits(){
    	setLayout(new GridBagLayout());
		buttons();	
    }

    private void buttons(){
    	JLabel x = new JLabel("We ");
    	JLabel z = new JLabel("are ");
    	JLabel a = new JLabel("awesome");
    	
    	add(x);
    	add(z);
    	add(a);
    	
    	
    	
    }
    
}
