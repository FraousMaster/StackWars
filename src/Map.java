import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Map {

    private BufferedImage  im;
    private BufferedImage image;
    JLabel label;
    private ArrayList<Stack> stacks;
    private HashMap<Integer, Integer> mapX = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, Integer>> mapY = new HashMap<>();

    public Map(){
        stacks = new ArrayList<Stack>();
        im = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_RGB);
        readFile();
        drawImage();
        setLabel();
    }

    private void readFile(){
        FileReader fr;
        int i, x = 0, y = 0;;
            try {
                fr = new FileReader("Graphics\\Maps\\Map1.txt");
                while ((i = fr.read()) != -1)
                {
                    mapX.put(x, i);
                    if (x == 20) {
                        mapY.put(y, mapX);
                        y++;
                        x = 0;
                    }
                }
            }
            catch(IOException e){
                    e.printStackTrace();
            }

    }

    private void drawImage(){
        Graphics g = im.getGraphics();
        int x = 0, y = 0;;
        for(int i = 0; i < mapY.size(); i ++){
            for(int j; j < mapY.get(i).size(); j++) {
                if (i == 49) {
                    try {
                        image = ImageIO.read(new File("Graphics\\Part1.png"));
                        g.drawImage(image, 96 * x, 54 * y, 96, 54, null);
                        x++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }

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
