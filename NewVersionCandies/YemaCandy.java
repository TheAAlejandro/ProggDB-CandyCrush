import java.awt.*;
import java.awt.geom.*;

public class YemaCandy extends DrawingObject {
    private double size;
    private int dx = 7;
    private int dy = 10;

    // Candy parts
    private Shape rightBody, leftBody;
    private Shape rightBodyHighlight, leftBodyHighlight;
    private Shape shadow;

    public YemaCandy(int x, int y, double size) {
        super(
            x, y,
            new Color[]{new Color(117, 80, 53), new Color(255, 223, 186)}, // dark → light (radial gradient)
            true,                               // gradient enabled
            true, new Color(214, 187, 156), 1.5f, // outline enabled
            false, false, true,                 // radial gradient only
            x, y,                               // center for gradient
            0, 0,                               // unused for radial
            (float) size                        // radius
        );
        this.size = size;
        buildCandy();
    }

    // --- Builder: precompute shapes ---
    private void buildCandy() {
        // Shadow
        Rectangle2D.Double baseShadow = new Rectangle2D.Double(
            x - size/3, y - size/8,
            size - size/3, size - size/4
        );
        Rectangle2D shadowBounds = baseShadow.getBounds2D();
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(45), shadowBounds.getCenterX(), shadowBounds.getCenterY());
        shadow = at.createTransformedShape(baseShadow);

        // Right body
        Path2D.Double rb = new Path2D.Double();
        rb.moveTo(x, y + size/2);
        rb.curveTo(x + size/8, y, x + size/8, y - size/2, x + size/8, y - size + (size/4));
        rb.curveTo(x + size/4, y - size/8, x + size/3, y - size/64, x + size/2, y + size/4);
        rb.curveTo(x + size/2 - (size/256), y + size/4 - (size/256), x + size/4, y + size/4, x, y + size/2);
        rb.closePath();
        rightBody = rb;

        // Left body
        Path2D.Double lb = new Path2D.Double();
        lb.moveTo(x, y + size/2);
        lb.curveTo(x + size/8, y, x + size/8, y - size/2, x + size/8, y - size + (size/4));
        lb.curveTo(x, y - size/4, x - size/4, y + size/64, x - size/2, y + size/4);
        lb.curveTo(x - size/2 + (size/528), y + size/4 + (size/128),
                   x - size/8, y + size/4 + (size/128), x, y + size/2);
        lb.closePath();
        leftBody = lb;

        // Right highlight
        Path2D.Double rbh = new Path2D.Double();
        rbh.moveTo(x + dx/2, y + size/2 - dy);
        rbh.curveTo(x + size/8, y, x + size/8 + dx, y - size/2, x + size/8, y - size + (size/4) + dy);
        rbh.curveTo(x + size/4, y - size/8 + dy, x + size/3, y - size/64, x + size/2 - dx, y + size/4);
        rbh.curveTo(x + size/2 - (size/256) - dx/2, y + size/4 - (size/256) - dy/4,
                    x + size/4, y + size/4, x + dx/2, y + size/2 - dy);
        rbh.closePath();
        rightBodyHighlight = rbh;

        // Left highlight
        Path2D.Double lbh = new Path2D.Double();
        lbh.moveTo(x - dx/2, y + size/2 - dy);
        lbh.curveTo(x + size/8, y, x + size/8 - dx, y - size/2, x + size/8, y - size + (size/4) + dy);
        lbh.curveTo(x, y - size/4 + dy, x - size/4, y + size/64, x - size/2 + dx, y + size/4);
        lbh.curveTo(x - size/2 + (size/528) + dx/2, y + size/4 + (size/128) - dy/4,
                    x - size/8, y + size/4 + (size/128), x - dx/2, y + size/2 - dy);
        lbh.closePath();
        leftBodyHighlight = lbh;
    }

    // Method that will draw the Yema Candy
    // Note: The manner of drawing the Yema candy is adjusted (different from other candies) because of how we draw Paths
    @Override
    public void draw(Graphics2D g2d) {
        // Shadow
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fill(shadow);

        // Base candy (light fill)
        g2d.setColor(new Color(255, 223, 186));
        g2d.fill(rightBody);
        g2d.fill(leftBody);

        // Highlights with radial gradient from DrawingObject
        g2d.setPaint(createPaint());
        g2d.fill(rightBodyHighlight);
        g2d.fill(leftBodyHighlight);

        // Outline
        if (outline) {
            g2d.setColor(colorOutline);
            g2d.setStroke(new BasicStroke(outlineThickness));
            g2d.draw(rightBody);
            g2d.draw(leftBody);
        }
    }
}