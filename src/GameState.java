import java.util.ArrayList;
import java.util.ArrayList;
import java.util.*;
import java.util.ArrayList;
import java.util.*;

public class GameState extends Observable{

    private ArrayList<Ant> ants;
    private ArrayList<Ant> antsToUpload;
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
    
    public void updateAllAnts(ArrayList<Ant> a){
        ants = a;
        setChanged();
    	notifyObservers();
    	System.out.println("ants upd");
    }
    
    public void addAnt(Ant a){
       ants.add(a);
   	   System.out.println("ant added");
    }
    
    public Map getMap(){
        return map;
    }

}

