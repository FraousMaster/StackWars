import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Stack extends JComponent{

    private int population, stackPosX, stackPosY;
    private BufferedImage image;

    public Stack(int posX, int posY){
        population = 10;
        stackPosX = posX;
        stackPosY = posY;
        try
        {
            image = ImageIO.read(new File("Graphics\\Stack.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public int getX(){
        return stackPosX;
    }

    public int getY(){
        return stackPosY;
    }
    public void setPos()
    {
    	stackPosX += 100;
    	stackPosY += 100;
    }
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
  }
}
