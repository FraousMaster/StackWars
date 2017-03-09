import java.util.ArrayList;
import Global.Resources;

public class Ant {
	/**
	 * 
	 * @author Arvid Wiklund, Linus Nilsson
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
    
    public Ant(int x, int y, int currentMapObject, ArrayList<Roads> road){
        posX = x;
        posY = y;
        this.road = road;
        ownedBy = Resources.getMyPlayerID();
        this.currentMapObject = currentMapObject;
    }
    
    /**
     * Used in server.
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
     * @return int 
     */
    public int getPosX() {
        return posX;
    }
    
    /**
     * @return int 
     */
    public int getPosY() {
        return posY;
    }
    
    /**
     * @return int 
     */
    public int getOwnedBy(){
        return ownedBy;
        
    }
    
    /**
     * @return int 
     */
    public int getCurrentMapObject(){
        return currentMapObject;
    }
    
    /**
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
