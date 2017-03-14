import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * a menu Gui class for one of the many menus.
 * Also creates the JFrame for all menus.
 * @author Johannes Edenholm
 *
 */
public class Menu extends JFrame {
	

	private static final long serialVersionUID = 1L;
	final StartMenu startMenu;
	/**
	 * Constructor for this class
	 */
	public Menu(){
		startMenu = new StartMenu();
		createGUI();
		add(startMenu);
	}
	/**
	 * Creates configurations for the frame
	 */
	private void createGUI(){
	
		try {
			BufferedImage Image =  ImageIO.read(new File("Graphics/Ant/ANTBILD.png"));
			 setTitle("Stack Wars!");
			 setContentPane(new JLabel(new ImageIcon(Image)));
			 setLayout(new GridBagLayout());
			 GridBagConstraints gbc = new GridBagConstraints();
			 gbc.gridwidth = GridBagConstraints.REMAINDER;
		     setLocation(10, 200);
		     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		     setSize(599,599);
		     setVisible(true);
	         setSize(600,600);

		} catch (IOException e) {

			e.printStackTrace();
		}	
	}
	
	
}
