package Model;


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

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof TrayCoords){
            return ((TrayCoords) obj).getX() == this.getX() && ((TrayCoords) obj).getY() == this.getY();
        }
        return false;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
}
