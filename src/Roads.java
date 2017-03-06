import java.awt.*;
import Global.Resources;

public class Roads {

    private Point pos;
    private int type;
    public Roads(int x, int y, int type){
        pos = new Point(x, y);
        this.type = type - 48;
    }

    public int getType(){
        return type;
    }
    public Point getPos() {
        return pos;
    }
    
    public Roads(String s)
    {
    	String b[] = s.split(":");
    	//System.out.println(s);
    	pos = new Point(Integer.parseInt(b[0]), Integer.parseInt(b[1]));
    	type = Integer.parseInt(b[2]);
    }
}
