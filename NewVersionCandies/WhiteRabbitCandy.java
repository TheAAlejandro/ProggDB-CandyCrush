import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class WhiteRabbitCandy extends DrawingObject {
    private double size;
    private int dx;

    private List<DrawingObject> parts = new ArrayList<>();
    private Area candyBody;
    private Shape leftEllipse, baseRect, rightEllipse;

    public WhiteRabbitCandy(int x, int y, double size) {
        super(x, y,
              new Color[]{new Color(240,230,187)}, // base color
              false, true, new Color(75,39,18), 0.5f, // outline
              false, false, false,
              0,0,0,0,0f);
        this.size = size;
        this.dx = (int)(0.07 * size);
        buildCandy();
    }

    private void buildCandy() {
        // --- Candy shapes ---
        leftEllipse = new Ellipse2D.Double(x - size/2 - dx, y - size/4, 0.15*size, 0.5*size);
        baseRect = new Rectangle2D.Double(x - size/2, y - size/4, 0.9*size, 0.5*size);
        rightEllipse = new Ellipse2D.Double(x - size/2 + 0.9*size - dx, y - size/4, 0.15*size, 0.5*size);

        candyBody = new Area(leftEllipse);
        candyBody.add(new Area(baseRect));
        candyBody.add(new Area(rightEllipse));

        // --- Shadow ---
        parts.add(new ShapeObject() {
            @Override
            public void draw(Graphics2D g2d) {
                g2d.setColor(new Color(0,0,0,50));
                AffineTransform shadowTransform = AffineTransform.getTranslateInstance(-size*0.07, size*0.1);
                Shape shadow = shadowTransform.createTransformedShape(candyBody);
                g2d.fill(shadow);
            }
        });

        // --- Candy with gradient ---
        parts.add(new ShapeObject() {
            @Override
            public void draw(Graphics2D g2d) {
                // Gradient start at top of candy, end at bottom based on class fields
                float startX = (float)x;
                float startY = (float)(y - size/4);
                float endX   = startX;
                float endY   = (float)(y - size/4 + 0.5f*size);

                LinearGradientPaint gradient = new LinearGradientPaint(
                        startX, startY, endX, endY,
                        new float[]{0f,1f},
                        new Color[]{new Color(240,230,187), new Color(204,159,79)}
                );

                g2d.setPaint(gradient);
                g2d.fill(leftEllipse);
                g2d.fill(baseRect);
                g2d.fill(rightEllipse);
            }
        });

        // --- Outline ---
        parts.add(new ShapeObject() {
            @Override
            public void draw(Graphics2D g2d) {
                g2d.setColor(new Color(75,39,18));
                g2d.setStroke(new BasicStroke(0.5f));
                g2d.draw(candyBody);
            }
        });
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (DrawingObject obj : parts) {
            obj.draw(g2d);
        }
    }

    // Helper class for arbitrary shapes
    private static class ShapeObject extends DrawingObject {
        private Shape shape;
        private Color fill, stroke;
        private float thickness;

        public ShapeObject() {
            super(0,0,new Color[]{Color.WHITE},false,false,Color.BLACK,1f,false,false,false,0,0,0,0,0f);
        }

        @Override
        public void draw(Graphics2D g2d) {
            if(shape != null) {
                g2d.setColor(fill);
                g2d.fill(shape);
                g2d.setColor(stroke);
                g2d.setStroke(new BasicStroke(thickness));
                g2d.draw(shape);
            }
        }
    }
}
