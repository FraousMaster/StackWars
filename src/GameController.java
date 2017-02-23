import java.util.*;

public class GameController implements Observer{
	private GameView gameView = null;
	public GameController(GameView gameView)
	{
		this.gameView = gameView;
	}
	@Override
	public void update(Observable o, Object arg) {
		if(o == gameView)
		{
			System.out.println("Hellow world this is working very good");
		}
	}
}
