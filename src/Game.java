import java.util.*;

public class Game implements Observer {
    private GameView gamView;
    private GameController gamController;
    private GameState gamState;

    public Game() {
        gamController = new GameController();
        gamState = new GameState();
        gamView = new GameView(gamState);
    }
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}