import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import Global.Resources;

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
    private int lastX, lastY;
    private int sX = Resources.getScalingFactorX(), sY = Resources.getScalingFactorY();
    
    HashMap<Integer, Integer> mapX = new HashMap<>();

    private HashMap<Integer, HashMap<Integer, Integer>> mapY = new HashMap<>();

    public Map(){
        stacks = new ArrayList<Stack>();
        im = new BufferedImage(Resources.getWidth(), Resources.getHeight(), BufferedImage.TYPE_INT_RGB);
        readFile();
        drawImage();
        setLabel();
        setRoads();
        for(Stack s: stacks){
        	//System.out.println(s.testPrint());
        }
    }

    private void readFile(){
        FileReader fr;
        int i, x = 0, y = 0;
            try {
                fr = new FileReader("Graphics/Maps/Map2.txt");


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
                	for(int k = 0; k <= 4; k++){
                		//check y + 1 under stack
                		if(i != 19 && (56 >= mapY.get(i + 1).get(j) && mapY.get(i + 1).get(j) >= 51)){
                			temp.add(new Roads(j * sX, (i + 1) * sY, mapY.get(i + 1).get(j)));
                			for(Roads r : getRoad(j, (i + 1), previousDirection.over_tile)){
                				temp.add(r);
                			}
                			addRoadToStack(j, i, lastX, lastY, temp);
                			temp = new ArrayList<>();
                		}
                		//check over stack - y
                		if (i != 0 && (56 >= mapY.get(i - 1).get(j) && mapY.get(i - 1).get(j) >= 51)){
                			temp.add(new Roads(j * sX, (i - 1) * sY, mapY.get(i - 1).get(j) +5));
                			for(Roads r : getRoad(j, (i - 1), previousDirection.under_tile)){
                				temp.add(r);
                			}
                			addRoadToStack(j, i, lastX, lastY, temp);
                			temp = new ArrayList<>();
                			
                		}
                		//check left of stack -x
                		if (j != 0 && (56 >= mapY.get(i).get(j - 1) && mapY.get(i).get(j - 1) >= 51)){
                			temp.add(new Roads((j - 1) * sX, i * sY, mapY.get(i).get(j - 1) + 5));
                			for(Roads r : getRoad((j - 1), i, previousDirection.right_of_tile)){
                				temp.add(r);
                			}
                			addRoadToStack(j, i, lastX, lastY, temp);
                			temp = new ArrayList<>();
                		}
                		//check right of stack + x
                		if (j != 19 && (56 >= mapY.get(i).get(j + 1) && mapY.get(i).get(j + 1) >= 51)){
                			temp.add(new Roads((j + 1) * sX, i * sY, mapY.get(i).get(j + 1)));
                			for(Roads r : getRoad((j + 1), i, previousDirection.left_of_tile)){
                				temp.add(r);
                			}
                			addRoadToStack(j, i, lastX, lastY, temp);
                			temp = new ArrayList<>();
                		}
                	}
                }
            }
        }
        
    }

    private ArrayList<Roads> getRoad(int x, int y, previousDirection dir)
    {
        ArrayList<Roads> temp = new ArrayList<>();
        while(true){
            /*check position under the road at pos x y (+ y)
            *this means direction from stack != under_stack
             */
        	if(y != 19 && dir != previousDirection.under_tile){
	            if (56 >= mapY.get((y + 1)).get((x)) && mapY.get(y + 1).get((x)) >= 51){
	            	//System.out.println("In under: y :"+ y + " x: "+ x + " " + mapY.get(y).get((x)) + "  Dir: " + dir);
	            	y += 1;
	                temp.add(new Roads(x * sX, y * sY, mapY.get(y).get(x)));
	                dir = previousDirection.over_tile;
	            }
	            if (y != 19 && mapY.get((y + 1)).get(x) == 50){
	            	//System.out.println("STack found");
	            	//System.out.println("IN break in under: " + mapY.get((y + 1)).get(x)+ " y: " + y + " x: " + x);
	            	y += 1;
	            	 break;
	            }
            }
            /*check position over the road at pos x,y (+ y)
            *this means direction from stack != under_stack
             */
        	if(y != 0 && dir != previousDirection.over_tile){
	            if((56 >= mapY.get(y - 1).get(x) && mapY.get(y - 1).get((x)) >= 51)){
	            	//System.out.println("In Over: y :"+ y + " x: "+ x + " " + mapY.get(y).get((x)) + "  Dir: " + dir);
	            	 //System.out.println("over: y :"+ y + " x: "+ x);
	            	
	            	y -= 1;
	                temp.add(new Roads(x * sX, y * sY, (mapY.get(y).get(x) + 5)));
	                dir = previousDirection.under_tile;
	            }
	            if(y != 0 && mapY.get((y - 1)).get(x) == 50){
	            	//System.out.println("IN break in over: " + mapY.get((y - 1)).get(x)+ " y: " + y + " x: " + x);
	            	y -= 1;
	            	break;
	            }
        	}
            /*check position right of the road at pos x,y (+ x)
            *this means direction from stack != under_stack
             */

        	if(x != 19 && dir != previousDirection.right_of_tile){
        		//System.out.println("in Right: y :"+ y + " x: "+ x + " " + mapY.get(y).get((x)) + "  Dir: " + dir);
                if(56 >= mapY.get(y).get((x + 1)) &&  mapY.get(y).get((x + 1)) >= 51){
	            	 //System.out.println("Right: y :"+ y + " x: "+ x);
	            	x += 1;
	                temp.add(new Roads(x * sX, y * sY,(mapY.get(y).get(x) + 5)));
	                dir = previousDirection.left_of_tile;
	            }
	           
	            if (x != 19 && mapY.get(y).get((x + 1)) == 50){
	            	//System.out.println("IN break in reigh: " + mapY.get((y)).get(x + 1)+ " y: " + y + " x: " + x);
	            	x += 1;
	            	break;
	            }
        	}
            /*check position left of the road at pos x,y (- x)
            *this means direction from stack != under_stack
             */
        	if(x != 0  && dir != previousDirection.left_of_tile){
	            if(x != 19 && (56 >= mapY.get(y).get((x - 1)) && mapY.get(y).get((x - 1)) >= 51)){
	            	//System.out.println("In left:"+ " y :"+ y + " x: "+ x + " " + mapY.get(y).get((x)) + "  Dir: " + dir);
	            	//System.out.println("left: y :"+ y + " x: "+ x);
	            	x -= 1;
	                temp.add(new Roads(x * sX, y * sY, mapY.get(y).get(x)));
	                dir = previousDirection.right_of_tile;

	            }
	            if(x != 0 && mapY.get(y).get((x - 1)) == 50){
	            	//System.out.println("IN break in left: " + mapY.get((y)).get(x - 1)+ " y: " + y + " x: " + x);
	            	x -= 1;
	            	break;
	            }
        	}
        	
            if(mapY.get(y).get(x) == 50){
            	//System.out.println("end: y :"+ y + " x: "+ x + " " + mapY.get(y).get((x)) + "  Dir: " + dir);
            	break;
            }
        }    
        /*System.out.println();
        System.out.println("DONE");
        System.out.println();*/
        lastX = x;
        lastY = y;
        return temp;
    }
    private void addRoadToStack(int x,int y, int x1, int y1, ArrayList<Roads> list){
        //System.out.println("FROM: " + x + " , " + y + " TO " + x1 + " , " + y1 + " ROAD :");
        for(Roads r : list){
        	 //System.out.println(r.getPos() + " type : " + r.getType());
        }
    	x1 = x1 * sX;
        y1 = y1 * sY;
        x = x * sX;
        y = y * sY;
        Point p = new Point(x1, y1);
        for(Stack s : stacks){
            if(s.getX() == x && s.getY() == y){
                s.addPath(p, list);
            }
        }
    }



    private void drawImage() {
        Graphics g = im.getGraphics();
        for (int i = 0; i < 20; i++) {
           // System.out.println(i + " " + mapY.get(i).size());
            for (int j = 0; j < 20; j++) {
                if ( mapY.get(i).get(j) == 49) {//1
                    try {
                        image = ImageIO.read(new File("Graphics/Part1.png"));
                        g.drawImage(image, sX * j , sY * i, sX, sY, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } 
                else if ( mapY.get(i).get(j) == 50) {//2
                    try {
                        image = ImageIO.read(new File("Graphics/StackV2.png"));
                        g.drawImage(image, sX * j , sY * i, sX, sY, null);
                        stacks.add(new Stack( sX * j , sY * i));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mapY.get(i).get(j) == 51) {//3
                    try {
                        image = ImageIO.read(new File("Graphics/RoadVertical.png"));
                        g.drawImage(image, sX * j , sY * i, sX, sY, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } 
                else if (mapY.get(i).get(j) == 52) {//4
                    try {
                        image = ImageIO.read(new File("Graphics/RoadHorizontal.png"));
                        g.drawImage(image, sX * j , sY * i, sX, sY, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                } 
                else if (mapY.get(i).get(j) == 53) {//5
                    try {
                        image = ImageIO.read(new File("Graphics/rightbottom.png"));
                        g.drawImage(image, sX * j , sY * i, sX, sY, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mapY.get(i).get(j) == sY) {//6
                    try {
                        image = ImageIO.read(new File("Graphics/lefttop.png"));
                        g.drawImage(image, sX * j , sY * i, sX, sY, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mapY.get(i).get(j) == 55) {//7
                    try {
                        image = ImageIO.read(new File("Graphics/topright.png"));
                        g.drawImage(image, sX * j , sY * i, sX, sY, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mapY.get(i).get(j) == 56) {//8
                    try {
                        image = ImageIO.read(new File("Graphics/leftbottom.png"));
                        g.drawImage(image, sX * j , sY * i, sX, sY, null);
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
        label.setPreferredSize(new Dimension(Resources.getWidth(),Resources.getHeight()));
    }
    public BufferedImage getImage(){
        return im;
    }
    public ArrayList<Stack> getStacks(){
        return stacks;
    }

}
