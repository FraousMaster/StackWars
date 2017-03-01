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

    HashMap<Integer, Integer> mapX = new HashMap<>();

    private HashMap<Integer, HashMap<Integer, Integer>> mapY = new HashMap<>();

    public Map(){
        stacks = new ArrayList<Stack>();
        im = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_RGB);
        readFile();
        drawImage();
        setLabel();
        setRoads();
    }

    private void readFile(){
        FileReader fr;
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
                        //System.out.println(" Key Y: "+ y + " Key X:"+ x + " content :" + mapX.get(x));
                    }
                }
            }
            catch(IOException e){
                    e.printStackTrace();
            }

    }

    private void setRoads(){
        ArrayList<Roads> temp = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(mapY.get(i).get(j) == 50) {
                    temp = getRoad(j, i);
                }
            }
        }
        for(Roads r : temp)
            System.out.println(r.getPos());
    }
    private ArrayList<Roads> getRoadTiles(int x, int y){

        return null;
    }

    private ArrayList<Roads> getRoad(int x, int y)
    {
    	previousDirection dir = null;
    	int count = 0;
        ArrayList<Roads> temp = new ArrayList<>();
        while(true){
        	count ++;
            /*check position under the road at pos x y (- y)
            *this means direction from stack != under_stack
             */
        	//System.out.println(mapY.get(y).get(x) +" " + dir);
        	if(y != 19 && dir != previousDirection.under_tile){
	            if (56 >= mapY.get(y + 1).get((x)) && mapY.get(y + 1).get((x)) >= 51){
	            	y += 1;
	                temp.add(new Roads(x * 96, y * 54));
	                dir = previousDirection.over_tile;
	            }
	            else if (y != 19 && mapY.get(y + 1).get(x) == 50){
	            	//System.out.println("STack found");
	            	 y += 1;
	            }
                /*if(y > 19)
                    y = 19;*/
            }
            /*check position over the road at pos x,y (+ y)
            *this means direction from stack != under_stack
             */
        	else if(y != 0 && dir != previousDirection.over_tile){
	            if((56 >= mapY.get(y - 1).get(x) && mapY.get(y - 1).get((x)) >= 51)){
	            	 //System.out.println("over: y :"+ y + " x: "+ x);
	            	y -= 1;
	                temp.add(new Roads(x * 96, y * 54));
	                dir = previousDirection.under_tile;
	            }
	            else if (y != 0 && mapY.get(y - 1).get(x) == 50){
	            	//temp.add(new Roads(x * 96, y * 54));
	            	//System.out.println("STack found");
	            	y -= 1;
	            }
                /*if(y < 0)
                    y = 0;*/
        	}
            /*check position right of the road at pos x,y (+ x)
            *this means direction from stack != under_stack
             */

        	else if(x != 19 && dir != previousDirection.right_of_tile){
               //;
                if(56 >= mapY.get(y).get((x + 1)) &&  mapY.get(y).get((x + 1)) >= 51){
	            	 //System.out.println("Right: y :"+ y + " x: "+ x);
	            	x += 1;
	                temp.add(new Roads(x * 96, y * 54));
	                dir = previousDirection.left_of_tile;
	            }
	           
	            else if (x != 19 &&  mapY.get(y).get(x + 1) == 50){
	            	//temp.add(new Roads(x * 96, y * 54));
	            	//System.out.println("STack found");
	            	x += 1;
                    //System.out.println(mapY.get(y).get(x));
	            }
                /*if(x > 19)
                    x = 19;*/
        	}
            /*check position left of the road at pos x,y (- x)
            *this means direction from stack != under_stack
             */
        	else if(x != 0  && dir != previousDirection.left_of_tile){
	            if(x != 19 && (56 >= mapY.get(y).get((x - 1)) && mapY.get(y).get((x - 1)) >= 51)){
	            	//System.out.println("left: y :"+ y + " x: "+ x);
	            	x -= 1;
	                temp.add(new Roads(x * 96, y * 54));
	                dir = previousDirection.right_of_tile;

	            }
	            else if (x != 0 && mapY.get(y).get(x - 1) == 50){
	            	//temp.add(new Roads(x * 96, y * 54));
	            	//System.out.println("STack found");
	            	x -= 1;
                    //System.out.println(mapY.get(y).get(x));
	            }
                /*if(x < 0)
                    x = 0;*/
        	}
            if(mapY.get(y).get(x) == 50){
            	System.out.println("end: y :"+ y + " x: "+ x + " " + mapY.get(y).get((x)));
            	break;
            }
        }
        //System.out.println("hello");
        //System.out.println(temp.size());
            //break;

        //  System.out.println("hello");
        // System.out.println(temp.size());
        return temp;
        }



    private void drawImage() {
        Graphics g = im.getGraphics();
        for (int i = 0; i < 20; i++) {
           // System.out.println(i + " " + mapY.get(i).size());
            for (int j = 0; j < 20; j++) {
                if ( mapY.get(i).get(j) == 49) {//1
                    try {
                        image = ImageIO.read(new File("Graphics\\Part1.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } 
                else if ( mapY.get(i).get(j) == 50) {//2
                    try {
                        image = ImageIO.read(new File("Graphics\\StackV2.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                        stacks.add(new Stack( 96 * j , 54 * i));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mapY.get(i).get(j) == 51) {//3
                    try {
                        image = ImageIO.read(new File("Graphics\\RoadVertical.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } 
                else if (mapY.get(i).get(j) == 52) {//4
                    try {
                        image = ImageIO.read(new File("Graphics\\RoadHorizontal.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                } 
                else if (mapY.get(i).get(j) == 53) {//5
                    try {
                        image = ImageIO.read(new File("Graphics\\rightbottom.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mapY.get(i).get(j) == 54) {//6
                    try {
                        image = ImageIO.read(new File("Graphics\\lefttop.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mapY.get(i).get(j) == 55) {//7
                    try {
                        image = ImageIO.read(new File("Graphics\\topright.png"));
                        g.drawImage(image, 96 * j , 54 * i, 96, 54, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mapY.get(i).get(j) == 56) {//8
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
