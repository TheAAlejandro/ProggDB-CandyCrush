import java.awt.*;
import javax.swing.*;

public class DrawingCanvas extends JComponent{
    private int width;
    private int height;
    private ChocnutCandy p1;

    public DrawingCanvas(int w, int h){
        width = w;
        height = h;
        p1 = new ChocnutCandy(150, 150, 100);
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;   
        
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHints(rh);

        // Draw the pochi
        p1.drawChocnut(g2d);
    }   
}