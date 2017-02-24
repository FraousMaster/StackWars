import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Map {

    private BufferedImage  im;
    private BufferedImage image;
    JLabel label;
    private ArrayList<Stack> stacks;

    public Map(){
        stacks = new ArrayList<Stack>();
        im = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_RGB);
        drawImage();
        setLabel();
    }

    private void drawImage(){
        Graphics g = im.getGraphics();
        FileReader fr;
        int i, x = 0, y = 0;

        try {
            fr = new FileReader("Graphics\\Maps\\Map1.txt");
            while ((i = fr.read()) != -1) {
                if (i == 49) {
                    try {
                        image = ImageIO.read(new File("Graphics\\Part1.png"));
                        g.drawImage(image, 96 * x, 54 * y, 96, 54, null);
                        x++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (x == 20) {
                        y++;
                        x = 0;
                    }

                } else if (i == 50) {
                    try {
                        image = ImageIO.read(new File("Graphics\\Part2.png"));
                        g.drawImage(image, 96 * x, 54 * y, 96, 54, null);
                        x++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (x == 20) {
                        y++;
                        x = 0;
                    }
                } else if (i == 51) {
                    try {
                        image = ImageIO.read(new File("Graphics\\Stack.png"));
                        g.drawImage(image, 96 * x, 54 * y, 96, 54, null);
                        stacks.add(new Stack( 96 * x, 54 * y));
                        x++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    if (x == 20) {
                        y++;
                        x = 0;
                    }
                    
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void setLabel(){
        label = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(im,0,0,null);
            }
        };
        label.setPreferredSize(new Dimension(1920,1080));
    }
    public BufferedImage getImage(){
        return im;
    }
    public ArrayList<Stack> getStacks(){
        return stacks;
    }

}
