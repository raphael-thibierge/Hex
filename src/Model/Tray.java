package Model;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by raphael on 15/11/15.
 */
public class Tray {
    private int nbLine;
    private int nbColumn;
    private int cptTokenCells = 0;

    private ArrayList<ArrayList<Cell>> grid;

    public Tray(int nbLine, int nbColumn){
        this.nbLine = abs(nbLine);
        this.nbColumn = abs(nbColumn);
        // init the tray
        this.reset();
    }


    private void reset() {
        // space between cells
        int space = 50;

        // init grid
        this.grid = new ArrayList<>();

        // for each  grid line
        for (int line = 0 ; line < this.nbLine ; line++){
            // create grid line, to insert cells in.
            ArrayList<Cell> arrayLine = new ArrayList<>();
            // for each grid column
            for (int column = 0 ; column < this.nbColumn ; column++){
                // insert a new Cell
                arrayLine.add(new Cell(new TrayCoords(column, line), new Point((column+1)*space, (line+1)*space)));
            }
            // add the array line in grid
            this.grid.add(arrayLine);
        }
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

    public boolean clickOnGrid(Point position){
        if (position != null){
            // for each cell in the tray
            for (int line = 0 ; line < this.nbLine ; line++){
                for (int column = 0 ; column < this.nbColumn ; column++){
                    // test if the click is on the cell
                    if (this.getCell(new TrayCoords(column, line)).clickOnCell(position))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean valideCoords(TrayCoords coords){
        return (coords != null && coords.getY() < this.nbLine && coords.getX() < this.nbColumn);
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
            return this.grid.get(coords.getY()).get(coords.getX());
        }
        return null;
    }
}
