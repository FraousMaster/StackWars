import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private enum previousDirection{
        over_tile,
        under_tile,
        left_of_tile,
        right_of_tile
    };

    private BufferedImage  im;
    private BufferedImage image;
    JLabel label;
    private ArrayList<Stack> stacks;
    private HashMap<Integer, HashMap<Integer, Integer>> mapY = new HashMap<>();

    public Map(){
        stacks = new ArrayList<Stack>();
        im = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_RGB);
        readFile();
        drawImage();
        setLabel();
       // getRoad(4,0, previousDirection.over_tile);
    }

    private void readFile(){
        FileReader fr;
        HashMap<Integer, Integer> mapX = new HashMap<>();
        int i, x = 0, y = 0;
            try {
                fr = new FileReader("Graphics\\Maps\\Map2.txt");

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
                    }
                }
            }
            catch(IOException e){
                    e.printStackTrace();
            }

    }

    private void setRoads(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                if(mapY.get(i).get(j) == 51){
                    //stack found

                    //check - x
                    if(mapY.get(i).get((j - 1)) == 50){

                    }
                    //check - y
                    else if(mapY.get(i - 1).get((j)) == 50){

                    }
                    //check + x
                    else if(mapY.get(i).get((j + 1)) == 50){

                    }
                    //check + y
                    else if(mapY.get(i + 1).get((j)) == 50){

                    }
                }
            }
        }
    }

    private ArrayList<Roads> getRoad(int x, int y, previousDirection dir)
    {
        int tile;
        boolean hasNext = false;
        ArrayList<Roads> temp = new ArrayList<>();
        Roads previousroad;
        while(true){
            /*check position over the road at pos x y (- y)
            *this means direction from stack != under_stack
             */

            if (y != 0 && (56 <= mapY.get(y - 1).get((x)) && mapY.get(y - 1).get((x)) >= 51) && dir != previousDirection.under_tile) {
                y -= 1;
                previousroad = new Roads(x * 96, y * 54);
                temp.add(previousroad);
            }
            /*check position under the road at pos x,y (+ y)
            *this means direction from stack != under_stack
             */
            else if((56 <= mapY.get(y + 1).get((x)) && mapY.get(y + 1).get((x)) >= 51)&& dir != previousDirection.over_tile){
                y += 1;
                temp.add(new Roads(x * 96, y * 54));
            }
            /*check position left of the road at pos x,y (- x)
            *this means direction from stack != under_stack
             */
            else if(x != 0 && (56 <= mapY.get(y).get((x - 1)) && mapY.get(y).get((x - 1)) >= 51)&& dir != previousDirection.right_of_tile){
                x -= 1;
                temp.add(new Roads(x * 96, y * 54));
            }
            /*check position right of the road at pos x,y (+ x)
            *this means direction from stack != under_stack
             */
            else if((56 <= mapY.get(y).get((x + 1)) && mapY.get(y).get((x + 1)) >= 51) && dir != previousDirection.left_of_tile){
                x += 1;
                temp.add(new Roads(x * 96, y * 54));
            }

            System.out.println("hello");
            return temp;
            /*

                continue;
            }*/
        }

    }

    private void drawImage() {
        Graphics g = im.getGraphics();
        for (int i = 0; i < 20; i++) {
           // System.out.println(i + " " + mapY.get(i).size());
            for (int j = 0; j < 20; j++) {
                int tile = mapY.get(i).get(j);
                if (tile == 49) {//1
                    try {
                        image = ImageIO.read(new File("Graphics\\Part1.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } 
                else if (tile == 50) {//2
                    try {
                        image = ImageIO.read(new File("Graphics\\StackV2.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                        stacks.add(new Stack( 96 * j , 54 * i));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (tile == 51) {//3
                    try {
                        image = ImageIO.read(new File("Graphics\\RoadVertical.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } 
                else if (tile == 52) {//4
                    try {
                        image = ImageIO.read(new File("Graphics\\RoadHorizontal.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                } 
                else if (tile == 53) {//5
                    try {
                        image = ImageIO.read(new File("Graphics\\rightbottom.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (tile == 54) {//6
                    try {
                        image = ImageIO.read(new File("Graphics\\lefttop.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (tile == 55) {//7
                    try {
                        image = ImageIO.read(new File("Graphics\\topright.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (tile == 56) {//8
                    try {
                        image = ImageIO.read(new File("Graphics\\leftbottom.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
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
