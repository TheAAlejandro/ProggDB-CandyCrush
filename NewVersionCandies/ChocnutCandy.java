import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Main constructor for Chocnut candy
public class ChocnutCandy extends DrawingObject {
    private double size;
    private List<DrawingObject> chocnutParts = new ArrayList<>(); // ArrayList which would contain all the shapes needed for our chocnut parts
    private Rectangle baseChocnut;

    public ChocnutCandy(int x, int y, double size) {
        super(x, y,
              new Color[]{new Color(164,107,69), new Color(193,138,98)}, // gradient base colors
              true, false, Color.BLACK, 1f,
              false, true, false,
              0,0,0,0,0f);
        this.size = size;
        buildCandy();
    }

    // Constructor to build our candy
    private void buildCandy() {
        int rectX = (int)(x - size + size/4);
        int rectY = (int)(y - size + size/2);
        int rectWidth = (int)size;
        int rectHeight = (int)(0.5 * size);

        // Component 1: Shadow for our chocnut candy 
        chocnutParts.add(new Rectangle(
            rectX - 4, rectY + 1, rectWidth + 3, rectHeight + 3,
            new Color[]{new Color(36, 19, 9)}, false, false, null, 0f,
            false,false,false,0,0,0,0,0));

        // Component 2: Main body of our chocnut candy
        baseChocnut = new Rectangle(
            rectX, rectY, rectWidth, rectHeight,
            new Color[]{new Color(145,85,47)}, false, true, Color.BLACK, 1f,
            false,false,false,0,0,0,0,0);
        chocnutParts.add(baseChocnut);

        // Component 3: Shadows for the top part of chocnut
        // Top part 
        chocnutParts.add(new Rectangle(
            rectX, rectY, rectWidth, (int)(0.17*size),
            new Color[]{new Color(72,38,18)}, false, false, null, 0f,
            false,false,false,0,0,0,0,0));

        // Middle part
        chocnutParts.add(new Rectangle(
            rectX, rectY + (int)(0.25*size), rectWidth, (int)(0.10*size),
            new Color[]{new Color(72,38,18)}, false, false, null, 0f,
            false,false,false,0,0,0,0,0));

        // Component 4: Add the embossed parts
        // Top part
        chocnutParts.add(new Rectangle(
            rectX, rectY, rectWidth, (int)(0.10*size),
            new Color[]{new Color(175,119,80), new Color(189, 133, 93)}, // gradient colors
            true,          // enable gradient
            false, null, 0f, // no outline
            false, true, false, // activate horizontal gradient
            0,0,0,0,0f));
        // Middle Part
        chocnutParts.add(new Rectangle(
            rectX, rectY + (int)(0.22*size), rectWidth, (int)(0.07*size),
            new Color[]{new Color(175,119,80), new Color(189, 133, 93)}, // gradient colors
            true,          // enable gradient
            false, null, 0f, // no outline
            false, true, false, // activate horizontal gradient
            0,0,0,0,0f));
        // Bottom Part
        chocnutParts.add(new Rectangle(
            rectX, rectY + (int)(0.37*size), rectWidth, (int)(0.09*size),
            new Color[]{new Color(189, 133, 93), new Color(175, 119, 80)}, // gradient colors
            true,          // enable gradient
            false, null, 0f, // no outline
            false, true, false, // activate horizontal gradient
            0,0,0,0,0f));        
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Single loop to draw all the parts of the Chocnut candy
        for (DrawingObject obj : chocnutParts) {
            obj.draw(g2d);
        }
    }
}
