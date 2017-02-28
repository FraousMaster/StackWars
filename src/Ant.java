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

    private int posX, posY, currentMapObject, previousMapObject;
    double speedX, speedY, angle;
    private int ownedBy;
    private BufferedImage image;
    
    public Ant(int x, int y, int owns, int currentMapObject, double speedX, double speedY, double angle){
        posX = x;
        posY = y;
        ownedBy = owns;
        this.speedX = speedX;
        this.speedY = speedY;
        this.angle = angle;
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
