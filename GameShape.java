import java.awt.Graphics2D;
import java.util.Random;


//This code is the abstract class for all the shapes
//It contains the common properties and methods for all candy for easy access and modification
//It also contains a method to generate a random candy

public abstract class GameShape {
    protected double x;
    protected double y;
    protected double size;
    protected int shapeType;

    public GameShape(double x, double y, double size, int shapeType) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.shapeType = shapeType;
    }

    // Abstract method that allows the subclasses to draw themselves
    public abstract void draw(Graphics2D g2d);

    public static GameShape createRandom(double x, double y, double size) {
        Random rand = new Random();
        int shapeType = rand.nextInt(7);

        switch (shapeType) {
            case 0: return new PotchiCandy(x, y, size);
            case 1: return new YemaCandy(x, y, size);
            case 2: return new LalaCandy(x, y, size);
            case 3: return new ChocnutCandy(x, y, size);
            case 4: return new PolvoronCandy(x, y, size);
            case 5: return new hawHawCandy(x, y, size);
            case 6: return new WhiteRabbitCandy(x, y, size);
            default: return new YemaCandy(x,y,size);
        }
    }


    public int getShapeType() {
        return shapeType;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
