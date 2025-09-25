import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class PotchiCandy extends GameShape {
    private Random rand = new Random();
    private int sugarCount = 500;

    public PotchiCandy(double x, double y, double size) {
        super(x, y, size, 0);
    }

    @Override
    public void draw(Graphics2D g2d) {

        // Create a shadow of the potchi candy
        int shadowOffsetX = 3;
        int shadowOffsetY = 5;
        g2d.setColor(new Color(0, 0, 0, 50)); 

        // Draw the shadow of the potchi candy
        Ellipse2D.Double shadowBottom = new Ellipse2D.Double(
        x - size/2 - shadowOffsetX, 
        y - (size/2) + (size/16) + shadowOffsetY, 
        size - size/16, 
        size - size/3);
        g2d.fill(shadowBottom);

        // Create the bottom part of the potchi with white as a color
        Ellipse2D.Double bottomPart = new Ellipse2D.Double(x - size/2, y - (size/2) + (size/16), size - size/8, size - size/3);
        g2d.setColor(Color.WHITE);
        g2d.fill(bottomPart);
        
        // Create the top part of the potchi with light pink and dark pink gradients
        Ellipse2D.Double topPart = new Ellipse2D.Double(x - size/2, y - size/2 - size/64, size - size/8, size - size/3);
        float centerX = (float)(x - 5);
        float centerY = (float)(y - 10);
        float radius = (float)(size /2);

        RadialGradientPaint gradient = new RadialGradientPaint(
            new Point2D.Float(centerX, centerY),
            radius,
            new float[]{0f, 1f},                       
            new Color[]{new Color(255, 182, 193),      // Light Pink
                        new Color(227, 110, 111)}      // Dark Pink
        );

        g2d.setPaint(gradient);
        g2d.fill(topPart);

        // Create Areas to form the candy for other operations
        Area candyShape = new Area(topPart);

        // Create sugar sprinkles on the top poart
        g2d.setColor(Color.WHITE);
        Rectangle bounds = candyShape.getBounds();
        for (int i = 0; i < sugarCount; i++) {
            int sx, sy;
            do {
                sx = bounds.x + rand.nextInt(bounds.width);
                sy = bounds.y + rand.nextInt(bounds.height);
            } while (!candyShape.contains(sx, sy));

            // draw small sugar dot
            g2d.fill(new Ellipse2D.Double(sx, sy, 1, 1)); 
        }

        // Merge the above area to the bottom part to create the outline of potchi
        candyShape.add(new Area(bottomPart));
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(candyShape);
    }
}