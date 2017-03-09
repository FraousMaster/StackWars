package Global;
import java.io.*;
import java.util.ArrayList;


public class Resources {
	private static int width = 1920;
	private static int height = 1080;
	private static int scalingFactorX = width/20;
	private static int scalingFactorY = height/20;
	
	private static File setFile = new File("Settings\\file.xml");
	private static int myPlayerID;
	
	private static ArrayList<String> stacks = new ArrayList<>();
    private static ArrayList<String> allRoads = new ArrayList<>();
	private static String myStack = "";
	
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
	/*
	 * @param type
	 * Depending on the type of the map ant gets a different offset.
	 * This depends on the resolution chosen by the player. 
	 */
	public static int getAntXOffset(int type)
	{
		int temp = (scalingFactorX / 2) - 15;
		int xOffset = 0;
		switch(type) {
			case 3: xOffset = temp;
					break;
			case 4: xOffset = temp + scalingFactorY + 20;
					break;
			case 8: xOffset = temp;
					break;
			case 9: xOffset = -temp - scalingFactorY - 20;
					break;
		}
		return xOffset;
	}
	
	public static int getAntYOffset(int type)
	{
		int temp = (scalingFactorY / 2) - 10;
		int yOffset = 0;
		switch(type) {
			case 3: yOffset = temp + scalingFactorY - 20;
					break;
			case 4: yOffset = temp;
					break;
			case 8: yOffset = temp - scalingFactorY + 20;	
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
	
	public static File getXMLFile()
	{
		return setFile;
	}

	public static void setMyPlayerID(String x)
	{
		myPlayerID = Integer.parseInt(x);
	}

	public static int getMyPlayerID()
	{
		return myPlayerID;
	}
	
    public static void setAllRoads(ArrayList<String> list){
        allRoads = list;
    }
    
    public static ArrayList<String> getAllRoads()
    {
    	return allRoads;
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

