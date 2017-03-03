import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.Math.*;
import Global.Resources;

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
							//System.out.println();
							this.stackSelected = s;
						}
						else if(mouseClick.getButton() == 3)
						{
							if(stackSelected.getConnectedStacks(s.getX(), s.getY()) != null)
							{
									if(!(stackSelected.getPopulation() == 0))
									{
										System.out.println("THIS IS THE ROAD: " + stackSelected.getConnectedStacks(s.getX(), s.getY()).get(0).getType());
										stackSelected.decreasePopulation();
										gameState.addAnt(new Ant(stackSelected.getX(), stackSelected.getY(), 1, 
												stackSelected.getConnectedStacks(s.getX(), s.getY()).get(0).getType(), 
												stackSelected.getConnectedStacks(s.getX(), s.getY())));
									}
							}
							
							
						}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(o == gameState)
		{
			gameView.updateFrame();
			
		}
	}
}