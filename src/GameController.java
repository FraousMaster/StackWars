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
							//System.out.println();
							this.stackSelected = s;
						}
						else if(mouseClick.getButton() == 3)
						{
							//gameState.addAnt(new Ant(stackSelected.getX(), stackSelected.getY(), 1, 4, 
									//stackSelected.getConnectedStacks(s.getX(), s.getY())));
							//System.out.println(s.getX() + " : " + s.getY());
							if(stackSelected.getConnectedStacks(s.getX(), s.getY()) != null)
							{
								//System.out.println("Right click");
								gameState.addAnt(new Ant(stackSelected.getX(), stackSelected.getY(), 1, 4, 
										stackSelected.getConnectedStacks(s.getX(), s.getY())));
							}
							
							System.out.println("adding ant");
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