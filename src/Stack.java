import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import Global.Resources;
/**
 * @Authors Linus Nilsson and Arvid Wiklund
 * This is the ant stack object. It has the position, population and owner
 * of the stack. It also has a rallyPoint which can be activated and it knows
 * the position and way to the connected stacks. It has a lot of get and set methods.
 */
public class Stack extends JComponent{

    private int population, stackPosX, stackPosY, ownedBy;
    private BufferedImage image;
    private HashMap<Point, ArrayList<Roads>> connectedStacks = new HashMap<>();
    private boolean checkFirst = true;
    private boolean selected = false;
    private boolean gotRallyPoint = false;
    private Stack rallyPoint;
    
    public Stack(int posX, int posY){
        population = 5;
        stackPosX = posX;
        stackPosY = posY;
        ownedBy = 0;
    }
    
    /**
     * assembles the stacks so they can be sent over the internet to server.
     */
    public String toString(){
    	return stackPosX + ":" + stackPosY + ":" + ownedBy + ":" + population;
    }
    
    /**
     * Helps with the implementation of rally points in the game.
     * @return
     */
    public String updateToString(){
    	if(gotRallyPoint)
    		return stackPosX + ":" + stackPosY + ":" + rallyPoint.getX() + ":" + rallyPoint.getY();
    	
    	else
    		return stackPosX + ":" + stackPosY + ":" + stackPosX + ":" + stackPosY;
    }
        
    /**
     * rebuilds the stacks when sent to the server from the client.
     * @param s
     */
    public Stack(String s){
    	String b[] = s.split(":");
    	stackPosX = Integer.parseInt(b[0]);
    	stackPosY = Integer.parseInt(b[1]);
    	ownedBy = Integer.parseInt(b[2]);
    	population = Integer.parseInt(b[3]);
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
    public Stack getRallyPoint()
    {
    	if(gotRallyPoint)
    	{
    		return rallyPoint;
    	}
    	return null;
    }
    public void setOwnedBy()
    {
    	if(checkFirst)
    	{	
    		population += 5;
    		checkFirst = false;
	   	}
    	ownedBy = Resources.getMyPlayerID();
    }
    public void setOwnedBy(int x)
    {
    	ownedBy = x;
    }
    
    public void setOwnedByEnemy(String s)
    {
    	Stack stack = new Stack(s);
    	this.ownedBy = stack.getOwnedBy();
    }
    
    public void setRallyPoint(Stack rallyPoint)
    {
    	gotRallyPoint = true;
    	if(this.equals(rallyPoint))
    	{
    		this.rallyPoint = rallyPoint;
    		gotRallyPoint = false;
    	}
    	else
    	{
    		this.rallyPoint = rallyPoint;
    	}
    }
    
    public void removeRallyPoint()
    {
    	rallyPoint = null;
    }
    public boolean checkIfRallyPoint()
    {
    	return gotRallyPoint;
    }
    
    public void decreasePopulation()
    {
    	population -= 1;
    }
    
    public void decreasePopulation(Ant a)
    {
    	if(population <= 0)
    	{
    		setOwnedBy(a.getOwnedBy());
    		setRallyPoint(this);
    	}
    	else
    		population -= 1;
    }
    public void increasePopulation()
    {
		population += 1;
    }
    
    public void setPopulation(int x)
    {
    	population = x;
    }
    
    public ArrayList<Roads> getConnectedStacks(int x, int y)
    {
    	Point key = new Point(x,y);
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
    public boolean isSelected(){
        return selected;
    }
    public void setSelected(boolean b){
        selected = b;
    }
}