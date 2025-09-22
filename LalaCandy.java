import java.awt.*;
import java.awt.geom.*;

public class LalaCandy{
    private double x;
    private double y;
    private double size;
    private int lineSpacing = 15;

    public LalaCandy(double x, double y, double size, int lineSpacing){
        // Initialize position as the center of the lala candy
        this.x = x;
        this.y = y;
        this.size = size;
        this.lineSpacing = lineSpacing;
    }
    public void drawLala(Graphics2D g2d){
        // Add shadow to the lala candy
        g2d.setColor(Color.BLACK);
        Rectangle2D.Double shadow = new Rectangle2D.Double(x - size + size/4 - 1.5, y - size + size/2 + 1, 1 * size + 2, 0.5 * size + 2);
        Rectangle2D shadowBounds = shadow.getBounds2D();
        g2d.fill(shadow);

        // Draw the base rectangle of the lala candy
        g2d.setColor(new Color(117, 83, 101)); // Light yellow color for Lala
        Rectangle2D.Double lalaBase = new Rectangle2D.Double(x - size + size/4, y - size + size/2, 1 * size, 0.5 * size);
        g2d.fill(lalaBase);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.draw(lalaBase);

        // Clip the area so that the lines will only be drawn within the rectangle
        g2d.setClip(lalaBase);

        // Setup the drawing board for the lines
        Area baseLalaCandy = new Area(lalaBase);
        int rectX = baseLalaCandy.getBounds().x;
        int rectY = baseLalaCandy.getBounds().y;
        int rectWidth = baseLalaCandy.getBounds().width;
        int rectHeight = baseLalaCandy.getBounds().height;

        // Draw the diagonal lines from top-right to bottom-left
        int startX = rectX - rectHeight; // Start before the rectangle
        int endX = rectX + rectWidth + rectHeight; // End after the rectangle

        // First for loop to generate the diagonal shadow lines
        for (int i = startX; i < endX; i += lineSpacing) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3.0f));
            g2d.drawLine(i, rectY, i + rectHeight, rectY + rectHeight); // (\)
            g2d.drawLine(i, rectY, i - rectHeight, rectY + rectHeight); // (/)
        }

        // Second for-loop to generate lighter highlights for the embossed effect
        for (int i = startX; i < endX; i += lineSpacing) {
            g2d.setColor(new Color(132, 93, 114));
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawLine(i, rectY - 1, i + rectHeight, rectY + rectHeight - 1); // (\)
            g2d.drawLine(i, rectY - 1, i - rectHeight, rectY + rectHeight - 1); // (/)
        }

        // Third for-loop to generate darker lines for the embossed effect
        for (int i = startX; i < endX; i += lineSpacing) {            
            g2d.setColor(new Color(117, 83, 101));
            g2d.setStroke(new BasicStroke(0.5f));
            g2d.drawLine(i, rectY - 2, i + rectHeight, rectY + rectHeight - 2); // (\)
            g2d.drawLine(i, rectY - 2, i - rectHeight, rectY + rectHeight - 2); // (/)
        }

        // Clear the clip and add the black outline again
        g2d.setClip(null);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(lalaBase);
    }
}