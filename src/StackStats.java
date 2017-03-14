import Global.Resources;
/**
 * @Author Linus Nilsson
 * Not currently used but is for future buff systems.
 */
public class StackStats {
	private int population;
	private int xPos;
	private int yPos;
	
	public StackStats(int x, int y){
		xPos = x;
		yPos = y;
		population = 10;
	}
	
	public void setPopulation(int value){
		population = value;
	}
	public int getxPos(){
		return xPos;
	}
	public int getyPos(){
		return yPos;
	}
}
