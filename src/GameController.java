import java.util.*;
import java.awt.*;

public class GameController implements Observer{
	private GameView gameView = null;
	private GameState gameState = null;
	public GameController(GameView gameView, GameState gameState)
	{
		this.gameView = gameView;
		this.gameState = gameState;
	}
	@Override
	public void update(Observable o, Object arg) {
		if(o == gameView)
		{
			System.out.println("Something happened in GameView");
			
			if(arg.equals(new Point()))
			{
				Point point = (Point) arg;
				for(Stack s : gameState.getStacks())
				{
					if(point.getX() == s.getX())
					{
						System.out.println("hellow");
					}
				}
			}
			gameState.moveStack();
		}
		if(o == gameState)
		{
			//System.out.println("Something happened in GameState");
			
		}
	}
}