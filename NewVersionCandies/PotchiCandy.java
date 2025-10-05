import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PotchiCandy extends DrawingObject {
    private double size;
    private int sugarCount = 300; // number of sugar dots
    private Random rand = new Random();
    private List<DrawingObject> potchiParts = new ArrayList<>(); // parts of the candy
    private Ellipse bottomPart, topPart;
    private List<Point> sugarBits = new ArrayList<>(); // store sugar positions

    public PotchiCandy(int x, int y, double size) {
        super(
            x, y,
            new Color[]{Color.WHITE}, // base color
            false, true, Color.BLACK, 1f, // outline
            false, false, false,
            0,0,0,0,0
        );
        this.size = size;
        buildCandy();
    }

    // Main builder for our candy
    private void buildCandy() {
        int shadowOffsetX = (int)(size * 0.06);
        int shadowOffsetY = (int)(size * 0.1);

        // Shadow
        Ellipse shadow = new Ellipse(
            (int)(x - size/2 - 2*shadowOffsetX),
            (int)(y - size/2 + 2*shadowOffsetY),
            (int)(size - size/16), (int)(size - size/3),
            new Color[]{new Color(0, 0, 0, 50)}, false, false,
            null, 0,
            false, false, false, 0,0,0,0,0f
        );
        potchiParts.add(shadow);

        // Bottom
        bottomPart = new Ellipse(
            (int)(x - size/2), (int)(y - size/2 + size/16),
            (int)(size - size/8), (int)(size - size/3),
            new Color[]{Color.WHITE}, false, false, null, 0f,
            false, false, false, 0,0,0,0,0f
        );
        potchiParts.add(bottomPart);

        // Top
        topPart = new Ellipse(
            (int)(x - size/2), (int)(y - size/2 - size/64),
            (int)(size - size/8), (int)(size - size/3),
            new Color[]{new Color(255,182,193), new Color(227,110,111)}, true,
            true, null, 0f, false, false, true,
            0,0,0,0,(float)(5*size)
        );
        potchiParts.add(topPart);

        // Generate sugar bits once for the combined shape
        Area candyShape = new Area(bottomPart.getShape());
        candyShape.add(new Area(topPart.getShape()));
        generateSugarBits(candyShape);
    }

    // Precompute sugar coordinates inside the given shape
    private void generateSugarBits(Shape candyShape) {
        sugarBits.clear();
        Rectangle2D bounds = candyShape.getBounds2D();

        for (int i = 0; i < sugarCount; i++) {
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

    // Draw the candy
    @Override
    public void draw(Graphics2D g2d) {
        // Draw shadow & base first
        for (DrawingObject obj : potchiParts) {
            obj.draw(g2d);
        }

        // Clip to candy shape (bottom + top combined)
        Area candyShape = new Area(bottomPart.getShape());
        candyShape.add(new Area(topPart.getShape()));
        Shape oldClip = g2d.getClip();
        g2d.setClip(candyShape);

        // Draw precomputed sugar bits
        g2d.setColor(Color.WHITE);
        for (Point p : sugarBits) {
            g2d.fill(new Ellipse2D.Double(p.x, p.y, 1, 1));
        }

        // Restore clip
        g2d.setClip(oldClip);

        // Outline around full candy
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(0.5f));
        g2d.draw(candyShape);
    }
}
