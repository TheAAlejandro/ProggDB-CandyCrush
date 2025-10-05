import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends DrawingObject {
    private int width, height;
    private Ellipse2D.Double ellipse;

    public Ellipse(
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
        super(x, y, colors,
              gradient,
              outline, colorOutline, outlineThickness,
              vertGradient, horizGradient, radialGradient,
              dx_color1, dy_color1, dx_color2, dy_color2, radius);
        this.width = width;
        this.height = height;

        ellipse = new Ellipse2D.Double(x, y, width, height);
    }

    // Constructor to return the shape of the Ellipse
    public Shape getShape(){
        return ellipse;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, width, height);

        // --- Fill ---
        if (gradient && colors.length == 2) {
            g2d.setPaint(createPaint());
        } else {
            g2d.setPaint(colors[0]); // solid fill
        }
        g2d.fill(ellipse);

        // --- Outline ---
        if (outline) {
            g2d.setColor(colorOutline);
            g2d.setStroke(new BasicStroke(outlineThickness));
            g2d.draw(ellipse);
        }
    }
}
