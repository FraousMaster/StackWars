import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Stack extends JComponent{

    private int population, stackPosX, stackPosY;
    private BufferedImage image;
    private HashMap<Point, ArrayList<Roads>> connectedStacks = new HashMap<>();

    public Stack(int posX, int posY){
        population = 10;
        stackPosX = posX;
        stackPosY = posY;
        try
        {
            image = ImageIO.read(new File("Graphics\\Stack.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
     
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
    public ArrayList<Roads> getConnectedStacks(int x, int y)
    {
    	Point key = new Point();
    	key.setLocation(x, y);
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
        connectedStacks.put(p, list);
    }
    public Dimension getPreferredSize() 
    {
        return new Dimension(image.getWidth(), image.getHeight());
    }
}
