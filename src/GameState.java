import java.util.ArrayList;
import java.util.ArrayList;
import java.util.*;
import java.util.ArrayList;
import java.util.*;

public class GameState extends Observable{

    private ArrayList<Ant> ants;
    private ArrayList<Stack> stacks;
    private Map map;

    public GameState(){
        map = new Map();
        ants = new ArrayList<Ant>();
        stacks = new ArrayList<Stack>();
        initGame();
    }

    public void initGame(){
        stacks = map.getStacks();
    }

    public ArrayList<Ant> getAnts(){
        return ants;
    }

    public ArrayList<Stack> getStacks(){
    	return stacks;
    }
    public void moveStack()
    {
    	for(Stack s : stacks)
    	{
    		s.setPos();
    		//setChanged();
        	//notifyObservers();
    	}
    }
    public void uppdateGameState(Ant a){
        ants.add(a);
        setChanged();
    	notifyObservers();
    }
    public Map getMap(){
        return map;
    }

}

