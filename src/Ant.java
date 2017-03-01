import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Ant implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	public enum Player{
        playerOne,
        playerTwo,
        playerThree,
        PlayerFour
    }

    private int posX, posY, currentMapObject, previousMapObject;
    double speedX, speedY, angle;
    private int ownedBy;
    private BufferedImage image;
    private ArrayList<BufferedImage> imageSelection;
    
    public Ant(int x, int y, int owns, int currentMapObject, double speedX, double speedY, double angle){
        posX = x;
        posY = y;
        ownedBy = owns;
        this.speedX = speedX;
        this.speedY = speedY;
        this.angle = angle;
        this.currentMapObject = currentMapObject;
    }
    
    public Ant(String s){
    	String b[] = s.split(":");
    	posX = Integer.parseInt(b[0]);
    	posY = Integer.parseInt(b[1]);
    	ownedBy = Integer.parseInt(b[2]);
    	currentMapObject = Integer.parseInt(b[3]);
    }
    
    public String toString(){
    	return getPosX() + ":" + getPosY() + ":" + getOwnedBy() + ":" + getCurrentMapObject();
    }
    
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public double getSpeedX() {
        return speedX;
    }
    public double getSpeedY() {
        return speedY;
    }
    public int getOwnedBy(){
        return ownedBy;
        
    }
    public double getAngle(){
        return angle;
    }
    public int getCurrentMapObject(){
        return currentMapObject;
    }
    public void setPos(int x, int y){
        posY = y;
        posX = x;
    }
    public void setSpeedX(int speedX){
        
        this.speedX = speedX;
    }
    public void setSpeedY(int speedY){
        
        this.speedY = speedY;
    }
    public void setCurrentMapObject(int newMapObject){
        previousMapObject = currentMapObject;
        currentMapObject = newMapObject;
    }
}
