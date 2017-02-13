import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Menu extends JFrame {
	
	private int menuState;
	private int previous;
	final PlayMenu playMenu = new PlayMenu();
	final StartMenu startMenu = new StartMenu();
	
	public Menu(){
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
