import java.util.ArrayList;
import java.util.*;
import Global.Resources;

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

    public ArrayList<Ant> getAnts(){
        return ants;
    }
    
    /*
     * @author Arvid Wiklund
     * Return an ArrayList with newly added ants locally that is sent to the server.
     */
    public ArrayList<Ant> getAntsToUpload(){
        return antsToUpload;
    }
    
    /*
     * @author Arvid Wiklund
     * Return an ArrayList with all changes to stacks locally.
     */
    public ArrayList<String> getStacksToUpdate(){
        return stacksToUpdate;
    }
    
    public ArrayList<Stack> getStacks(){
    	return stacks;
    }
    
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

    public void addStackUpdate(Stack s)
    {
        stacksToUpdate.add(s.updateToString());
    }
    
    public Map getMap(){
        return map;
    }

}

