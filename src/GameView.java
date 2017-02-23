import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class GameView {

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
            ActionListener listener = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
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
            };
            Timer timer = new Timer(10, listener);
            timer.start();

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

}

