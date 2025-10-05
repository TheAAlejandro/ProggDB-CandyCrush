import java.awt.*;
import java.awt.geom.Path2D;

public class CurvedTriangle extends DrawingObject {
    private int x1, y1; // start point
    private int x2, y2; // end point
    private int ctrl1X, ctrl1Y; // first Bezier control point
    private int ctrl2X, ctrl2Y; // second Bezier control point

    private Path2D.Double triangle; // Shape field (like ellipse in Ellipse.java)

    public CurvedTriangle(
        int x1, int y1,
        int x2, int y2,
        int ctrl1X, int ctrl1Y,
        int ctrl2X, int ctrl2Y,
        Color[] colors,
        boolean gradient,
        boolean outline, Color colorOutline,
        float outlineThickness,
        boolean vertGradient, boolean horizGradient, boolean radialGradient,
        int dx_color1, int dy_color1,
        int dx_color2, int dy_color2,
        float radius
    ) {
        super(x1, y1, colors,
              gradient,
              outline, colorOutline, outlineThickness,
              vertGradient, horizGradient, radialGradient,
              dx_color1, dy_color1, dx_color2, dy_color2, radius);

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.ctrl1X = ctrl1X;
        this.ctrl1Y = ctrl1Y;
        this.ctrl2X = ctrl2X;
        this.ctrl2Y = ctrl2Y;

        // Build the curved triangle shape once
        triangle = new Path2D.Double();
        triangle.moveTo(x1, y1);
        triangle.curveTo(ctrl1X, ctrl1Y, ctrl2X, ctrl2Y, x2, y2);
        triangle.lineTo(x1, y2);  // close to form a triangle
        triangle.closePath();
    }

    // Method to return the Shape
    public Shape getShape() {
        return triangle;
    }

    @Override
    public void draw(Graphics2D g2d) {
        triangle = new Path2D.Double();
        triangle.moveTo(x1, y1);
        triangle.curveTo(ctrl1X, ctrl1Y, ctrl2X, ctrl2Y, x2, y2);
        triangle.lineTo(x1, y2);  // close to form a triangle
        triangle.closePath();
        
        // --- Fill ---
        if (gradient && colors.length == 2) {
            g2d.setPaint(createPaint());
        } else {
            g2d.setPaint(colors[0]); // solid fill
        }
        g2d.fill(triangle);

        // --- Outline ---
        if (outline) {
            g2d.setColor(colorOutline);
            g2d.setStroke(new BasicStroke(outlineThickness));
            g2d.draw(triangle);
        }
    }
}
