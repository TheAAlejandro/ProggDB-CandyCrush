import java.awt.*;
import javax.swing.*;

public class DrawingCanvas extends JPanel { // Use JPanel for drawing
    private DrawingObject shape; // use DrawingObject as a Datatype

    public DrawingCanvas(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        // Initialize candy for sample
        this.shape = new LalaCandy(150, 150, 45, 15);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        // Enable antialiasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        shape.draw(g2d); // Call the custom draw method
    }
}