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
    HashMap<Integer, Integer> mapX = new HashMap<>();
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
        int i, x = 0, y = 0;
            try {
                fr = new FileReader("Graphics\\Maps\\Map1.txt");

                while ((i = fr.read()) != -1)
                {
                    if(i < 57 && i > 48) {

                        mapX.put(x, i);
                        x++;
                        if (x == 20) {

                            mapY.put(y, mapX);
                            y++;
                            x = 0;
                            mapX = new HashMap<>();
                        }
                        //System.out.println(" Key Y: "+ y + " Key X:"+ x + " content :" + mapX.get(x));
                    }
                }
            }
            catch(IOException e){
                    e.printStackTrace();
            }

    }

    private void setRoads(){
        for (int i = 0; i < 20; i++) {
            //System.out.println(i + " " + mapY.get(i).size());
            for (int j = 0; j < 20; j++) {

                if(mapY.get(i).get(j) == 51){
                    //stack found
                    
                }
            }
        }

    }

    private void drawImage() {
        Graphics g = im.getGraphics();
        for (int i = 0; i < 20; i++) {
           // System.out.println(i + " " + mapY.get(i).size());
            for (int j = 0; j < 20; j++) {
                if ( mapY.get(i).get(j) == 49) {
                    try {
                        image = ImageIO.read(new File("Graphics\\Part1.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else if (mapY.get(i).get(j) == 50) {
                    try {
                        image = ImageIO.read(new File("Graphics\\Part2.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if ( mapY.get(i).get(j) == 51) {
                    try {
                        image = ImageIO.read(new File("Graphics\\Stack.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                        stacks.add(new Stack( 96 * j , 54 * i));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
