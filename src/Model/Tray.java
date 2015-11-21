package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by raphael on 15/11/15.
 */
public class Tray {
    public static int standartSize = 5;
    private int nbLine;
    private int nbColumn;
    private int cptTackenCells = 0;

    private ArrayList<ArrayList<Cell>> grid;

    public Tray(int nbLine, int nbColumn) throws NegativeArraySizeException{
        if (nbLine < 0 || nbColumn < 0)
            throw new NegativeArraySizeException();

        this.nbLine = nbLine;
        this.nbColumn = nbColumn;
        // init the tray
        this.initTray();
    }

    public void initTray() {
        // space between cells
        int size = 60;

        if(this.grid != null){
            this.grid.clear();
        }

        // init grid
        this.grid = new ArrayList<>();

        // for each  grid line
        for (int line = 0 ; line < this.nbLine ; line++){
            // create grid line, to insert cells in.
            ArrayList<Cell> arrayLine = new ArrayList<>();
            // for each grid column
            for (int column = 0 ; column < this.nbColumn ; column++){
                // insert a new Cell
                arrayLine.add(new Cell(new TrayCoords(line, column), getLozPoint(line, column, size)));
            }
            // add the array line in grid
            this.grid.add(arrayLine);
        }
        this.editTrayForm(size,1);
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
                this.cptTackenCells++;
                return true;
            }
        }
        return false;
    }

    public boolean isFull(){
        // number of tacken grid cell equald cells number in grid
        return this.cptTackenCells == this.nbLine*this.nbColumn;
    }

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

    private ArrayList<Cell> getNeighborCellsList(Cell cell){
        // list to return
        ArrayList<Cell> list = new ArrayList<>();
        // get the neighbor cell
        TrayCoords cellCoords = cell.getCoords();
        list.add(this.getCell(new TrayCoords(cellCoords.getLine(), cellCoords.getColumn()-1)));
        list.add(this.getCell(new TrayCoords(cellCoords.getLine(), cellCoords.getColumn()+1)));
        list.add(this.getCell(new TrayCoords(cellCoords.getLine()-1, cellCoords.getColumn())));
        list.add(this.getCell(new TrayCoords(cellCoords.getLine()+1, cellCoords.getColumn())));
        list.add(this.getCell(new TrayCoords(cellCoords.getLine()-1, cellCoords.getColumn()+1)));
        list.add(this.getCell(new TrayCoords(cellCoords.getLine()+1, cellCoords.getColumn()-1)));
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

    public void editTrayForm(int size, int mode){
        // set all cell's position
        for (Cell cell : this.getCellList()){
            if (cell != null){
            	if(mode == 1)
            		cell.setPosition(getHorizontalLozPoint(cell.getCoords().getLine(), cell.getCoords().getColumn(), size));
            	else
            		cell.setPosition(getLozPoint(cell.getCoords().getLine(), cell.getCoords().getColumn(), size));
            }
        }

    }

    private Point getLozPoint(int line, int column, int size) {
        int espacement = 5;
        int sizeY = (int)(((float)size)*0.88); // provisoire
        int sizeX = size;
        int decalY = (sizeY+espacement)/2;
        int decalX = 3*sizeX/4+espacement;
        int x = column * decalX;
        int y = line * (sizeY + espacement) + column * decalY;
        x+=50;
        y+=50;
        return new Point(x, y);
    }

    private Point getHorizontalLozPoint(int line, int column, int size){

        int espacement = 5;
        int sizeY = (int)(((float)size)*0.88); // provisoire
        int sizeX = size;
        int decalY = sizeY/2+espacement/2;
        int decalX = 3*sizeX/4+espacement;
        int y = line * (decalY) + (decalY)*(nbColumn-column);
        int x = column * (decalX) + line*(decalX);
        x+=50;
        y+=50;
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
    
    public void editSize(int size)
    {
    	this.nbColumn = size;
    	this.nbLine = size;
        this.initTray();
    }
}
