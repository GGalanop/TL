import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
export DISPLAY = 0.0;

public class test {
    public static void main(String[] args) {
        // Create a JFrame (window)
        
        JFrame frame = new JFrame("Circle Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400); // Set the size of the window

        // Create a custom panel to draw a circle
        CirclePanel panel = new CirclePanel();
        frame.add(panel); // Add the panel to the frame

        frame.setVisible(true); // Make the window visible
    }
}

class CirclePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the drawing color
        g.setColor(Color.RED);
        // Draw a circle (oval with equal width and height)
        g.fillOval(100, 100, 200, 200); // x, y, width, height
    }
}
