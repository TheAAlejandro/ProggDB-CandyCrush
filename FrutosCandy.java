import java.awt.*;
import java.util.Random;

public class FrutosCandy{
    private double x;
    private double y;
    private double size;
    private Color color;
    private int sugarCount = 500;  // number of sugar dots
    private Random rand = new Random();

    public FrutosCandy(double x, double y, double size){
        // Initialize position as the center of the potchi
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void drawPotchi(Graphics2D g2d){}}