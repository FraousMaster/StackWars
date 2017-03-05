import java.util.ArrayList;
import java.util.ArrayList;
import java.util.*;
import java.util.ArrayList;
import java.util.*;
import Global.Resources;

public class GameState extends Observable{

    private ArrayList<Ant> ants;
    private ArrayList<Ant> antsToUpload;
    private ArrayList<Stack> stacks;
    private Map map;

    public GameState(){
        map = new Map();
        ants = new ArrayList<Ant>();
        antsToUpload = new ArrayList<Ant>();
        stacks = new ArrayList<Stack>();
        initGame();
    }

    public void initGame(){
        stacks = map.getStacks();
    }

    public ArrayList<Ant> getAnts(){
        return ants;
    }
    public ArrayList<Ant> getAntsToUpload(){
        return antsToUpload;
    }
    
    public ArrayList<Stack> getStacks(){
    	return stacks;
    }
    
    public void updateAllAnts(ArrayList<Ant> a){
    	if(a != null)
        {
    		ants = a;
        }
    	else
    		ants = new ArrayList<>();
    	
        setChanged();
    	notifyObservers();
    }
    
    public void addAnt(Ant a){
        antsToUpload.add(a);
    }

    
    public Map getMap(){
        return map;
    }

}

