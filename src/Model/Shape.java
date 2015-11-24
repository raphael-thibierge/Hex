package Model;

import java.awt.*;

import static java.lang.Math.min;

/**
 * Created by raphael on 23/11/15.
 */
public enum  Shape {

    verticalLozange,
    horizontaleLozange;

    public int getDecalX (int sizeX, int space){
        return 3*sizeX/4+space;
    }

    public int getDecalY (int sizeY, int space){
        if (this == verticalLozange){
            return (sizeY+space)/2;
        } else {
            return  sizeY/2+space/2;
        }
    }

    public int getCellSize(int width, int height, int nbLine, int nbColumn){

        int space = 0;

        if (this == horizontaleLozange){
            int spaceWidth = 4*(width-space*nbLine)/3 / (nbColumn*2);
            int spaceHeight = (height / (nbLine+1));
            space = min(spaceWidth, spaceHeight);
        }
        else if (this == verticalLozange){
            int spaceWidth = (width / ((nbColumn - 1) * 2));
            int spaceHeight = (height / (nbLine + nbColumn/2 + 1 ));
            space = min(spaceWidth, spaceHeight);
        }
        return space;
    }

    public Point placeCell(Cell cell, int width, int height, int nbLine, int nbColumn){
        if (cell != null){
            int space = 0;
            int sizeX = cell.getRad()*2;
            int sizeY = (int)(((float)sizeX)*0.88);
            int decalY = this.getDecalY(sizeY, space);
            int decalX =this.getDecalX(sizeX, space);
            int line = cell.getCoords().getLine();
            int column = cell.getCoords().getColumn();
            int x = 0 , y = 0 ;
            if (this == verticalLozange){
                x = column * decalX + sizeX/2;
                y = line * (sizeY + space) + (nbColumn-column) * decalY;
                x+= (width-(sizeX+(nbColumn-1)*decalX))/2;
                y+= (height-(nbLine*sizeY+(nbColumn-1)*(sizeY/2)))/2 - 22;
                //y+= (height-( nbLine*sizeY + (nbColumn+1)*decalY ))/2;
            } else if (this == horizontaleLozange){
                y = decalY * (line + column );
                x = decalX * (column + (nbLine-line));
                y+= (height-(sizeY*nbLine))/2;
                x+= ((width-((nbLine+nbColumn) * (decalX)))/2);
            }
            return new Point(x,y);
        }
        return null;
    }
}
