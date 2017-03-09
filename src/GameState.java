import java.util.ArrayList;
import java.util.*;
import Global.Resources;

/**
 * @author Arvid Wiklund, Hugo Frost, Linus Nilsson, Johannes Edenholm
 */
public class GameState extends Observable{
	
    private ArrayList<Ant> ants;
    private ArrayList<Ant> antsToUpload;
    private ArrayList<Stack> stacks;
    private ArrayList<String> stacksToUpdate;
    private Map map;

    public GameState(){
        map = new Map();
        ants = new ArrayList<Ant>();
        antsToUpload = new ArrayList<Ant>();
        stacks = new ArrayList<Stack>();
        stacksToUpdate = new ArrayList<String>();
        initGame();
    }	
    
    public void initGame(){
        stacks = map.getStacks();
    }
    
    /**
     * 
     * @return ArrayList<Ant> Return list of all ants.
     */
    public ArrayList<Ant> getAnts(){
        return ants;
    }
    
    /**
     * 
     * @return ArrayList<Ant> Return list of all ants to be sent to the server.
     */
    public ArrayList<Ant> getAntsToUpload(){
        return antsToUpload;
    }
    
    /**
     * 
     * @return ArrayList<String> Return list of all stacks to be sent to the server in String form.
     */
    public ArrayList<String> getStacksToUpdate(){
        return stacksToUpdate;
    }
    
    /**
     * 
     * @return ArrayList<Stack> Return list of all stacks.
     */
    public ArrayList<Stack> getStacks(){
    	return stacks;
    }
    
    /**
     * 
     * @param a is string that can be split up and converted to stacks.
     */
    public void updateAllStacks(String a)
    {
    	String[] dummy = a.split("&");
    	for(String b : dummy)
    	{
    		if(!(b == "" || b == null || b.equals("") || b.equals(null)))
    		{
    			Stack stack = new Stack(b);
    		
				for(Stack s : stacks)
		    	{
		    		if(s.getX() == stack.getX() && s.getY() == stack.getY())
		    		{
		    			s.setPopulation(stack.getPopulation());
		    			s.setOwnedBy(stack.getOwnedBy());
		    		}
		    	}
    		}
    	}
    }
    
    /**
     * 
     * @param a is a string that can be split up and converted to ants.
     */
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
    
    /**
     * 
     * @param a Adds an ant, will be sent to the server in the next package.
     */
    public void addAnt(Ant a){
        antsToUpload.add(a);
    }
    
    /**
     * 
     * @param s Adds a stack, will be sent to server in the next package that contains an Ant.
     */
    public void addStackUpdate(Stack s)
    {
        stacksToUpdate.add(s.updateToString());
    }
    
    /**
     * 
     * @return Map
     */
    public Map getMap(){
        return map;
    }

}

