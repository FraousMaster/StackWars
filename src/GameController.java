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
			//System.out.println("Something happened in GameView" + arg);
			try
			{
				MouseEvent mouseClick = (MouseEvent)arg;
				Point point = new Point(mouseClick.getX(),mouseClick.getY());
				
				//System.out.println("Hellow");
				for(Stack s : gameState.getStacks())
				{
					//System.out.println("This is stacks position: " + s.getX() + " " + s.getY());
					
					if(point.getX() >= s.getX() && point.getX() <= (s.getX() + 96) && 
					   point.getY() >= s.getY() && point.getY() <= (s.getY() + 54))
					{
						
						
						if(mouseClick.getButton() == 1)
						{
							this.stackSelected = s;
							//System.out.println("Shows the menu for this stack");
						}
						else if(mouseClick.getButton() == 3)
						{
							//System.out.println("Trying to move to this stack");
							double selX = stackSelected.getX();
							double selY = stackSelected.getY();
							double moveX = s.getX();
							double moveY = s.getY();
							double angle = Math.atan2((selY - moveY), (selX - moveX));
						
							System.out.println("Values : " + selX + " " + selY + " " + moveX + " " + moveY);
							System.out.println(angle);
							gameState.uppdateGameState(new Ant(stackSelected.getX() + 96, stackSelected.getY() + 54, 1, 2, 1, 1, angle));
						}
						
						//System.out.println("This is truly a stack!");
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