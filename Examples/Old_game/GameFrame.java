import javax.swing.*;


public class GameFrame {

    private JFrame mainFrame;
    private JPanel mainPanel;
    public GameFrame() {
        mainFrame = buildFrame();
        Level level = new Level(1);
        mainPanel = level.getPanel();
        mainPanel.add(new GraphicsGen());
        mainFrame.add(mainPanel);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);


    }
    private JFrame buildFrame() {
        JFrame frame;
        frame = new JFrame("Stack Wars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 250);
        frame.setVisible(true);
        return frame;
    }

}
