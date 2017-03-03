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
}
