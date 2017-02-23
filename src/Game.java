<<<<<<< HEAD
import java.util.*;
=======
public class Game {
    private GameView gamView;
    
    private GameState gamState;

    public Game(){
       // gamController = new GameController();
        gamState = new GameState();
        gamView = new GameView(gamState);
    }
    private void getnull(){
>>>>>>> origin/JohannesBranchv2

public class Game {
    private GameView gameView = null;
    private GameController gameController = null;
    private GameState gameState = null;
    
    public Game() {
        gameState = new GameState();
        gameView = new GameView(gameState);
        gameController = new GameController(gameView, gameState);
        gameState.addObserver(gameView);
        gameState.addObserver(gameController);
        gameView.addObserver(gameController);     
    }
}