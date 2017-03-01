import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ant {
	/**
	 * 
	 */
	
	public enum Player{
        playerOne,
        playerTwo,
        playerThree,
        PlayerFour
    }

    private int posX, posY, currentMapObject, previousMapObject, ownedBy;
    private ArrayList<Roads> road;
    //double speedX, speedY, angle;
    //private BufferedImage image;
    double speedX, speedY, angle;
    
    public Ant(int x, int y, int owns, int currentMapObject, ArrayList<Roads> road){
        posX = x;
        posY = y;
        this.road = road;
        ownedBy = owns;
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
    public int getOwnedBy(){
        return ownedBy;
        
    }
    public int getCurrentMapObject(){
        return currentMapObject;
    }
    public void setPos(int x, int y){
        posY = y;
        posX = x;
    }
    public void setCurrentMapObject(int newMapObject){
        previousMapObject = currentMapObject;
        currentMapObject = newMapObject;
    }
}
