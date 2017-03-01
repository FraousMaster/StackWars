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
        try
        {
        	image = ImageIO.read(new File("Graphics\\Ant\\AntV4.png"));
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
    }
    
    public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
      
    	
    	stream.defaultWriteObject();
    	stream.writeInt(imageSelection.size()); // how many images are serialized?

    	    for (BufferedImage eachImage : imageSelection) {
    	        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    	        ImageIO.write(eachImage, "jpg", buffer);

    	        stream.writeInt(buffer.size()); // Prepend image with byte count
    	        buffer.writeTo(stream);         // Write image
    	    }
    	    
    	stream.writeInt(posX);
        stream.writeInt(posY);
        stream.writeInt(currentMapObject);
        stream.writeInt(ownedBy);
        
        stream.writeDouble(speedX);
        stream.writeDouble(speedY);
        stream.writeDouble(angle);
    }

    public void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
    	stream.defaultReadObject();
    	 int imageCount = stream.readInt();
    	    imageSelection = new ArrayList<BufferedImage>(imageCount);
    	    for (int i = 0; i < imageCount; i++) {
    	        int size = stream.readInt(); // Read byte count

    	        byte[] buffer = new byte[size];
    	        stream.readFully(buffer); // Make sure you read all bytes of the image

    	        imageSelection.add(ImageIO.read(new ByteArrayInputStream(buffer)));
    	    }
    	
    	
    	posX = stream.readInt(); 
    	posY = stream.readInt();
    	currentMapObject = stream.readInt();
    	ownedBy = stream.readInt();
    	
    	speedX = stream.readDouble(); 
    	speedY = stream.readDouble(); 
    	angle = stream.readDouble(); 
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
