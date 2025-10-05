import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Main constructor to build the Potchi candy
public class PotchiCandy extends DrawingObject {
    private double size;
    private int sugarCount = 300; // number of sugar dots
    private Random rand = new Random();
    private List<DrawingObject> potchiParts = new ArrayList<>(); //arraylist where we will store all the parts of potchi
    private Ellipse bottomPart, topPart;

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
        
        // Add the Shadow to our container
        Ellipse shadow = new Ellipse(
                    (int)(x - size/2 - 2*shadowOffsetX),
                    (int)(y - size/2 + 2*shadowOffsetY),
                    (int)(size - size/16), (int)(size - size/3),
                    new Color[]{new Color(0, 0, 0, 50)}, false, false, 
                    null, 0,
                    false, false, false, 0,0,0,0,0f);
        potchiParts.add(shadow);

        // Define the bottom part of our candy
        bottomPart = new Ellipse(
            (int)(x - size/2), (int)(y - size/2 + size/16),
            (int)(size - size/8), (int)(size - size/3),
            new Color[]{Color.WHITE}, false, false, null, 0f, false, false, false,
            0,0,0,0,0f);
        potchiParts.add(bottomPart);

        // Define the top part of our candy
        topPart = new Ellipse(
            (int)(x - size/2), (int)(y - size/2 - size/64),
            (int)(size - size/8), (int)(size - size/3),
            new Color[]{new Color(255,182,193), new Color(227,110,111)}, true,
            true, null, 0f, false, false, true,
            0,0,0,0,(float)(5*size));
        potchiParts.add(topPart);
    }

        // Draw the candy parts and we will clip everything later
        @Override
        public void draw(Graphics2D g2d){
            // Draw shadow & base first
            for (DrawingObject obj : potchiParts) {
                obj.draw(g2d);}

        // Clip the top part first so that we could draw the sugar bits on top of the candy
        Area candyShape = new Area(bottomPart.getShape());
        candyShape.add(new Area(topPart.getShape()));
        Shape oldClip = g2d.getClip();

        // Clip to candy shape so sugar dots are contained
        g2d.setClip(candyShape);

        // Scatter sugar bits
        Rectangle2D bounds = candyShape.getBounds2D();
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < sugarCount; i++) {
                    int sx, sy;
                    int attempts = 0;
                    do {
                        sx = (int)(bounds.getX() + rand.nextInt(Math.max(1, (int)bounds.getWidth())));
                        sy = (int)(bounds.getY() + rand.nextInt(Math.max(1, (int)bounds.getHeight())));
                        attempts++;
                    } while (!candyShape.contains(sx, sy) && attempts < 100);
                    g2d.fill(new Ellipse2D.Double(sx, sy, 1, 1));
                }
         
        // Restore clip
        g2d.setClip(oldClip);

        // --- Outline around full candy ---
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(0.5f));
        g2d.draw(candyShape);

    }
}
