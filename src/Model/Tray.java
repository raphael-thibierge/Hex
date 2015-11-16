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
        this.reset();
    }


    private void reset() {
        // space between cells
        int space = 50;

        // for each  grid line
        for (int line = 0 ; line < this.nbLine ; line++){

            // create grid line, to insert cells in.
            ArrayList<Cell> arrayLine = new ArrayList<>();

            // for each grid column
            for (int column = 0 ; column < this.nbColumn ; column++){
                // insert a new Cell
                arrayLine.add(new Cell(new TrayCoords(line, column), new Point((line+1)*space, (column+1)*space)));
            }

            // add the array line in grid
            this.grid.add(arrayLine);
        }
    }


    public boolean putTocken(TrayCoords coords, Color color){
        return false;
    }

    public boolean isFool(){
        return false;
    }

    public boolean testVictory(Color color){
        return false;
    }

    public boolean clickOnGrid(Point position){
        return false;
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


}
