import java.awt.*;
import java.awt.geom.*;

public class WhiteRabbitCandy extends GameShape{
    private int dx = (int)(0.07 * size);

    public WhiteRabbitCandy(double x, double y, double size) {
        super(x, y, size, 6);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Define absolute positions for shapes 
        Ellipse2D.Double leftEllipse = new Ellipse2D.Double(x - size + size/2 - dx, y - size + size/2, 0.15 * size, 0.5 * size);
        Rectangle2D.Double whiteRabbitBase = new Rectangle2D.Double(x - size + size/2, y - size + size/2, 0.9 * size, 0.5 * size);
        Ellipse2D.Double rightEllipse = new Ellipse2D.Double(x - size + size/2 + 0.9 * size - dx, y - size + size/2, 0.15 * size, 0.5 * size);

        // Merge them into one shape
        Area candyBody = new Area(leftEllipse);
        candyBody.add(new Area(whiteRabbitBase));
        candyBody.add(new Area(rightEllipse));

        // Generate the shadow
        g2d.setColor(new Color(0, 0, 0, 50)); 
        AffineTransform shadowTransform = AffineTransform.getTranslateInstance(-3, 5); // offset
        Shape shadow = shadowTransform.createTransformedShape(candyBody);
        g2d.fill(shadow);

        // Gradient covering the full candy height
        LinearGradientPaint rectGradient = new LinearGradientPaint(
            (float)(x - size + size/2),            // gradient start X
            (float)(y - size + size/2),            // start Y (top of candy)
            (float)(x - size + size/2),            // same X
            (float)(y - size + size/2 + 0.5*size), // end Y (bottom of candy)
            new float[]{0f, 1f},
            new Color[]{new Color(240,230,187), new Color(204,159,79)}
        );

        // Apply gradient
        g2d.setPaint(rectGradient);
        g2d.fill(leftEllipse);
        g2d.fill(whiteRabbitBase);
        g2d.fill(rightEllipse);
        
        // Generate the border
        g2d.setColor(new Color(75, 39, 18));  
        g2d.setStroke(new BasicStroke(0.5f));
        g2d.draw(candyBody);
    }
}