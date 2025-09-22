import java.awt.*;
import java.awt.geom.*;

public class ChocnutCandy{
    private double x;
    private double y;
    private double size;
    private int lineSpacing = 20;

    public ChocnutCandy(double x, double y, double size){
        // Initialize position as the center of the lala candy
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void drawChocnut(Graphics2D g2d){
        // Add shadow to the chocnut candy
        g2d.setColor(new Color(72, 38, 18));
        Rectangle2D.Double chocnutShadow = new Rectangle2D.Double(x - size + size/4 - 4, y - size + size/2, 1 * size + 5, 0.5 * size + 3);
        g2d.fill(chocnutShadow);

        // Draw the base rectangle of the chocnut candy
        g2d.setColor(new Color(145, 85, 47)); 
        Rectangle2D.Double chocnutBase = new Rectangle2D.Double(x - size + size/4, y - size + size/2, 1 * size, 0.5 * size);
        g2d.fill(chocnutBase);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.draw(chocnutBase);

        // Move the reference point of the rectangle to the top-left corner of the rectangle
        g2d.translate(x - size + size/4, y - size + size/2);
        
        // Generate the shadows
        g2d.setColor(new Color(72, 38, 18));
        Rectangle2D.Double rectAboveShadow = new Rectangle2D.Double(0, 0, size, 0.15 * size);
        g2d.fill(rectAboveShadow);

        g2d.setColor(new Color(75, 39, 18));
        Rectangle2D.Double rectMiddleShadow = new Rectangle2D.Double(0, 0.22*size, size, 0.1 * size);
        g2d.fill(rectMiddleShadow);

        // Generate the highlights for the bottom rectangle
        g2d.setColor(new Color(193, 138, 98));
        Rectangle2D.Double rectBelowHighlights = new Rectangle2D.Double(0, 0.38*size, size, 0.12 * size);
        g2d.fill(rectBelowHighlights);

        // Create the three base rectangles now
        // Generate a vertical gradient
        LinearGradientPaint verticalBodyColors = new LinearGradientPaint(
            0, 0, 0, (float)(0.15 * size),
            new float[]{0f, 1f},
            new Color[]{new Color(164, 107, 69), new Color(193, 138, 98)} 
        );
        g2d.setPaint(verticalBodyColors);
        Rectangle2D.Double rectAbove = new Rectangle2D.Double(0, 0, size, 0.12 * size);
        Rectangle2D.Double rectMiddle = new Rectangle2D.Double(0, 0.22*size, size, 0.07 * size); // Thinner than the other two
        g2d.fill(rectAbove);
        g2d.fill(rectMiddle);

        // Different color for the rectangle below
        g2d.setColor(new Color(164, 107, 69));
        Rectangle2D.Double rectBelow = new Rectangle2D.Double(0, 0.42*size, size, 0.08 * size);
        g2d.fill(rectBelow);
    }

}
