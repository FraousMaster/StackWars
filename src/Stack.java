import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import Global.Resources;

public class Stack extends JComponent{

    private int population, stackPosX, stackPosY;
    private BufferedImage image;
    private HashMap<Point, ArrayList<Roads>> connectedStacks = new HashMap<>();

    public Stack(int posX, int posY){
        population = 10;
        stackPosX = posX;
        stackPosY = posY;   
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public int getX(){
        return stackPosX;
    }

    public int getY(){
        return stackPosY;
    }
    public int getPopulation()
    {
    	return population;
    }
    public void decreasePopulation()
    {
    	population -= 1;
    }
    public ArrayList<Roads> getConnectedStacks(int x, int y)
    {
    	
    	Point key = new Point(x,y);
    	//System.out.println(connectedStacks);
    	if(connectedStacks.get(key) != null)
		{
    		return connectedStacks.get(key);
		}
    	else
    	{
    		return null;
    	}
    }

    public void addPath(Point p, ArrayList<Roads> list)
    {
    	if(connectedStacks.containsKey(p))
      	   	connectedStacks.put(p, list);
    		
    	   	if(!connectedStacks.containsKey(p))
    	   		connectedStacks.put(p, list);
    	
    }
    public Dimension getPreferredSize() 
    {
        return new Dimension(image.getWidth(), image.getHeight());
    }
    public String testPrint(){
    	String s = new String();
    	s += "Size: " + connectedStacks.size();    	
    	return s;
    }
}