import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageIO;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class GameView extends Observable implements Observer{

    private GameState gameState;
    private GameFrame gameFrame;
    
    public GameView(GameState gameState){
        gameFrame = new GameFrame();
        this.gameState = gameState;
    }
    public void updateFrame()
    {
    	gameFrame.updateThisFrame();
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
            		notifyObservers(e);
            	}
            });
            add(drawPanel);
            pack();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        private void updateThisFrame()
        {
        	//System.out.println("Pos: updateThisFrame");
        	for(Ant a : gameState.getAnts()) {
                if (a.getPosX() >= D_W) {
                    //a.setPos(0,a.getPosY());
                    //drawPanel.repaint();
                } else {
                    //a.setPos((int)(a.getPosX() + Math.sqrt(2)), (int)(a.getPosY()+ Math.sqrt(2)));
                    
                    //drawPanel.repaint();
                }
                drawPanel.repaint();
        	}
        }
                
        private class DrawPanel extends JPanel {

			private static final long serialVersionUID = 1L;
			private BufferedImage image;
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gameState.getMap().getImage(), 0, 0, 1920, 1080, null);
                for(Stack s : gameState.getStacks())
                {
                	g.setColor(Color.WHITE);
                	g.drawString("Pop: " + s.getPopulation(), s.getX() + 27, s.getY() + 27);
                }
				try {
					image = ImageIO.read(new File("Graphics\\Ant\\AntV4.png"));

				} catch (IOException e) {
					e.printStackTrace();
				}
                for(Ant a : gameState.getAnts()) {
                	double rotationRequired = getRotationRequired(a);
                	//rotationRequired = a.getAngle() + Math.PI;
                	double locationX = image.getWidth() / 2;
                	double locationY = image.getHeight() / 2;
                	AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                	
                    g.drawImage(op.filter(image, null), a.getPosX(), a.getPosY(), 82, 70, null);
                }
            }
            
            public double getRotationRequired(Ant a)
            {
            	if(a.getCurrentMapObject() == 3)
            	{
            		return 0;
            	}
            	else if(a.getCurrentMapObject() == 4)
            	{
            		return Math.PI / 2;
            	}
            	else if(a.getCurrentMapObject() == 5)
            	{
            		return Math.PI / 4 * -1;
            	}
            	else if(a.getCurrentMapObject() == 6)
            	{
            		return Math.PI / 4 * -1;
            	}
            	else if(a.getCurrentMapObject() == 7)
            	{
            		return Math.PI / 4;
            	}
            	else if(a.getCurrentMapObject() == 8)
            	{
            		return 0;
            	}
            	else
        		{
        			return 0;
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
			gameFrame.updateThisFrame();

		}
	}
}

