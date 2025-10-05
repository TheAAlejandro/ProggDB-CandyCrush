import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

// Main Constructor for LalaCandy object which comprises of different objects defined on their individual classes 
public class LalaCandy extends DrawingObject {
    private int size;
    private int lineSpacing;
    private List<DrawingObject> candyParts = new ArrayList<>(); // Append the "parts" of Lala candy in an array list for faster appending
    private Rectangle base;

    public LalaCandy(int x, int y, int size, int lineSpacing) {
        // Inherit all the properties of the main class
        super(
            x, y,
            new Color[]{new Color(117, 83, 101)}, // base color
            false, true, Color.BLACK, 1f,
            false, false, false, 0,0,0,0,0
        );
        this.size = size;
        this.lineSpacing = lineSpacing;
        buildCandy(); // Build the candy
    }

    private void buildCandy() {
        // Part 1: Construct the shadow of the candy and append it to the array
        Rectangle shadow = new Rectangle(
            (int)(x - size + size/4 - 1.5),
            (int)(y - size + size/2 + 1.5),
            (int)(1 * size + 2),
            (int)(0.5 * size + 2),
            new Color[]{Color.BLACK}, false, false, null, 0f,
            false, false, false, 0,0,0,0,0); // No border, solid fill, must be pixels away from where the base will be placed
        candyParts.add(shadow);

        // Part 2: Construct the base structure for Lala Candy
        int rectX = (int)(x - size + size/4); // Note: (int) structure forces the values to be on that datatype
        int rectY = (int)(y - size + size/2);
        int rectWidth = (int)(1 * size);
        int rectHeight = (int)(0.5 * size);

        // Define the base as a rectangle object by calling the Rectangle class
        base = new Rectangle(
            rectX, rectY, rectWidth, rectHeight,
            new Color[]{new Color(117, 83, 101)}, false,
            true, Color.BLACK, 1f, false, false, false,
            0,0,0,0,0);
        candyParts.add(base);
    }

    // Draw the candy graphics by incorporating the base, shadow, and all other ingredients
    @Override
    public void draw(Graphics2D g2d) {
        // Draw shadow & base first
        for (DrawingObject obj : candyParts) {
            obj.draw(g2d);}
 
        // Clip to base rectangle using getBounds2D() [Why clip: So that the lines that we will draw are only bounded on the base rectangle]
        Shape oldClip = g2d.getClip();
        Rectangle2D bounds = base.getBounds2D();
        g2d.clip(bounds);

        // Get the start and end coordinates to where we draw the lines
        int startX = (int)(bounds.getX() - bounds.getHeight());
        int endX = (int)(bounds.getX() + bounds.getWidth() + bounds.getHeight());

        // Iterate over an equally spaced values to which we can draw the lines (using Line class)
        for (int i = startX; i < endX; i += lineSpacing) {
            // Shadow lines (darker violet colors that are thick)
            new Line(i, (int)bounds.getY(), i + (int)bounds.getHeight(), (int)bounds.getY() + (int)bounds.getHeight(), Color.BLACK, 3f).draw(g2d);
            new Line(i, (int)bounds.getY(), i - (int)bounds.getHeight(), (int)bounds.getY() + (int)bounds.getHeight(), Color.BLACK, 3f).draw(g2d);}
        
        for (int i = startX; i < endX; i += lineSpacing) {
            // Highlights (thin lines which can mimic a border-ish style)
            new Line(i, (int)bounds.getY() - 1, i + (int)bounds.getHeight(), (int)bounds.getY() + (int)bounds.getHeight() - 1, new Color(132,93,114), 1.5f).draw(g2d);
            new Line(i, (int)bounds.getY() - 1, i - (int)bounds.getHeight(), (int)bounds.getY() + (int)bounds.getHeight() - 1, new Color(132,93,114), 1.5f).draw(g2d);}
            
        for (int i = startX; i < endX; i += lineSpacing) {
            // Dark emboss (lines placed on top of the shadow lines to recreate the embossed effect)
            new Line(i, (int)bounds.getY() - 1, i + (int)bounds.getHeight(), (int)bounds.getY() + (int)bounds.getHeight() - 1, new Color(117,83,101), 0.5f).draw(g2d);
            new Line(i, (int)bounds.getY() - 1, i - (int)bounds.getHeight(), (int)bounds.getY() + (int)bounds.getHeight() - 1, new Color(117,83,101), 0.5f).draw(g2d);}

        // Restore the old clip
        g2d.setClip(oldClip);

        // Redraw outline
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(bounds);
    }
}
