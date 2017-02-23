import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ant {
    public enum player{
        playerOne,
        playerTwo,
        playerThree,
        PlayerFour
    }

    private int posX, posY;
    private player ownedBy;
    private BufferedImage image;
    
    public Ant(int x, int y, player owns){
        posX = x;
        posY = y;
        ownedBy = owns;
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
    public player getOwnedBy(){
        return ownedBy;
    }
    public void setPos(int x, int y){
        posY = y;
        posX = x;
    }
    
    public BufferedImage getImage()
    {
    	return image;
    }
}
