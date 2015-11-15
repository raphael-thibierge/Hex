package Model;

import static java.lang.Math.abs;

/**
 * Created by raphael on 15/11/15.
 */
public class TrayCoords {
    private int X;
    private int Y;

    public TrayCoords(){
        this.X = 0;
        this.Y = 0;
    }

    public TrayCoords(int X, int Y){
        this.setX(X);
        this.setY(Y);
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = abs(X);
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = abs(Y);
    }
}
