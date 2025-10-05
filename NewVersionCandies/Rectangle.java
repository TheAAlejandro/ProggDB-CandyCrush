import java.awt.*;
import java.awt.geom.*;

// Main constructor for Rectangle that extends DrawingObject
public class Rectangle extends DrawingObject {
    private int width, height;

    public Rectangle(
        int x, int y, int width, int height,
        Color[] colors,
        boolean gradient,
        boolean outline, Color colorOutline,
        float outlineThickness,
        boolean vertGradient, boolean horizGradient, boolean radialGradient,
        int dx_color1, int dy_color1,
        int dx_color2, int dy_color2,
        float radius
    ) {
        // Inherited properties
        super(x, y, colors,
              gradient,
              outline, colorOutline, outlineThickness,
              vertGradient, horizGradient, radialGradient,
              dx_color1, dy_color1, dx_color2, dy_color2, radius);
        // Properties unique for Rectangle objects
        this.width = width;
        this.height = height;
    }

    // Construct which returns the x, y, width, height values (useful for clipping)
    public Rectangle2D getBounds2D() {
    return new Rectangle2D.Double(x, y, width, height);
}

    @Override
    public void draw(Graphics2D g2d) {
        // Construct rectangle using Rectangle2D.Double
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        g2d.setPaint(createPaint());
        g2d.fill(rect);

        // Returns outline of rectangle if necessary
        if (outline) {
            g2d.setColor(colorOutline);
            g2d.setStroke(new BasicStroke(outlineThickness));
            g2d.draw(rect);
        }
    }
}
