import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class SceneFrame extends JFrame {
    private JFrame frame;
    private int width;
    private int height;
    private ShapeButton[][] buttons = new ShapeButton[8][8];
    private GameShape[][] shapes = new GameShape[8][8];

    private Random rand = new Random();
    private Point firstClick = null;
    private boolean animating = false;

    public SceneFrame(int w, int h) {
        frame = new JFrame("Kendi Kwela");
        width = w;
        height = h;
    }

    public void setUpGUI() {
        Container cp = frame.getContentPane();
        cp.setLayout(new GridLayout(8, 8));

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                // Per each cell, instantiate a button and spawn a random shape inside it
                shapes[r][c] = GameShape.createRandom(width/16, height/16, 45);
                ShapeButton btn = new ShapeButton(shapes[r][c]);
                // btn.addActionListener(new ButtonClickListener(r, c));
                buttons[r][c] = btn;
                cp.add(btn);
            }
        }

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SceneFrame sf = new SceneFrame(800, 600);
        sf.setUpGUI();
    }
}
