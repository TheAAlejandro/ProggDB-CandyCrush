import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PolvoronCandy extends DrawingObject {
    private double size;
    private double dx = 5;
    private double dy = 5;
    private int toppingsCount = 300;
    private Random rand = new Random();
    private List<DrawingObject> polvoronParts = new ArrayList<>();
    private List<Point> sugarBits = new ArrayList<>();  // sugar coordinates
    private Ellipse bottomBody, topBody;

    public PolvoronCandy(int x, int y, double size) {
        super(x, y,
              new Color[]{new Color(252, 235, 215)}, // base color
              false, true, new Color(145,123,115), 0.5f, // outline
              false, false, false,
              0,0,0,0,0f);

        this.size = size;
        buildCandy();
    }

    // Method to build our candy
    private void buildCandy() {
        // Component #1: Create the shadow of the candy
        Ellipse shadow = new Ellipse(
            (int)(x - size/2 - dx),
            (int)(y - size + size/4 + 2*dy),
            (int)(0.9*size),
            (int)(0.9*size),
            new Color[]{new Color(0,0,0,50)}, false, false,
            null, 0,
            false, false, false, 0,0,0,0,0f);
        polvoronParts.add(shadow);

        // Component #2: Bottom Part of our Candy
        bottomBody = new Ellipse(
            (int)(x - size/2),
            (int)(y - size + size/4 + 1.5*dy),
            (int)(0.9*size),
            (int)(0.9*size),
            new Color[]{new Color(252, 235, 215)}, false, true,
            Color.BLACK, 0.25f,
            false, false, false, 0,0,0,0,0f);
        polvoronParts.add(bottomBody);

        // Component #3: Top Part of the candy
        topBody = new Ellipse(
            (int)(x - size/2),
            (int)(y - size + size/4),
            (int)(0.9*size),
            (int)(0.9*size),
            new Color[]{new Color(252, 235, 215)}, false, true,
            Color.BLACK, 0.25f,
            false, false, false, 0,0,0,0,0f);
        polvoronParts.add(topBody);

        // Combine bottom and top into one Area
        Area candyShape = new Area(bottomBody.getShape());
        candyShape.add(new Area(topBody.getShape()));

        // Generate sugar once for the combined shape
        generateSugarBits(candyShape);  
    }

    // Generate sugar coordinates inside a given shape
    private void generateSugarBits(Shape candyShape) {
        sugarBits.clear();
        Rectangle2D bounds = candyShape.getBounds2D();

        for (int i = 0; i < toppingsCount; i++) {
            int sx, sy;
            int attempts = 0;
            do {
                sx = (int)(bounds.getX() + rand.nextInt(Math.max(1, (int)bounds.getWidth())));
                sy = (int)(bounds.getY() + rand.nextInt(Math.max(1, (int)bounds.getHeight())));
                attempts++;
            } while (!candyShape.contains(sx, sy) && attempts < 100);

            sugarBits.add(new Point(sx, sy));
        }
    }

    // Draw the candy and sugar
    @Override
    public void draw(Graphics2D g2d) {
        // Draw shadow & body
        for (DrawingObject obj : polvoronParts) {
            obj.draw(g2d);
        }

        // Clip sugar bits to the top body
        Area candyShape = new Area(topBody.getShape());
        candyShape.add(new Area(bottomBody.getShape()));
        Shape oldClip = g2d.getClip();
        g2d.setClip(candyShape);

        // Add the sugar bits
        for (Point p : sugarBits) {
            Ellipse sugar = new Ellipse(
                p.x, p.y, 1, 1,
                new Color[]{Color.BLACK}, false, false,
                null, 0f, false, false, false, 
                0,0,0,0,0f
            );
            sugar.draw(g2d);  // use your DrawingObject’s draw method
        }


        // Restore clip
        g2d.setClip(oldClip);
    }
}
