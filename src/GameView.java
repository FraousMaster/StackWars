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
import Global.Resources;

public class GameView extends Observable implements Observer{

    private GameState gameState;
    private GameFrame gameFrame;
    private int sprite = 1;
    
    public GameView(GameState gameState){
        gameFrame = new GameFrame();
        this.gameState = gameState;
    }
    public void updateFrame()
    {
    	gameFrame.updateThisFrame();
    }
    
    public class GameFrame extends JFrame {
    	
        private int D_W = Resources.getWidth();
        private int D_H = Resources.getHeight();
        int x = 0;
        int y = 0;

        DrawPanel drawPanel = new DrawPanel();

        public GameFrame() {
            drawPanel.addMouseListener(new MouseAdapter(){
            	public void mouseClicked(MouseEvent e)
            	{
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
			drawPanel.repaint();
        }
                
        private class DrawPanel extends JPanel {

			private static final long serialVersionUID = 1L;
			private BufferedImage image;
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gameState.getMap().getImage(), 0, 0, D_W, D_H, null);
                for(Stack s : gameState.getStacks())
                {
                	if(s.getOwnedBy() == 1)
                	{
                		g.setColor(Color.GREEN);
                		if(s.isSelected()){
							g.drawRect(s.getX(), s.getY() ,Resources.getScalingFactorX(), Resources.getScalingFactorY());
						}
                	}
                	else if(s.getOwnedBy() == 2)
                	{
                		g.setColor(Color.RED);
						if(s.isSelected()){
							g.drawRect(s.getX(), s.getY() ,Resources.getScalingFactorX(), Resources.getScalingFactorY());
						}
                	}
                	else if(s.getOwnedBy() == 3)
                	{
                		g.setColor(Color.BLUE);
						if(s.isSelected()){
							g.drawRect(s.getX(), s.getY() ,Resources.getScalingFactorX(), Resources.getScalingFactorY());
						}
                	}
                	else if(s.getOwnedBy() == 4)
                	{
                		g.setColor(Color.BLACK);
						if(s.isSelected()){
							g.drawRect(s.getX(), s.getY() ,Resources.getScalingFactorX(), Resources.getScalingFactorY());
						}
                	}
                	else
                	{
                		g.setColor(Color.WHITE);
                	}
                	int oX = Resources.getScalingFactorX()/2 - 20;
                	int oY = Resources.getScalingFactorY()/2 + 3;
                	g.drawString("Pop: " + s.getPopulation(), s.getX() + oX, s.getY()+ oY);
                }
				try {

                	if(sprite <= 2) {
						image = ImageIO.read(new File("Graphics/Ant/AntV1.png"));
						//sprite++;
					/*}
					else if(sprite <= 4){
						image = ImageIO.read(new File("Graphics/Ant/AntV2.png"));
                		sprite ++;
					}
					else{
						image = ImageIO.read(new File("Graphics/Ant/AntV3.png"));
						sprite = 1;
					}*/

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
                	
                    g.drawImage(op.filter(image, null), a.getPosX(), a.getPosY(), null);
                }
                
            }
            
            public double getRotationRequired(Ant a)
            {
            	if(a.getCurrentMapObject() == 3)
            	{
            		return Math.PI / 2;
            	}
            	else if(a.getCurrentMapObject() == 4)
            	{
            		return 0;
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
            		return -(Math.PI / 2);
            	}
            	else if(a.getCurrentMapObject() == 9)
            	{
            		return Math.PI;
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
			gameFrame.updateThisFrame();
		}
	}
}

