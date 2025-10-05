import java.awt.*;

public abstract class DrawingObject {
    // Shared position amongst all classes
    protected int x, y;

    // Color and Gradient properties for shapes (will be ignored once gradient is False)
    protected Color[] colors;
    protected boolean gradient;       // enable/disable fill gradient
    protected boolean vertGradient;   // vertical linear gradient
    protected boolean horizGradient;  // horizontal linear gradient
    protected boolean radialGradient; // radial gradient

    // Gradient anchor properties 
    protected int dx_color1, dy_color1, dx_color2, dy_color2;
    protected float radius; // only be used if radialGradient is True

    // Outline properties 
    protected boolean outline;
    protected Color colorOutline;
    protected float outlineThickness;

    // Main DrawingObject to be called by other classes
    public DrawingObject(
        int x, int y,
        Color[] colors,
        boolean gradient,
        boolean outline,
        Color color_outline,
        float outlineThickness,
        boolean vertGradient,
        boolean horizGradient,
        boolean radialGradient,
        int dx_color1, int dy_color1,
        int dx_color2, int dy_color2,
        float radius
    ) {
        this.x = x;
        this.y = y;
        this.colors = colors != null ? colors : new Color[]{Color.BLACK}; // safe default
        this.gradient = gradient;
        this.outline = outline;
        this.colorOutline = color_outline != null ? color_outline : Color.BLACK;
        this.outlineThickness = outlineThickness;
        this.vertGradient = vertGradient;
        this.horizGradient = horizGradient;
        this.radialGradient = radialGradient;
        this.dx_color1 = dx_color1;
        this.dy_color1 = dy_color1;
        this.dx_color2 = dx_color2;
        this.dy_color2 = dy_color2;
        this.radius = radius;
    }

    // Gradient logic whenever gradient is required by the drawing
    protected Paint createPaint() {
        if (gradient && colors.length >= 2) {
            if (vertGradient || horizGradient) {         // If gradient chosen is linear
                return new GradientPaint(dx_color1, dy_color1, colors[0], dx_color2, dy_color2, colors[1]);} 
                else if (radialGradient) {               // If gradient chosen is radial
                return new RadialGradientPaint(new Point.Float(dx_color1, dy_color1), radius, new float[]{0f, 1f}, colors);
            } else {                                     // Fallback to other gradients
                return new GradientPaint(dx_color1, dy_color1, colors[0], dx_color2, dy_color2, colors[1]);} 
        }
        return colors[0]; // Returns the color if false
    }

    // Main drawing method
    public abstract void draw(Graphics2D g2d);
}
