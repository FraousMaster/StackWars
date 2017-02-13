import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameFrame extends JFrame {

    private static final int D_W = 2000;
    private static final int D_H = 2000;
    int x = 0;
    int y = 0;
    private ArrayList<AntObject> ants = new ArrayList<AntObject>();

    DrawPanel drawPanel = new DrawPanel();

    public GameFrame() {
        ActionListener listener = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	for(AntObject a : ants)
            	{
	                if (a.getPosX() >= D_W) {
	                    a.setPos(0, a.getPosY());
	                    drawPanel.repaint();
	                } 
	                else {
	                    a.setPos(a.getPosX() + 10, a.getPosY());
	                    drawPanel.repaint();
	                }
            	}
            }
        };
        Timer timer = new Timer(10, listener);
        timer.start();
        
        add(drawPanel);
        for(int i = 0; i <= 2000; i += 50){
        	ants.add(new AntObject(i ,i ));
            }
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(AntObject a : ants)
            {
	            g.setColor(Color.GREEN);
	            g.fillRect(a.getPosX(), a.getPosY(), 50, 50);
            }
        }

        public Dimension getPreferredSize() {
            return new Dimension(D_W, D_H);
        }
    }
}
