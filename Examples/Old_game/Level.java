import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Level {

    private ArrayList<Stack> listStack = new ArrayList<Stack>();
    private HashMap<Integer, ArrayList<Stack>> levelStack = new HashMap<Integer, ArrayList<Stack>>();
    private int level;

    public Level(int level) {
        addLevels();
        listStack = new ArrayList<Stack>();
        this.level =level;
    }

    private JPanel panel;
        {
            panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Iterator<Stack> iter = levelStack.get(level).iterator();
                    while (iter.hasNext()) {
                        Stack temp = iter.next();
                        g.drawImage(temp.getImage(), temp.getX(), temp.getY(), null);
                    }

                }
            };
    }

    private void addLevels() {
        int x[] = {100, 850, 500, 100, 850};
        int y[] = {100, 100, 500, 850, 850};
        for (int i = 0; i <= 4; i++) {
            Stack temp = new Stack(x[i], y[i]);
            listStack.add(temp);
        }
        levelStack.put(1, listStack);
    }

    public JPanel getPanel(){
        panel.setBackground(Color.white);
        return panel;
    }
}
