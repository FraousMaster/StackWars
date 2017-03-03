package Global;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Resources {
	private static int width = 1000;
	private static int height = 1000;
	private static int scalingFactorX = width/20;
	private static int scalingFactorY = height/20;
	File settFile = new File("../Settings/file.xml");
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
	public static void setWidth(int x)
	{
		width = x;
	}
	public static void setHeight(int y)
	{
		width = y;
	}
}
