package Model;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;

/**
 * Created by raphael on 15/11/15.
 */
public class Tray {
    private int nbLine;
    private int nbColumn;
    private int cptTokenCells = 0;

    private ArrayList<ArrayList<Cell>> grid;

    public Tray(int nbLine, int nbColumn) throws NegativeArraySizeException{
        if (nbLine < 0 || nbColumn < 0)
            throw new NegativeArraySizeException();

        this.nbLine = nbLine;
        this.nbColumn = nbColumn;
        // init the tray
        this.reset();
    }

    private void reset() {
        // space between cells
        int space = 60;

        // init grid
        this.grid = new ArrayList<>();

        // for each  grid line
        for (int line = 0 ; line < this.nbLine ; line++){
            // create grid line, to insert cells in.
            ArrayList<Cell> arrayLine = new ArrayList<>();
            // for each grid column
            for (int column = 0 ; column < this.nbColumn ; column++){
                // insert a new Cell
                arrayLine.add(new Cell(new TrayCoords(column, line), getLozPoint(line, column, space)));
            }
            // add the array line in grid
            this.grid.add(arrayLine);
        }

        this.editTrayForm(space);
    }


    public boolean putTocken(TrayCoords coords, Color color){
        // if coords and color are valide
        if (Color.validPlayerColor(color) && valideCoords(coords)){
            // get cell in  grid
            Cell cell = getCell(coords);
            // if cell is free
            if (cell != null && !cell.isTacken()){
                // change cell color and increment number of tokens in grid;
                cell.setColor(color);
                this.cptTokenCells++;
                return true;
            }
        }
        return false;
    }

    public boolean isFool(){
        return this.cptTokenCells == this.nbLine*this.nbColumn;
    }

    public boolean testVictory(Color color){
        return false;
    }

    public TrayCoords clickOnGrid(Point position){
        if (position != null){
            // for each cell in the tray
            for (Cell cell : this.getCellList()){
                    // test if the click is on the cell
                if (cell != null && cell.clickOnCell(position)){
                        return cell.getCoords();
                }
            }
        }
        return null;
    }

    private boolean valideCoords(TrayCoords coords){
        return (coords != null && coords.getY() < this.nbLine && coords.getX() < this.nbColumn);
    }

    public void editTrayForm(int space){
        int decal = space / 3 ;

        for (int line = 0 ; line < this.nbLine ; line++){
            for (int column = 0 ; column < this.nbColumn ; column++){

                Cell cell = this.getCell(new TrayCoords(column, line));
                if (cell != null){
                    cell.setPosition(getLozPoint(line, column, space));
                }
            }
        }

    }

    static private Point getLozPoint(int line, int column, int space){
        int decal = space / 3 ;
        int x = ((column+1) * space ) /*+ ( decal * column )*/;
        int y = ((line+1) * space ) + ( decal * column );
        return new Point(x, y);
    }


    /*
    *
    * ACCESSORS
    * */

    public int getNbLine() {
        return nbLine;
    }

    public int getNbColumn() {
        return nbColumn;
    }

    public Cell getCell(TrayCoords coords){
        if (valideCoords(coords)){
            return this.grid.get(coords.getY()).get(coords.getX()) ;
        }
        return null;
    }

    public ArrayList<Cell> getCellList(){
        ArrayList<Cell> list = new ArrayList<>();
        if (this.grid != null){
            for (int line = 0 ; line < this.nbLine ; line++){
                for (int column = 0 ; column < this.getNbColumn() ; column++){
                    list.add(this.getCell(new TrayCoords(column, line)));
                }
            }
        }
        return list;
    }
}
