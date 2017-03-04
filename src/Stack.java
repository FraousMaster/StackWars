import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import Global.Resources;

public class Stack extends JComponent{

    private int population, stackPosX, stackPosY, ownedBy;
    private BufferedImage image;
    private HashMap<Point, ArrayList<Roads>> connectedStacks = new HashMap<>();
    private boolean checkFirst = true;
    
    public Stack(int posX, int posY){
        population = 5;
        stackPosX = posX;
        stackPosY = posY;
        ownedBy = 0;
    }
    
    public Stack(String s){
    	String b[] = s.split(":");
    	stackPosX = Integer.parseInt(b[0]);
    	stackPosY = Integer.parseInt(b[1]);
    	ownedBy = Integer.parseInt(b[2]);
    	population = Integer.parseInt(b[3]);
    	//System.out.println("building ANT");
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
    public int getOwnedBy()
    {
    	return ownedBy;
    }
    public void setOwnedBy()
    {
    	ownedBy = Resources.getMyPlayerID();
    }
    public void setOwnedBy(int x)
    {
    	
    	if(checkFirst)
    	{	
    		population += 5;
    		checkFirst = false;
	   	}
    	setOwnedBy();
    }
    public void decreasePopulation()
    {
    	population -= 1;
    }
    public void increasePopulation()
    {
    	population += 1;
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