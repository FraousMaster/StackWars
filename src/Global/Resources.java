package Global;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Resources {
	private static int width = 1000;
	private static int height = 1000;
	private static int scalingFactorX = width/20;
	private static int scalingFactorY = height/20;
	File settFile = new File("../Settings/file.xml");
	public Resources()
	{
		
	}
	
	public static int getWidth()
	{
		return width;
	}
	public static int getHeight()
	{
		return height;
	}
	public static int getScalingFactorX()
	{
		return scalingFactorX;
	}
	public static int getScalingFactorY()
	{
		return scalingFactorY;
	}
	public static void setWidth(int x)
	{
		width = x;
	}
	public static void setHeight(int y)
	{
		width = y;
	}
	
	private void readingSettings(){
        try{
            File settFile = new File("../Settings/file.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(settFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root Element:" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Game");
                Node node = nodeList.item(0);
                Element eElement = (Element) node;
                String temp[];
                String name = eElement.getElementsByTagName("resolution").item(0).getTextContent();
                temp = name.split("x");
                System.out.println("pls: " + Integer.parseInt(temp[0]));
        }catch(Exception filee){
            filee.printStackTrace();
        }
    }
}
