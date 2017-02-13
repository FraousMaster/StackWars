import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameView {

    public GameView(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame();
            }
        });
    }


    public class GameFrame extends JFrame {

        private static final int D_W = 2000;
        private static final int D_H = 2000;
        int x = 0;
        int y = 0;

        DrawPanel drawPanel = new DrawPanel();

        public GameFrame() {
            ActionListener listener = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    if (x >= D_W) {
                        x = 0;
                        drawPanel.repaint();
                    }
                    else {
                        x += 10;
                        drawPanel.repaint();
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

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                g.fillRect(x, y, 50, 50);
            }

            public Dimension getPreferredSize() {
                return new Dimension(D_W, D_H);
            }
        }
    }

}
