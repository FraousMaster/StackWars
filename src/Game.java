import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class Game {
    private GameView gameView = null;
    private GameController gameController = null;
    private GameState gameState = null;
    
    public Game() {
        gameState = new GameState();
        gameView = new GameView(gameState);
        gameState.addObserver(gameView);
        gameController = new GameController(gameView);
        gameView.addObserver(gameController);     
    }
}