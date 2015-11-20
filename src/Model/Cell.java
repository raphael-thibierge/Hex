package Model;


import java.awt.*;

/**
 * Created by raphael on 15/11/15.
 */
public class Cell extends Polygon {
    private TrayCoords coords;
    private Point position;
    private Color color;
    private final int rad = 30;

    public Cell(TrayCoords coords, Point position) throws NullPointerException{
        super();

        if (coords == null || position == null){
            throw new NullPointerException();
        }

        this.coords = coords;
        this.position = position;

        initPolygon();

        reset();
    }

    private void initPolygon(){

        double arc=(Math.PI*2)/6;
        for (int i=0; i<=6; i++) {
            this.addPoint((int) Math.round(position.getX() + rad * Math.cos(arc * i)),
                    (int) Math.round(position.getY() + rad * Math.sin(arc * i)));
        }
    }

    public void reset(){
        color = Color.EMPTY;
    }

    public boolean clickOnCell(Point position){
        return position != null && this.contains(position.getX(), position.getY());
    }

    public boolean isTacken(){
        return !this.color.equals(Color.EMPTY);
    }

    /*
    *
    * ACCESSORS
    * */

    public TrayCoords getCoords() {
        return coords;
    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPosition(Point position) {
        // translate polygon position
        this.translate(position.x - this.position.x, position.y - this.position.y);
        // set position
        this.position = position;
    }
}
