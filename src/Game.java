import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class Game implements Observer {
    private GameView gamView;
    private GameController gamController;
    private GameState gamState;

    public Game() {
        gamController = new GameController();
        gamState = new GameState();
        for(Stack s : gamState.getStacks())
        {
        	s.addMouseListener(new MouseAdapter()
			{
        		@Override
        		public void mouseClicked(MouseEvent e)
        		{
        			stackClicked();
        		}
			});
        }
        gamView = new GameView(gamState);
    }
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	public void stackClicked()
	{
		System.out.println("Hellow");
	}
	
}