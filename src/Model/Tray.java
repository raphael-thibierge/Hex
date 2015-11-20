package Model;

import java.awt.*;
import java.util.ArrayList;

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
        this.initTray();
    }

    private void initTray() {
        // space between cells
        int size = 60;

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
        this.editTrayForm(size);
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

    public boolean isFull(){
        return this.cptTokenCells == this.nbLine*this.nbColumn;
    }

    public boolean testVictory(Color color){
        switch (color){
            case RED:
                for (int line = 0 ; line < this.nbLine ; line++){
                    if (browse( this.getCell(new TrayCoords(line, 0)), Color.RED)) {
                        return true;
                    }
                }
                break;

            case BLUE:
                for (int column = 0 ; column < this.nbColumn ; column++){
                    if (browse(this.getCell(new TrayCoords(0, column)), Color.BLUE)){
                        return true;
                    }
                }
                break;

            default:
                break;
        }
        return false;
    }

    private boolean browse(Cell cell, Color color){
        if (cell != null && cell.isTacken()
                &&  color != null  && cell.getColor() == color){

            if (cell.getColor().equals(Color.BLUE) && cell.getCoords().getLine() == this.nbLine-1){
                return true;
            }
            if (cell.getColor().equals(Color.RED) && cell.getCoords().getColumn() == this.nbColumn-1){
                return true;
            }

            cell.setVisited(true);

            for (Cell neighbor : this.getNextCellsList(cell)){
                if (neighbor.getColor().equals(cell.getColor())
                        && !neighbor.isVisited()
                        && neighbor.isTacken()){
                    if (browse(neighbor, cell.getColor())){
                        cell.setVisited(false);
                        return true;
                    }
                }
            }

            cell.setVisited(false);
        }
        return false;
    }

    private ArrayList<Cell> getNextCellsList(Cell cell){

        ArrayList<Cell> list = new ArrayList<>();
        TrayCoords cellCoords = cell.getCoords();

        ArrayList<TrayCoords> coordsList = new ArrayList<>();
        coordsList.add(new TrayCoords(cellCoords.getLine(), cellCoords.getColumn()-1));
        coordsList.add(new TrayCoords(cellCoords.getLine(), cellCoords.getColumn()+1));
        coordsList.add(new TrayCoords(cellCoords.getLine()-1, cellCoords.getColumn()));
        coordsList.add(new TrayCoords(cellCoords.getLine()+1, cellCoords.getColumn()));
        coordsList.add(new TrayCoords(cellCoords.getLine()-1, cellCoords.getColumn()+1));
        coordsList.add(new TrayCoords(cellCoords.getLine() + 1, cellCoords.getColumn() - 1));

        Cell neighbor;
        for (TrayCoords coords : coordsList){
            if ((neighbor = this.getCell(coords)) != null){
                list.add(neighbor);
            }
        }
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
        return (coords != null
                && coords.getLine() < this.nbLine && coords.getColumn() < this.nbColumn
                && coords.getLine() >= 0 && coords.getColumn() >= 0);
    }

    public void editTrayForm(int space){
        int decal = space / 3 ;

        for (int line = 0 ; line < this.nbLine ; line++){
            for (int column = 0 ; column < this.nbColumn ; column++){

                Cell cell = this.getCell(new TrayCoords(line, column));
                if (cell != null){
                    cell.setPosition(getHorizontalLozPoint(line, column, space));
                }
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

        /* in the other side
        * int y = line * (decalY) + (decalY)*column;
        int x = column * (decalX) + (nbLine-line)*(decalX);
        */
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
        ArrayList<Cell> list = new ArrayList<>();
        if (this.grid != null){
            for (int line = 0 ; line < this.nbLine ; line++){
                for (int column = 0 ; column < this.getNbColumn() ; column++){
                    list.add(this.getCell(new TrayCoords(line, column)));
                }
            }
        }
        return list;
    }
}
