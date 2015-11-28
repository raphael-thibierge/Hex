package Model;

import java.awt.*;
import java.text.DecimalFormat;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

/**
 * Created by raphael on 23/11/15.
 */
public enum  Shape {

    // the two tray shapes
    verticalLozange,
    horizontalLozange;

    double padReduct = 0.2;
    int space = 0; // space between two cell in the shape

    public double getTransformX(double cellWidth){
        return 3.0*cellWidth/4.0+space;
    }

    public double getTransformY(double cellHeight){
        if (this == verticalLozange){
            return (cellHeight+space)/2.0;
        } else {
            return  cellHeight/2.0+space/2.0;
        }
    }

    public double getCellSize(int width, int height, int nbLine, int nbColumn){

        // edit width and height to consider a pad
        width -= width * padReduct;
        height -= height* padReduct;

        double cellWidth = 0;
        double cellHeight = 0;

        // compute cell size, it depend of the shape
        if (this == horizontalLozange){
             cellWidth = (width/ (nbLine + (nbColumn-1)/2.0));
             cellHeight = (height / nbLine)*(2.0-sqrt(3)/2.0);
        }
        else if (this == verticalLozange){
             cellWidth = (width / (nbColumn*4/3));
             cellHeight = ((height / (nbLine + nbColumn/2 ))*(2.0-sqrt(3)/2.0));
        }

        // return the littlest size value between height and width
        return min(cellWidth, cellHeight);
    }

    public Point placeCell(Cell cell, int width, int height, int nbLine, int nbColumn){
        if (cell != null){

            // cell's dimention
            int cellWidth = cell.getRad()*2;
            int cellHeight = (int)(cell.getRad()*2*(sqrt(3)/2.0)) ;

            // get transform's position value to place the cell
            int transformY = (int) this.getTransformY(cellHeight) + 1;
            int transformX = (int) this.getTransformX(cellWidth) +1;

            // get cell coords
            int line = cell.getCoords().getLine();
            int column = cell.getCoords().getColumn();
            // cell position
            int positionX = 0 , positionY = 0 ;

            // compute cell position

            if (this == verticalLozange){
                positionX = (column * transformX + (int)(cellWidth/2.0));
                positionY = (line * ((int)cellHeight + space) + (nbColumn-column) * transformY);
                // center shape
                positionX += (width-(cellWidth+(nbColumn-1)*transformX))/2;
                positionY += (height-(nbLine*cellHeight+((nbColumn-1)*cellHeight)/2))/2;
            }

            else if (this == horizontalLozange){
                positionY = (transformY * (line + column+1 ));
                positionX = (transformX * (column + (nbLine-line-1)));
                // center shape
                positionY += (height-(cellHeight*nbLine))/2;
                positionX += cellWidth/2 + (width-(nbLine + (nbColumn-1)/2)*cellWidth )/2;
            }

            // return cell position
            return new Point((int)positionX,(int)positionY);
        }
        return null;
    }
}
