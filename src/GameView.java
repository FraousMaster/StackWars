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
    private Map map;
    private GameFrame gameFrame;
    
    public GameView(GameState gameState){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                gameFrame = new GameFrame();
            }
        });
        this.gameState = gameState;
        this.map = map;
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
            		int x = e.getX();
            		int y = e.getY();
            		setChanged();
            		notifyObservers(new Point(x, y));
            	}
            });
            add(drawPanel);
            pack();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        
        public void updateFrame()
        {
        	for(Ant a : gameState.getUppdates()) {
                if (a.getPosX() >= D_W) {
                    a.setPos(0,a.getPosY());
                    drawPanel.repaint();
                } else {
                    a.setPos(a.getPosX() + 1, a.getPosY()+ 1);
                    drawPanel.repaint();
                }
        	}
        }
        
        private class DrawPanel extends JPanel {

			private static final long serialVersionUID = 1L;
			private BufferedImage image;
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gameState.getMap().getImage(), 0, 0, 1920, 1080, null);

              /*  for(Stack s : gameState.getStacks())
                {
                	image = s.getImage();
                	g.drawImage(image, s.getX(), s.getY(), 82, 70, null);
                }*/
                
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
			this.gameState = gameState;
			gameFrame.updateFrame();

		}
	}

}

