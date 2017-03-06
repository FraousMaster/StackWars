import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.Math.*;
import Global.Resources;

public class GameController implements Observer{
	private GameView gameView = null;
	private GameState gameState = null;
	private Stack stackSelected;
	private int populationIncrease = 0;
	private int sX = Resources.getScalingFactorX();
	private int sY = Resources.getScalingFactorY();
	
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
					if(point.getX() >= s.getX() && point.getX() <= (s.getX() + sX) && 
					   point.getY() >= s.getY() && point.getY() <= (s.getY() + sY))
					{
						
						
						if(mouseClick.getButton() == 1)
						{	
							//System.out.println();
							if(s.getOwnedBy() == Resources.getMyPlayerID())
							{
								
								if(stackSelected != null) {
									stackSelected.setSelected(false);
								}
								this.stackSelected = s;
								stackSelected.setSelected(true);
							}
						}
						else if(mouseClick.getButton() == 2)
						{
							if(stackSelected.getConnectedStacks(s.getX(), s.getY()) != null && stackSelected.getOwnedBy() == Resources.getMyPlayerID())
							{
								stackSelected.setRallyPoint(s);
								gameState.addStackUpdate(stackSelected);
							}
						}
						else if(mouseClick.getButton() == 3)
						{
							if(stackSelected.getConnectedStacks(s.getX(), s.getY()) != null && stackSelected.getOwnedBy() == Resources.getMyPlayerID())
							{
									if(!(stackSelected.getPopulation() == 0))
									{
										//System.out.println("THIS IS THE ROAD: " + stackSelected.getConnectedStacks(s.getX(), s.getY()).get(0).getType());
										stackSelected.decreasePopulation();
										int type = stackSelected.getConnectedStacks(s.getX(), s.getY()).get(0).getType();
										int x = Resources.getAntXOffset(type);
										int y = Resources.getAntYOffset(type);
										gameState.addAnt(new Ant(stackSelected.getX() + x, stackSelected.getY() + y, 
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
			
		}
	}
}