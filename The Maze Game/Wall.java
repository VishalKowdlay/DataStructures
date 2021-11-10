import java.awt.*;

public class Wall {
    int[] x;
    int[] y;
    private boolean ifEmpty;

    public Wall(int[] x, int[] y)
    {
        this.x = x;
        this.y = y;
        ifEmpty = true;
    }

    public Wall(){
        ifEmpty = false;
    }

    public boolean getIfEmpty(){
        return ifEmpty;
    }

    public Polygon getPoly(){
        return new Polygon(x, y, x.length);
    }
}
