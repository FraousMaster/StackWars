import java.util.ArrayList;
import Global.Resources;

/**
 * This class creates all the ants that are used in the server, for the clients to see. Interacts with stack.
 * @author Arvid Wiklund, Linus Nilsson
 */
public class Ant {

	public enum Player{
        playerOne,
        playerTwo,
        playerThree,
        PlayerFour
    }

    private int posX, posY, currentMapObject, previousMapObject, ownedBy;
    private ArrayList<Roads> road;
    
    public Ant(int x, int y, int currentMapObject, ArrayList<Roads> road){
        posX = x;
        posY = y;
        this.road = road;
        ownedBy = Resources.getMyPlayerID();
        this.currentMapObject = currentMapObject;
    }
    
    /**
     * Used in server to recreate the ants with current positions and owned by.
     * @param x
     * @param y
     * @param currentMapObject
     * @param ownedBy
     */
    public Ant(int x, int y, int currentMapObject, int ownedBy){
        posX = x;
        posY = y;
        this.road = road;
        this.ownedBy = ownedBy;
        this.currentMapObject = currentMapObject;
    }
    
    /**
     * Used in server to recreate the ants from a string.
     * @param s
     */
    public Ant(String s){
    	String b[] = s.split(":");
    	posX = Integer.parseInt(b[0]);
    	posY = Integer.parseInt(b[1]);
    	ownedBy = Integer.parseInt(b[2]);
    	currentMapObject = Integer.parseInt(b[3]);
    	//System.out.println("building ANT");
    }
    
    /**
     * @return String
     */
    public String toString(){
    	return getPosX() + ":" + getPosY() + ":" + getOwnedBy() + ":" + getCurrentMapObject();
    }	
    
    /**
     * Gets and Xpos
     * @return int 
     */
    public int getPosX() {
        return posX;
    }
    
    /**
     * Gets and Ypos
     * @return int 
     */
    public int getPosY() {
        return posY;
    }
    
    /**
     * Gets who owns this ant
     * @return int 
     */
    public int getOwnedBy(){
        return ownedBy;
        
    }
    
    /**
     * gets where the ant is standing on the map
     * @return int 
     */
    public int getCurrentMapObject(){
        return currentMapObject;
    }
    
    /**
     * self explanatory.
     * @param x
     * @param y
     */
    public void setPos(int x, int y){
        posY = y;
        posX = x;
    }
    
    /**
     * Determines the direction the ant will take.
     * @param newMapObject
     */
    public void setCurrentMapObject(int newMapObject){
        previousMapObject = currentMapObject;
        currentMapObject = newMapObject;
    }
}
