import java.awt.*;
import javax.swing.*;

//Helper class for putting the shape inside the button for easy repainting and accessing

public class ShapeButton extends JButton {
    private GameShape shape;

    public ShapeButton(GameShape s) {
        this.shape = s;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(true);
    }

    public void setShape(GameShape s) {
        this.shape = s;
        repaint();
    }

    public GameShape getShape() {
        return shape;
    }

    // For rendering the shape on the button
    @Override
    protected void paintComponent(Graphics g) {
        if (shape != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            shape.draw(g2); 
            g2.dispose();
        }
    }
}
