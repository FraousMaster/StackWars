import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.Math.*;

public class GameController implements Observer{
	private GameView gameView = null;
	private GameState gameState = null;
	private Stack stackSelected;
	
	public GameController(GameView gameView, GameState gameState)
	{
		this.gameView = gameView;
		this.gameState = gameState;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o == gameView)
		{
			try
			{
				MouseEvent mouseClick = (MouseEvent)arg;
				Point point = new Point(mouseClick.getX(),mouseClick.getY());
				
				for(Stack s : gameState.getStacks())
				{
					
					if(point.getX() >= s.getX() && point.getX() <= (s.getX() + 96) && 
					   point.getY() >= s.getY() && point.getY() <= (s.getY() + 54))
					{
						
						
						if(mouseClick.getButton() == 1)
						{
							this.stackSelected = s;
						}
						else if(mouseClick.getButton() == 3)
						{
							double selX = stackSelected.getX();
							double selY = stackSelected.getY();
							double moveX = s.getX();
							double moveY = s.getY();
							double angle = Math.atan2((selY - moveY), (selX - moveX));
						
							//System.out.println("Values : " + selX + " " + selY + " " + moveX + " " + moveY);
							//System.out.println(angle);
							gameState.addAnt(new Ant(stackSelected.getX(), stackSelected.getY(), 1, 4));
							//System.out.println("adding ant");
						}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			gameState.moveStack();
		}
		if(o == gameState)
		{
			gameView.updateFrame();
			
		}
	}
}