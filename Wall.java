import java.awt.*;

public class Wall {
    int[] x;
    int[] y;

    public Wall(int[] x, int[] y)
    {
        this.x = x;
        this.y = y;
    }

    public Polygon getPoly(){
        return new Polygon(x, y, x.length);
    }
}
