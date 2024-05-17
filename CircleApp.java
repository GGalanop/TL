import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CircleApp {
    public static void main(String[] args) {
        // Image dimensions
        int width = 400;
        int height = 400;

        // Create a buffered image
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Get the graphics object from the image
        Graphics2D g2d = bufferedImage.createGraphics();

        // Set the drawing color
        g2d.setColor(Color.RED);

        // Draw a filled circle (oval with equal width and height)
        g2d.fillOval(100, 100, 200, 200); // x, y, width, height

        // Dispose of the graphics object
        g2d.dispose();

        // Save the image to a file
        try {
            File file = new File("circle.png");
            ImageIO.write(bufferedImage, "png", file);
            System.out.println("Circle image created: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
