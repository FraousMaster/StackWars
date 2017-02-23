public class Game {
    private GameView gamView;
    
    private GameState gamState;

    public Game(){
       // gamController = new GameController();
        gamState = new GameState();
        gamView = new GameView(gamState);
    }
    private void getnull(){

    }
}