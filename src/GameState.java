import java.util.ArrayList;


public class GameState {

    private ArrayList<Ant> ants;
    public GameState(){
        ants = new ArrayList<Ant>();
        initGame();
    }

    public void initGame(){
        for(int i = 0; i <= 0; i++){
            ants.add(new Ant(i * 5, i * 2, Ant.player.PlayerFour));
        }
    }

    public ArrayList<Ant> getUppdates(){
        return ants;
    }

    public void uppdateGameState(ArrayList<Ant> a){
        ants = a;
    }

}
