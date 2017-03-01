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

<<<<<<< HEAD
    private int posX, posY, currentMapObject, previousMapObject, ownedBy;
    //double speedX, speedY, angle;
    //private BufferedImage image;
=======
    private int posX, posY, currentMapObject, previousMapObject;
    double speedX, speedY, angle;
    private int ownedBy;
    private BufferedImage image;
>>>>>>> 9adef257ff5f0d877112c76f96c46cc1d62dbca1
    private ArrayList<BufferedImage> imageSelection;
    
    public Ant(int x, int y, int owns, int currentMapObject){
        posX = x;
        posY = y;
        ownedBy = owns;
        this.currentMapObject = currentMapObject;
    
    }
    
    public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
      
    	
    	stream.defaultWriteObject();    
    	stream.writeInt(posX);
        stream.writeInt(posY);
        stream.writeInt(currentMapObject);
        stream.writeInt(ownedBy);
    }

    public void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
    	stream.defaultReadObject();

    	posX = stream.readInt(); 
    	posY = stream.readInt();
    	currentMapObject = stream.readInt();
    	ownedBy = stream.readInt();
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
