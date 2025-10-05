import java.awt.*;
import java.awt.geom.*;

// Constructor for Rounded Rectangle objects
public class RoundedRectangle extends DrawingObject {
    private int width, height;
    private int arcWidth, arcHeight;
    public RoundedRectangle(
        int x, int y, int width, int height,
        int arcWidth, int arcHeight,
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
        // Properties unique for this shape
        this.width = width;
        this.height = height;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public void draw(Graphics2D g2d) {
        RoundRectangle2D.Double rect =
            new RoundRectangle2D.Double(x, y, width, height, arcWidth, arcHeight);
            g2d.setPaint(createPaint());
            g2d.fill(rect);

        // Outline would only be activitated if its boolean value is True
        if (outline) {
            g2d.setColor(colorOutline);
            g2d.setStroke(new BasicStroke(outlineThickness));
            g2d.draw(rect);
        }
    }
}
