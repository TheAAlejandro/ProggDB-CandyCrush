import java.awt.*;
import java.awt.geom.*;

public class hawHawCandy{
    private double x;
    private double y;
    private double size;
    private int dx;
    private int dy;
    private int arcLeft;
    private int arcRight;

    public hawHawCandy(double x, double y, double size){
        // Initialize position as the center of the potchi
        this.x = x;
        this.y = y;
        this.size = size;
        this.dx = (int)(0.06 * size);
        this.dy = (int)(0.06 * size);
        this.arcLeft = (int)(0.25 * size);
        this.arcRight = (int)(0.25 * size);
    }

    public void drawHawHaw(Graphics2D g2d){
        // Translate first the points
        g2d.translate(x - size/4 - size/8, y - size + size/4);

        // Generate the hawhaw candy shadow
        g2d.setColor(new Color(235, 245, 237));
        RoundRectangle2D hawhawShadow = new RoundRectangle2D.Double(-3*dx, dy,  0.6*size, 0.9*size, arcLeft, arcRight);
        g2d.fill(hawhawShadow);

        // Generate the hawhaw candy body
        g2d.setColor(new Color(245, 255, 247));
        RoundRectangle2D hawhawBody = new RoundRectangle2D.Double(-dx, -dy, 0.6*size, 0.9*size, arcLeft, arcRight);
        g2d.fill(hawhawBody);

        // Generate the shadow inside the candy first
        g2d.setColor(new Color(193, 231, 219));
        RoundRectangle2D hawhawInnerShadow = new RoundRectangle2D.Double(dx, dy, 0.4*size, 0.7*size, arcLeft, arcRight);
        g2d.fill(hawhawInnerShadow);

        // Generate the light body inside the shadow
        g2d.setColor(new Color(245, 255, 247));
        RoundRectangle2D hawhawInnerBody = new RoundRectangle2D.Double(2*dx, dy, 0.4*size - dx, 0.7*size - dy, arcLeft, arcRight);
        g2d.fill(hawhawInnerBody);
        g2d.setColor(new Color(193, 231, 219));
        g2d.setStroke(new BasicStroke(0.25f));
        g2d.draw(hawhawInnerBody);

        // Generate the border of the whole candy
        g2d.setStroke(new BasicStroke(0.5f));
        g2d.draw(hawhawBody);
    }
}
