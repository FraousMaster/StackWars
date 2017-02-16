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
	private String HOSTish = null;
	private String p2name ="Waiting...";
	private String p3name ="Waiting...";
	private String p4name ="Waiting...";
	private JButton start;
	private JButton back;
	private int cameFrom;
	private Server server;
	private Client client;
	private LobbyMenu lMenu;
	
	public WaitMenu(int from, String host, LobbyMenu menu){
		this.lMenu = menu;
		this.HOSTish = host;
		this.cameFrom = from;
		setLayout(new GridBagLayout());
		create();
		
		if(from == 0){
		try {
			server = new Server(HOSTish, this);
			server.start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
		if(from == 1){
			String IP = null;
			IP = lMenu.returnIP();
			client = new Client(IP , HOSTish, this);
			client.run();
			
		}
	
	}
	public String getHOST(){
		return HOSTish;
	}
	
	public void setHOST(String name){
		p1.setText(name);
		
	}
	public void setNames(String name){
		if(p1.equals(null)){
			p1.setText(name);
		}
		if(p2.equals("Waiting...")){
			p2.setText(name);
			 
		 }
	}
	
	public void setP2(String py2){
		p2.setText(py2);
	}
	public void setP3(String p3){
		this.p3name = p3;
	}
	public void setP4(String p4){
		this.p4name = p4;
	}


	
	private void create(){
		GridBagConstraints c = new GridBagConstraints();
		p1 = new JLabel(HOSTish);
		p2 = new JLabel(p2name);
		p3 = new JLabel(p3name);
		p4 = new JLabel(p4name);
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
	public void update(){
		this.revalidate();
		this.repaint();
	}
	

	public void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}


	public void setP2name(String p2name) {
		this.p2name = p2name;
	}
	public void setP3name(String p3name) {
		this.p3name = p3name;
	}
	public void setP4name(String p4name) {
		this.p4name = p4name;
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
				
		}

	}
}
