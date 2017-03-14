import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import Global.Resources;

/**
 * a menu Gui class for one of the many menus.
 * interacts with several other menu classes depending on what JButton has been pressed. 
 * @Author Linus Nilsson, Johannes Edenholm
 */
public class SettingsMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton applyButton;
	private JButton backButton;
	@SuppressWarnings("rawtypes")
	private JComboBox volume;
	private JComboBox resolution;
	private final String[] volSet = {"Volume","1","2","3","4","5"};
	private final String[] resSet = {"Default","1920x1080","1600x900","1360x768","1280x720"};
	private String VOL = "Volume";
	private String RES = "Default";
	/**
	 * Constructor for this class
	 */
	public SettingsMenu(){
		setLayout(new GridBagLayout());
		createGUI();	
	}
	
	/**
	 * Adds and creates GUI for the JPanel
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createGUI(){
		GridBagConstraints c = new GridBagConstraints();
		volume = new JComboBox(volSet);
		resolution = new JComboBox(resSet);
		applyButton = new JButton("Apply");
		backButton = new JButton("Back");
		
		volume.addActionListener(new Handler());
		resolution.addActionListener(new Handler());
		applyButton.addActionListener(new Handler());
		backButton.addActionListener(new Handler());
		volume.setPreferredSize(new Dimension(100, 50));
		resolution.setPreferredSize(new Dimension(100, 50));
		applyButton.setPreferredSize(new Dimension(100, 50));
		backButton.setPreferredSize(new Dimension(100, 50));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		add(volume, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		add(resolution, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(applyButton, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		add(backButton, c);
	}
	/**
	 * Returns the current resulution
	 * @return
	 */
	private String getRes(){
		if(RES.equals("Default")){
			RES = "1600x900";
		}
		return RES;
	}
	/**
	 * Returns the current volume
	 * @return
	 */
	private String getVol(){
		if(VOL.equals("Volume")){
			VOL = "3";
		}
		return VOL;
	}
	/**
	 * Removes the current GUI for this JPanel
	 */
	private void remove(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	/**
	 * Apply-button Creates a document of type XML and places the resolution and volume values in Elements.
	 * Handler class, takes care of actionEvents
	 */
	public class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backButton){
				remove();
				StartMenu menu = new StartMenu();
				add(menu);
			}
			else if(e.getSource() == volume ){
				VOL = volume.getSelectedItem().toString();
				
			}
			else if(e.getSource() == resolution){
				RES  = resolution.getSelectedItem().toString();
				
			}
			else if(e.getSource() == applyButton){
				try{
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.newDocument();
					Element root = document.createElement("Game");
					Element settings = document.createElement("settings");
					Element resolution = document.createElement("Resolution");
					Element volume = document.createElement("Volume");
					
					document.appendChild(root);
					root.appendChild(settings);
					settings.appendChild(volume);
					settings.appendChild(resolution);
					volume.appendChild(document.createTextNode(getVol()));
					resolution.appendChild(document.createTextNode(getRes()));
					TransformerFactory transFactory = TransformerFactory.newInstance();
					Transformer transformer = transFactory.newTransformer();
					DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(new File("Settings/file.xml"));
                    transformer.transform(source, result);
                    remove();
    				StartMenu menu = new StartMenu();
    				add(menu);
				}catch(ParserConfigurationException pce){
					pce.printStackTrace();
				}catch(TransformerException te){
					te.printStackTrace();
                }catch(Exception all){}
				
			}	
		}
	}
	
}
