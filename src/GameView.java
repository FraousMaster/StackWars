import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class GameView extends Observable implements Observer{

    private GameState gameState;

    public GameView(GameState gameState){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame();
            }
        });
        this.gameState = gameState;
    }


    public class GameFrame extends JFrame {

        private static final int D_W = 1920;
        private static final int D_H = 1080;
        int x = 0;
        int y = 0;

        DrawPanel drawPanel = new DrawPanel();

        public GameFrame() {
            drawPanel.addMouseListener(new MouseAdapter(){
            	public void mouseClicked(MouseEvent e)
            	{
            		System.out.println("Hellow world this is working");
            		setChanged();
            		notifyObservers();
            	}
            });
            add(drawPanel);
            pack();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        private class DrawPanel extends JPanel {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private BufferedImage image;
            protected void paintComponent(Graphics g) {
                try {
                    image = ImageIO.read(new File("Graphics\\Map.png"));
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 1920, 1080, null);
                
                for(Stack s : gameState.getStacks())
                {
                	image = s.getImage();
                	g.drawImage(image, s.getX(), s.getY(), 82, 70, null);
                }
                
                for(Ant a : gameState.getUppdates()) {
                	image = a.getImage();
                    g.drawImage(image, a.getPosX(), a.getPosY(), 82, 70, null);
                }
            }

            public Dimension getPreferredSize() {
                return new Dimension(D_W, D_H);
            }
        }
    }


	@Override
	public void update(Observable o, Object arg1) {
		if(o == gameState)
		{
			
		}
	}

}

