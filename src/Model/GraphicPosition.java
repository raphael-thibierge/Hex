package Model;

import static java.lang.Math.abs;

/**
 * Created by raphael on 15/11/15.
 */
public class GraphicPosition {
    private int X;
    private int Y;

    public GraphicPosition(int X, int Y){
        this.setX(X);
        this.setY(Y);
    }

    public GraphicPosition(){
        this.X = 0;
        this.Y = 0;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = abs(X);
    }

    public int getY() {
        return this.Y;
    }

    public void setY(int Y) {
        this.Y = abs(Y);
    }
}
