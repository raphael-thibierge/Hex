package Model;

import java.awt.*;

/**
 * Created by raphael on 15/11/15.
 */
public class Cell extends Polygon {
    private TrayCoords coords;
    private Point position;
    private Color color;
    private int rad = 37;
    private boolean visited = false;


    public Cell(TrayCoords coords, Point position) throws NullPointerException{
        super();
        if (coords == null || position == null){
            throw new NullPointerException();
        }
        // set coords and graphic position
        this.coords = coords;
        this.position = position;
        // init polygon's points
        initPolygon();
        // set cell empty
        resetColor();
    }

    private void initPolygon(){
        // set position of polygon's points
        double arc=(Math.PI*2)/6;
        for (int i=0; i<=6; i++) {
            this.addPoint((int) Math.round(position.getX() + rad * Math.cos(arc * i)),
                    (int) Math.round(position.getY() + rad * Math.sin(arc * i)));
        }
    }

    public void resetColor(){
        this.color = Color.EMPTY;
    }

    public boolean clickOnCell(Point position){
        return position != null && this.contains(position.getX(), position.getY());
    }

    public boolean isTacken(){
        return !this.color.equals(Color.EMPTY);
    }

    /*
    * ACCESSORS
    * */
    public TrayCoords getCoords() {
        return coords;
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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setRad(double rad) {
        // set rad
        this.rad = (int)rad;
        // uodate position of polygon's points
        double arc=(Math.PI*2)/6;
        for (int i=0; i<=6; i++) {
            xpoints[i] = ((int) Math.round(position.getX() + rad * Math.cos(arc * i)));
            ypoints[i] = ((int) Math.round(position.getY() + rad * Math.sin(arc * i)));
        }
    }

    public Polygon getBorderPolygone(int nbLine, int nbColumn, int firstPoint, int nbPoint){
        // uodate position of polygon's points
        Polygon border = new Polygon();

        double arc=(Math.PI*2)/6;
        int newRad = rad+ 5;
        border.addPoint((int) this.position.getX(), (int) this.position.getY());

        if (this.getCoords().getLine() == 0 || this.getCoords().getLine() == nbLine-1
                ||this.getCoords().getColumn() == 0 || this.getCoords().getColumn() == nbColumn-1){
            for (int i = 0 ; i < nbPoint ; i++){
                placePoint(border, newRad, (firstPoint+i)%6);
            }
        }

        return border;
    }

    public int getRad() {
        return rad;
    }

    @Override
    public String toString() {
        String text = "line=" + this.getCoords().getLine() + " ";
        text += "column=" + this.getCoords().getColumn() + " ";
        text += "Color=" + this.getColor() + " ";
        text += "visited=" + this.visited ;
        return text;
    }


    private void placePoint(Polygon polygon, int newRad, int number){
        double arc=(Math.PI*2)/6;
        polygon.addPoint((int) Math.round(position.getX() + newRad * Math.cos(arc * number)),
                (int) Math.round(position.getY() + newRad * Math.sin(arc * number)));
    }
}
