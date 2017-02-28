import javax.swing.*;


public class Menu extends JFrame {
	

	private static final long serialVersionUID = 1L;
	final StartMenu startMenu;
	
	public Menu(){
		startMenu = new StartMenu();
		frame();
		add(startMenu);
	}
	private void frame(){
		 setTitle("Stack Wars!");
	     setLocation(10, 200);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setSize(600,600);
	     setVisible(true);
	} 
	
	
}
