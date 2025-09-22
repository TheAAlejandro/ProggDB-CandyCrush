import java.awt.*;
import java.awt.geom.*;

public class YemaCandy{
    private double x;
    private double y;
    private double size;
    private Color color;
    private int dx = 7;
    private int dy = 10;

    public YemaCandy(double x, double y, double size){
        // Initialize position as the center of the potchi
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void drawYema(Graphics2D g2d){
        // Add shadow to the yema candy
        g2d.setColor(new Color(0, 0, 0, 50));
        Rectangle2D.Double shadow = new Rectangle2D.Double(x - size/3, y - size/8, size - size/3, size - size/4); 
        Rectangle2D shadowBounds = shadow.getBounds2D();

        // Do Affline Transform to skew the shadow
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(45), shadowBounds.getCenterX(), shadowBounds.getCenterY());
        Shape skewedShadow = at.createTransformedShape(shadow);
        g2d.fill(skewedShadow);

        // Draw the main body of the Yema candy (side view)
        g2d.setColor(new Color(255, 223, 186)); // Light yellow color for Yema

        // Draw the right body of yema
        Path2D.Double rightBody = new Path2D.Double();
        rightBody.moveTo(x, y + size/2);
        rightBody.curveTo(x + size/8, y, x + size/8, y - size/2, x + size/8, y - size + (size/4));
        rightBody.curveTo(x + size/4, y - size/8, x + size/3, y - size/64, x + size/2, y + size/4);
        rightBody.curveTo(x + size/2 - (size/256), y + size/4 - (size/256), x + size/4, y + size/4, x, y + size/2);
        rightBody.closePath();
        g2d.fill(rightBody);
        g2d.setStroke(new BasicStroke(1));

        // Draw the left body of yema
        Path2D.Double leftBody = new Path2D.Double();
        leftBody.moveTo(x, y + size/2);
        leftBody.curveTo(x + size/8, y, x + size/8, y - size/2, x + size/8, y - size + (size/4));        
        leftBody.curveTo(x, y - size/4, x - size/4, y + size/64, x - size/2, y + size/4);
        leftBody.curveTo(x - size/2 + (size/528), y + size/4 + (size/128), x - size/8, y + size/4 + (size/128), x, y+ size/2);
        leftBody.closePath();
        g2d.fill(leftBody);

        // Draw the highlights inside of the right body of yema
        g2d.setColor(new Color(117, 80, 53));
        Path2D.Double rightBodyHighlight = new Path2D.Double();
        rightBodyHighlight.moveTo(x + dx/2, y + size/2 - dy);
        rightBodyHighlight.curveTo(x + size/8, y, x + size/8 + dx, y - size/2, x + size/8, y - size + (size/4) + dy);
        rightBodyHighlight.curveTo(x + size/4, y - size/8 + dy, x + size/3, y - size/64, x + size/2 - dx, y + size/4);
        rightBodyHighlight.curveTo(x + size/2 - (size/256) - dx/2, y + size/4 - (size/256) - dy/4, x + size/4, y + size/4, x + dx/2, y + size/2 - dy);
        rightBodyHighlight.closePath(); 

        // Draw the highlights inside of the left body of yema
        Path2D.Double leftBodyHighlight = new Path2D.Double();
        leftBodyHighlight.moveTo(x - dx/2, y + size/2 - dy);
        leftBodyHighlight.curveTo(x + size/8, y, x + size/8 - dx, y - size/2, x + size/8, y - size + (size/4) + dy);
        leftBodyHighlight.curveTo(x, y - size/4 + dy, x - size/4, y + size/64, x - size/2 + dx, y + size/4);
        leftBodyHighlight.curveTo(x - size/2 + (size/528) + dx/2, y + size/4 + (size/128) - dy/4, x - size/8, y + size/4 + (size/128), x - dx/2, y + size/2 - dy);
        leftBodyHighlight.closePath();
        g2d.fill(leftBodyHighlight);

        // Add the gradient inside of the highlights
        Area rightBodyAreaGrad = new Area(rightBodyHighlight);
        Area leftBodyAreaGrad = new Area(leftBodyHighlight);

        // Define the gradient
        Rectangle gradientBounds = rightBodyAreaGrad.getBounds();
        float centerX = gradientBounds.x + gradientBounds.width / 2f;
        float centerY = gradientBounds.y + gradientBounds.height / 2f;
        float radius = 2 * Math.min(gradientBounds.width, gradientBounds.height);

        float[] dist = {0.0f, 1.0f};
        Color[] colors = {new Color(117, 80, 53), new Color(255, 223, 186)};

        RadialGradientPaint radialPaint = new RadialGradientPaint(
            new Point2D.Float(centerX, centerY),
            radius,
            dist,
            colors
        );

        // Fill the saved Area with gradient
        g2d.setPaint(radialPaint);
        g2d.fill(rightBodyAreaGrad);
        g2d.fill(leftBodyAreaGrad);

        // Set the strokes
        g2d.setColor(new Color(214, 187, 156));
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.draw(rightBody);
        g2d.draw(leftBody);
        }}