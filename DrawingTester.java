import javax.swing.*;

public class DrawingTester{
    public static void main(String[] args) {
        int w = 300;
        int h = 300;
        JFrame f = new JFrame();

        // Add the object here
        DrawingCanvas dc = new DrawingCanvas(w,h);
        f.setSize(w,h);
        f.setTitle("Drawing Objects in Java");
        f.add(dc);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}