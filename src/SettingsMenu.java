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
	
	public SettingsMenu(){
		setLayout(new GridBagLayout());
		buttons();	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void buttons(){
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
	
	private void volValue(String value){
		this.VOL = value;
		System.out.println(VOL);
	}
	private void resValue(String value){
		this.RES = value;
		System.out.println(RES);
	}
	private String getRes(){
		if(RES.equals("Default")){
			RES = "1920x1080";
		}
		return RES;
	}
	private String getVol(){
		if(VOL.equals("Volume")){
			VOL = "3";
		}
		return VOL;
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
				volValue(value);
			}
			else if(e.getSource() == resolution){
				String value  = resolution.getSelectedItem().toString();
				resValue(value);
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
                    StreamResult result = new StreamResult(new File("../Settings/file.xml"));
                    transformer.transform(source, result);
					
					
					
					
				}catch(ParserConfigurationException pce){
					pce.printStackTrace();
				}catch(TransformerException te){
					te.printStackTrace();
                }catch(Exception all){}
				
			}	
		}
	}
	
}
