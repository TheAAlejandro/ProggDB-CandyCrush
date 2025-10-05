import java.awt.*;
import java.awt.geom.*;

// Main constructor for Circle objects that extends DrawingObject
public class Circle extends DrawingObject {
    private int radius;

    public Circle(int x, int y, int radius,
                  Color[] colors,
                  boolean gradient,
                  boolean outline, Color colorOutline,
                  float outlineThickness,
                  boolean vertGradient, boolean horizGradient, boolean radialGradient,
                  int dx_color1, int dy_color1, int dx_color2, int dy_color2,
                  float radiusGradient) {
        // Inherits shared properties for the classes that extends Drawingobject
        super(x, y, colors, gradient, outline, colorOutline,
              outlineThickness, vertGradient, horizGradient, radialGradient,
              dx_color1, dy_color1, dx_color2, dy_color2, radiusGradient);
        // Only property that is unique in Circle shapes
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Draw the circle using Ellipse2D Double but the size is the same
        Ellipse2D.Double circle = new Ellipse2D.Double(
            x - radius, y - radius, radius, radius);
        // Fill the object (note: will return gradient if gradient boolean is True in DrawingObject)
        g2d.setPaint(createPaint());
        g2d.fill(circle);

        // Create outline if necessary (true for outline Boolean value)
        if (outline) {
            g2d.setColor(colorOutline);
            g2d.setStroke(new BasicStroke(outlineThickness));
            g2d.draw(circle);
        }
    }
}
