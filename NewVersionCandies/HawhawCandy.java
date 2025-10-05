import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HawhawCandy extends DrawingObject {
    private int size;
    private int dx, dy;
    private int arcLeft, arcRight;

    private List<DrawingObject> parts = new ArrayList<>();
    private RoundedRectangle body; // reference to main candy body

    public HawhawCandy(int x, int y, int size) {
        super(
            x, y,
            new Color[]{new Color(245, 255, 247)}, // base color
            false, true, Color.BLACK, 0.5f,
            false, false, false, 0,0,0,0,0
        );

        this.size = size;
        this.dx = (int)(0.06 * size);
        this.dy = (int)(0.06 * size);
        this.arcLeft = (int)(0.25 * size);
        this.arcRight = (int)(0.25 * size);

        buildCandy();
    }

    private void buildCandy() {
        // --- Shadow behind main body ---
        parts.add(new RoundedRectangle(
            x - 2*dx, y + (int)0.5*dy, 
            (int)(0.6*size), (int)(0.9*size),
            arcLeft, arcRight,
            new Color[]{new Color(235, 245, 237)}, // colors
            false, true, Color.BLACK, 0.5f,       // gradient, outline, outline color, thickness
            false, false, false,                   // vert, horiz, radial gradient
            0,0,0,0,0f                  // rounded corners
        ));

        // --- Main body ---
        body = new RoundedRectangle(
            x - dx, y - dy,
            (int)(0.6*size), (int)(0.9*size),
            arcLeft, arcRight,
            new Color[]{new Color(245, 255, 247)},
            false, true, Color.BLACK, 0.5f,
            false, false, false,
            0,0,0,0,0f
        );
        parts.add(body);

        // --- Inner shadow ---
        parts.add(new RoundedRectangle(
            x + dx, y + dy,
            (int)(0.4*size), (int)(0.7*size),
            arcLeft, arcRight,
            new Color[]{new Color(193, 231, 219)},
            false, true, new Color(193,231,219), 0.25f,
            false, false, false,
            0,0,0,0,0f
        ));

        // --- Inner light body ---
        parts.add(new RoundedRectangle(
            x + 2*dx, y + dy,
            (int)(0.4*size - dx), (int)(0.7*size - dy),
            arcLeft, arcRight,
            new Color[]{new Color(245, 255, 247)},
            false, true, new Color(193,231,219), 0.25f,
            false, false, false,
            0,0,0,0,0f
        ));
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (DrawingObject obj : parts) {
            obj.draw(g2d);
        }
    }
}
