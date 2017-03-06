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
    private boolean selected = false;
    private boolean gotRallyPoint = false;
    private Stack rallyPoint;
    
    public Stack(int posX, int posY){
        population = 5;
        stackPosX = posX;
        stackPosY = posY;
        ownedBy = 0;
    }
    
    public String toString(){
    	return stackPosX + ":" + stackPosY + ":" + ownedBy + ":" + population;
    }
    
    public String updateToString(){
    	return stackPosX + ":" + stackPosY + ":" + rallyPoint.getX() + ":" + rallyPoint.getY();
    }
        
    public Stack(String s){
    	String b[] = s.split(":");
    	stackPosX = Integer.parseInt(b[0]);
    	stackPosY = Integer.parseInt(b[1]);
    	ownedBy = Integer.parseInt(b[2]);
    	population = Integer.parseInt(b[3]);
    	//System.out.println("building ANT" + b[1]);
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
    	this.rallyPoint = rallyPoint;
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
    	}
    	else
    		population -= 1;
    }
    public void increasePopulation()
    {
    	if(gotRallyPoint)
    	{
    		rallyPoint.increasePopulation();
    	}
    	else
    		population += 1;
    }
    
    public void setPopulation(int x)
    {
    	population = x;
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
    public boolean isSelected(){
        return selected;
    }
    public void setSelected(boolean b){
        selected = b;
    }
}