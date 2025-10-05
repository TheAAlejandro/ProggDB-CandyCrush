import java.awt.*;
import java.awt.geom.*;

// Main constructor to generate Line objects
public class Line extends DrawingObject {
    private int x2, y2;
    public Line(
        int x1, int y1, int x2, int y2,
        Color[] colors,
        boolean outline, Color color_outline,
        float outlineThickness
    ) {
        // Inherited properties
        super(x1, y1, colors,
              false, // gradient always off for lines
              outline, color_outline, outlineThickness,
              false, false, false, // gradients off
              0, 0, 0, 0, 0f);
        // Endpoints for the lines
        this.x2 = x2;
        this.y2 = y2;
    }

    // First constructor for lines that have thickness defined
    public Line(int x1, int y1, int x2, int y2, Color strokeColor, float thickness) {
        // use the full constructor: colors[], outline = true, color_outline = strokeColor
        this(x1, y1, x2, y2, new Color[]{strokeColor}, true, strokeColor, thickness);
    }

    // Second constructor if we only want the default thickness
    public Line(int x1, int y1, int x2, int y2, Color strokeColor) {
        this(x1, y1, x2, y2, strokeColor, 1f);
    }

    @Override
    public void draw(Graphics2D g2d) {
        Line2D.Double line = new Line2D.Double(x, y, x2, y2);

        // stroke color
        g2d.setPaint(createPaint());
        // stroke thickness (use outlineThickness, fallback to 1f)
        g2d.setStroke(new BasicStroke(outlineThickness > 0 ? outlineThickness : 1f));
        g2d.draw(line);
    }
}
