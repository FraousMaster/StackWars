
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