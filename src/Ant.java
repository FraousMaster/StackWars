import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ant {
    public enum Player{
        playerOne,
        playerTwo,
        playerThree,
        PlayerFour
    }

    private int posX, posY, speedX, speedY, currentMapObject, previousMapObject;
    private int ownedBy;
    private BufferedImage image;
    
    public Ant(int x, int y, int owns, int currentMapObject){
        posX = x;
        posY = y;
        ownedBy = owns;
        this.currentMapObject = currentMapObject;
        try
        {
        	image = ImageIO.read(new File("Graphics\\Ant\\AntV4.png"));
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public int getSpeedX() {
        return speedX;
    }
    public int getSpeedY() {
        return speedY;
    }
    public int getOwnedBy(){
        return ownedBy;
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
    public BufferedImage getImage()
    {
    	return image;
    }
}
