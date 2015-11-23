package Model;

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



}
