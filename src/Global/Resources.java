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
	public static File getXMLFile()
	{
		return setFile;
	}
	
	
	public static void setWidth(int x)
	{
		width = x;
	}
	public static void setHeight(int y)
	{
		width = y;
	}
}
