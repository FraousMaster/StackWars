import javax.swing.*;
import java.awt.*;



public class GraphicsGen extends JPanel{
    private BufferedImage image;
    GraphicsGen() {
        try
        {
            image = ImageIO.read(new File("Graphics\\Stack.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.drawImage(image, 100, 300, null);
        }
}

