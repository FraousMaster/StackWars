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
    private static HashMap<Integer, ArrayList<Point>>  mapMap = new HashMap<>();
	private static int myPlayerID;
	
	private static ArrayList<String> stacks = new ArrayList<>();
	private static String myStack = "";
	
	private enum playerIDs{
		player_one,
		player_two,
		player_three,
		player_four
    };
	    
	public Resources()
	{
		
	}

    public static HashMap<Integer, ArrayList<Point>> getRoads()
    {
        return mapMap;
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
	public static int getAntXOffset(int type)
	{
		int temp = (scalingFactorX / 2) - 15;
		int xOffset = 0;
		switch(type) {
			case 3: xOffset = temp;
					break;
			case 4: xOffset = temp + 20;
					break;
			case 8: xOffset = temp;
					break;
			case 9: xOffset = -temp;
					break;
		}
		return xOffset;
	}
	public static int getAntYOffset(int type)
	{
		int temp = (scalingFactorY / 2) - 10;
		int yOffset = 0;
		switch(type) {
			case 3: yOffset = temp + 15;
					break;
			case 4: yOffset = temp;
					break;
			case 8: yOffset = temp - 15;	
					break;
			case 9: yOffset = temp;
					break;
		}
		return yOffset;
	}
	
	public static void setResolution(int x, int y)
	{
		width = x;
		scalingFactorX = width/20;
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

        if(mapMap.isEmpty()){
            mapMap.put(0, tempOne);
        }
        for (i = 0; i < mapMap.size(); i++) {
            if (mapMap.get(i).equals(tempOne) || mapMap.get(i).equals(tempTwo)) {
                unique = false;
            }
        }

        if(unique) {
            mapMap.put(i, tempOne);
        }

    }
    
    public static void setAllStacks(ArrayList<String> stackString)
    {
    	stacks = stackString;
    }
    public static ArrayList<String> getAllStacks()
    {
    	return stacks;
    }

	public static void setMyStack(String s)
	{
		myStack = s;
	}
	public static String getMyStack()
	{
		return myStack;
	}
}

