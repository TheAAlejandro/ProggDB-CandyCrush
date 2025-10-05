import java.awt.*;
import java.awt.geom.*;

// Main constructor for Square that extends DrawingObject
public class Square extends DrawingObject {
    private int size; // length of one side

    public Square(
        int x, int y, int size,
        Color[] colors,
        boolean gradient,
        boolean outline, Color colorOutline,
        float outlineThickness,
        boolean vertGradient, boolean horizGradient, boolean radialGradient,
        int dx_color1, int dy_color1,
        int dx_color2, int dy_color2,
        float radius
    ) {
        // Again, properties that are inherited from the main class
        super(x, y, colors, gradient, outline, colorOutline,
              outlineThickness, vertGradient, horizGradient, radialGradient,
              dx_color1, dy_color1, dx_color2, dy_color2, radius);
        // Property that is unique for this case
        this.size = size;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Use Rectangle2D.Double to create square (But keep width = height)
        Rectangle2D.Double square = new Rectangle2D.Double(x, y, size, size);
        g2d.setPaint(createPaint());
        
        // Creates outline if necessary
        if (outline) {
            g2d.setColor(colorOutline);
            g2d.setStroke(new BasicStroke(outlineThickness));
            g2d.draw(square);
        }
    }
}
