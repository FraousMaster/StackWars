<<<<<<< HEAD
import java.util.ArrayList;

=======
 import java.util.ArrayList;
 import java.util.*;
 import java.util.ArrayList;
 import java.util.*;
>>>>>>> e0c7c80f0b7a80695ecf8ed9c9d20b9358e27020

public class GameState extends Observable{

    private ArrayList<Ant> ants;
    private ArrayList<Stack> stacks;
    public GameState(){
        ants = new ArrayList<Ant>();
        stacks = new ArrayList<Stack>();
        initGame();
    }

    public void initGame(){
        for(int i = 0; i <= 0; i++){
            ants.add(new Ant(i * 5, i * 2, Ant.player.PlayerFour));
        }
        stacks.add(new Stack(100,100));
    }

    public ArrayList<Ant> getUppdates(){
        return ants;
    }

    public ArrayList<Stack> getStacks()
    {
    	return stacks;
    }
    
    public void uppdateGameState(Observable o, ArrayList<Ant> a){
        ants = a;
    }

    
}

