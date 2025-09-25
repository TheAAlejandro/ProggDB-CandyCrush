import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class PolvoronCandy extends GameShape {
    private double dx = 5;
    private double dy = 5;
    private double toppingsCount = 800;
    private Random rand = new Random();

    public PolvoronCandy(double x, double y, double size) {
        super(x, y, size, 4);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Translate first
        g2d.translate(x - size/2, y - size + size/4);

        // Generate the shadow of the polvoron candy
        g2d.setColor(new Color(0, 0, 0, 50));
        Ellipse2D.Double polvoronShadow = new Ellipse2D.Double(-1.25*dx, 1.5*dy, 0.95*size, 0.95*size);
        g2d.fill(polvoronShadow);

        // Generate the bottom body of the polvoron candy
        g2d.setColor(new Color(252, 235, 215));
        Ellipse2D.Double polvoronBodyBottom = new Ellipse2D.Double(0, 1.5*dy, 0.9 * size, 0.9 * size);
        g2d.fill(polvoronBodyBottom);

        // Generate the top body of the polvoron candy
        g2d.setColor(new Color(252, 235, 215));
        Ellipse2D.Double polvoronBodyTop = new Ellipse2D.Double(0, 0, 0.9 * size, 0.9 * size);
        g2d.fill(polvoronBodyTop);
        g2d.setColor(new Color(145, 123, 115));
        g2d.setStroke(new BasicStroke(0.35f));
        g2d.draw(polvoronBodyTop);        

        // Merge the area
        Area polvoronBody = new Area(polvoronBodyBottom);
        polvoronBody.add(new Area(polvoronBodyTop));
        g2d.setColor(new Color(145, 123, 115));
        g2d.setStroke(new BasicStroke(0.5f));
        g2d.draw(polvoronBody);

        // Generate random toppings 
        Rectangle bounds = polvoronBody.getBounds();
        Color[] sugarColors = { new Color(145, 123, 115), new Color(99, 84, 79)}; 

        for (int i = 0; i < toppingsCount; i++) {
            int sx, sy;
            do {
                sx = bounds.x + rand.nextInt(bounds.width);
                sy = bounds.y + rand.nextInt(bounds.height);
            } while (!polvoronBody.contains(sx, sy));

            // Pick a random color
            Color sugarColor = sugarColors[rand.nextInt(sugarColors.length)];
            g2d.setColor(sugarColor);

            // Draw small toppings
            g2d.fill(new Ellipse2D.Double(sx, sy, 1, 1));
        }
    }
}
