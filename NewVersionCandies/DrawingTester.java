import javax.swing.*;

public class DrawingTester {
    public static void main(String[] args) {
        int w = 500;
        int h = 400;

        JFrame f = new JFrame("Drawing Objects in Java");
        f.setSize(w, h);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingCanvas dc = new DrawingCanvas(w, h);
        f.add(dc);

        f.setVisible(true);
    }
}
