package Global;
import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Resources {
	private static int width = 1920;
	private static int height = 1080;
	private static int scalingFactorX = width/20;
	private static int scalingFactorY = height/20;
	
	private static File setFile = new File("Settings\\file.xml");
    private static HashMap<Integer, ArrayList<Point>> mapMap = new HashMap<>();
	private static int myPlayerID;
	
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
	
	public static int getMyPlayerID()
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
		scalingFactorY = height/20;
	}
	
	public static void setMyPlayerID(String x)
	{
		//System.out.println(x);;
		myPlayerID = Integer.parseInt(x);
		/*if(x.equals("1"))
		{
			myPlayerID = playerIDs.player_one;
		}
		if(x.equals("2"))
		{
			myPlayerID = playerIDs.player_one;
		}
		if(x.equals("3"))
		{
			myPlayerID = playerIDs.player_three;
		}
		if(x.equals("4"))
		{
			myPlayerID = playerIDs.player_four;
		}*/
	}
    public static void setAllRoads(Point start, Point end){
        int i;
        boolean unique = true;
        ArrayList<Point> tempOne = new ArrayList<>();
        ArrayList<Point> tempTwo = new ArrayList<>();
        tempOne.add(start);
        tempOne.add(end);
        tempTwo.add(end);
        tempTwo.add(start);

        for(i = 0; i <= mapMap.size(); i ++){
            if(mapMap.get(i).equals(tempOne) || mapMap.get(i).equals(tempTwo)){
                unique = false;
            }
        }
        if(unique)
            mapMap.put(i, tempOne);

    }

}
