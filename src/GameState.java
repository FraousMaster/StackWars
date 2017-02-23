<<<<<<< HEAD
 import java.util.ArrayList;
=======
import java.util.ArrayList;
>>>>>>> 9b7b0c82e943d14319b62ef8609f8c30d4dbd02a

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
