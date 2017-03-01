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
<<<<<<< HEAD
    public void moveStack()
    {
    	for(Stack s : stacks)
    	{
    		s.setPos();
    		//setChanged();
        	//notifyObservers();
    	}
    }
    public void updateAllAnts(ArrayList<Ant> a){
=======
    
    public void uppdateGameState(ArrayList<Ant> a){
>>>>>>> ee9e87878fabfdb8d6a5433d9de0c7ec1eb50ea2
        ants = a;
        setChanged();
    	notifyObservers();
    }
    
    public void addAnt(Ant a){
       ants.add(a);
   	   System.out.println("ant added");
    }
    
    public Map getMap(){
        return map;
    }

}

