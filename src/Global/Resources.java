package Global;
import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Resources {
	private static int width = 1920;
	private static int height = 1080;
	private static int scalingFactorX = width/20;
	private static int scalingFactorY = height/20;
	
	private static File setFile = new File("Settings\\file.xml");
	
	private static String myPlayerID = null;
	
	private enum playerIDs{
		player_one,
	    player_two,
	    player_three,
	    player_four
    };
	    
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
	
	public static String getMyPlayerID()
	{
		return myPlayerID;
	}
	public static File getXMLFile()
	{
		return setFile;
	}
	
	
	public static void setWidth(int x)
	{
		width = x;
		scalingFactorX = width/20;
	}
	public static void setHeight(int y)
	{
		height = y;
		scalingFactorX = width/20;
	}
	
	public static void setMyPlayerID(String x)
	{
		if(x.equals("1"))
		{
			myPlayerID = "player_one";
		}
		if(x.equals("2"))
		{
			myPlayerID = "player_two";
		}
		if(x.equals("3"))
		{
			myPlayerID = "player_three";
		}
		if(x.equals("4"))
		{
			myPlayerID = "player_four";
		}
	}
}
