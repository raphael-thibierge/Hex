package Model;

import Model.Exceptions.BadTraySizeException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 15/11/15.
 */
public class Tray {
    public final static int standardSize = 7;
    public final static int minimalSize = 2;

    // data information
    private ArrayList<ArrayList<Cell>> grid;
    private int nbLine;
    private int nbColumn;
    private int cptTackenCells = 0;

    // graphic information
    private int width = 0;
    private int height = 0;
    private Shape shape = Shape.verticalLozange;


    public Tray(int nbLine, int nbColumn) throws BadTraySizeException{
        if (nbLine < minimalSize || nbColumn < minimalSize)
            throw new BadTraySizeException(nbLine, nbColumn);

        this.nbLine = nbLine;
        this.nbColumn = nbColumn;
        // init the tray
        this.initTray();
    }

    public void initTray() {

        if(this.grid != null){
            this.grid.clear();
        }

        this.cptTackenCells = 0;

        // init grid
        this.grid = new ArrayList<>();

        // for each  grid line
        for (int line = 0 ; line < this.nbLine ; line++){
            // create grid line, to insert cells in.
            ArrayList<Cell> arrayLine = new ArrayList<>();
            // for each grid column
            for (int column = 0 ; column < this.nbColumn ; column++){
                // insert a new Cell
                arrayLine.add(new Cell(new TrayCoords(line, column), new Point(0,0)));
            }
            // add the array line in grid
            this.grid.add(arrayLine);
        }
        this.editTrayForm(this.shape);
    }

    public boolean putTocken(TrayCoords coords, Color color){
        // if coords and color are valid
        if (Color.validPlayerColor(color) && valideCoords(coords)){
            // get cell in  grid
            Cell cell = getCell(coords);
            // if cell is free
            if (cell != null && !cell.isTacken()){
                // change cell color and increment number of tokens in grid;
                cell.setColor(color);
                this.cptTackenCells++;
                return true;
            }
        }
        return false;
    }

    public boolean isFull(){
        // number of tacken grid cell equals cells number in grid
        return this.cptTackenCells == this.nbLine*this.nbColumn;
    }

    public boolean isEmpty(){
        return this.cptTackenCells == 0;
    };

    public boolean testVictory(Color color){

        switch (color){

            case RED:
                // if color is red we start from left grid side to go to the right grid side
                for (int line = 0 ; line < this.nbLine ; line++){
                    if (browseToVictory(this.getCell(new TrayCoords(line, 0)), Color.RED)) {
                        return true;
                    }
                }
                break;

            case BLUE:
                // if color is red we start from top grid side to go to the down grid side
                for (int column = 0 ; column < this.nbColumn ; column++){
                    if (browseToVictory(this.getCell(new TrayCoords(0, column)), Color.BLUE)){
                        return true;
                    }
                }
                break;

            default:
                break;
        }
        return false;
    }

    private boolean browseToVictory(Cell cell, Color color){
        // to be browsed, a cell have to exist, have the color we search, and have not already been visited
        if (cell != null && cell.isTacken()
                &&  color != null  && cell.getColor() == color
                && !cell.isVisited()){

            // if the cell is blue and is on the grid right border, browse has been successfull
            if (cell.getColor().equals(Color.BLUE) && cell.getCoords().getLine() == this.nbLine-1){
                return true;
            }

            // if the cell is red and is on the grid down border, browse has been successfull
            if (cell.getColor().equals(Color.RED) && cell.getCoords().getColumn() == this.nbColumn-1){
                return true;
            }

            // had a tag in cell to remember we visit if
            cell.setVisited(true);

            // browse all neighbor cells
            for (Cell neighbor : this.getNeighborCellsList(cell)){
                // if browse has been successful, return true
                if (browseToVictory(neighbor, color)){
                    // unset visited before return
                    cell.setVisited(false);
                    return true;
                }
            }

            // unset visited before return
            cell.setVisited(false);
        }
        return false;
    }

    public ArrayList<Cell> getNeighborCellsList(Cell cell){
        // list to return
        ArrayList<Cell> list = new ArrayList<>();
        // get the neighbor cell
        TrayCoords cellCoords = cell.getCoords();
        Cell cell1;
        if ((cell1 = this.getCell(new TrayCoords(cellCoords.getLine(), cellCoords.getColumn()-1))) != null)
            list.add(cell1);
        if ((cell1 = this.getCell(new TrayCoords(cellCoords.getLine(), cellCoords.getColumn()+1))) != null)
            list.add(cell1);
        if ((cell1 = this.getCell(new TrayCoords(cellCoords.getLine()-1, cellCoords.getColumn()))) != null)
            list.add(cell1);
        if ((cell1 = this.getCell(new TrayCoords(cellCoords.getLine()+1, cellCoords.getColumn()))) != null)
            list.add(cell1);
        if ((cell1 = this.getCell(new TrayCoords(cellCoords.getLine()-1, cellCoords.getColumn()-1))) != null)
            list.add(cell1);
        if ((cell1 = this.getCell(new TrayCoords(cellCoords.getLine()+1, cellCoords.getColumn()+1))) != null)
            list.add(cell1);
        return list;
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

    public boolean valideCoords(TrayCoords coords){
        // coords have to refer a grid cell
        return (coords != null
                && coords.getLine() < this.nbLine && coords.getColumn() < this.nbColumn
                && coords.getLine() >= 0 && coords.getColumn() >= 0);
    }

    public void editTrayForm(Shape shape){
        // set shape
        this.shape = shape;

        // compute size, it depend of the tray shape
        double size = this.shape.getCellSize(width, height, nbLine, nbColumn);

        // for each cell, set rad and position
        for (Cell cell : this.getCellList()) {
            cell.setRad(size / 2.0);
            cell.setPosition(shape.placeCell(cell, width, height, nbLine, nbColumn));
        }
    }

    /*
    * ACCESSORS
    * */

    public int getNbLine() {
        return nbLine;
    }

    public int getNbColumn() {
        return nbColumn;
    }

    public Cell getCell(TrayCoords coords){
        // return the cell if coords are valid
        if (valideCoords(coords)){
            return this.grid.get(coords.getLine()).get(coords.getColumn()) ;
        }
        return null;
    }

    public ArrayList<Cell> getCellList(){
        // cell list to return
        ArrayList<Cell> list = new ArrayList<>();
        if (this.grid != null){
            // for each cell in the grif
            for (int line = 0 ; line < this.nbLine ; line++){
                for (int column = 0 ; column < this.getNbColumn() ; column++){
                    //add cell in the list
                    list.add(this.getCell(new TrayCoords(line, column)));
                }
            }
        }
        return list;
    }

    public Shape getShape() {
        return shape;
    }

    public void editSize(int size) throws BadTraySizeException{
        if (size < minimalSize)
            throw new BadTraySizeException(size, size);

    	this.nbColumn = size;
    	this.nbLine = size;
        // reset tray's cell's
        this.initTray();
    }

    public void setGraphicSize(int width, int height) {
        // set size and edit tray size and form
        this.width = width;
        this.height = height;

        this.editTrayForm(this.shape);
    }
}
