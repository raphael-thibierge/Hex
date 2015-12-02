package Model.Exceptions;

import Model.Tray;

/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 02/12/15.
 */
public class BadTraySizeException extends Exception {
    private int nbLine ;
    private int nbColumn ;

    public BadTraySizeException(int nbLine, int nbColumn){
        this.nbLine = nbLine;
        this.nbColumn = nbColumn;
    }

    @Override
    public String toString() {
        return "Tray's line number ("+this.nbLine+") and tray's column number ("+this.nbColumn+") must be above " + Tray.minimalSize ;
    }

    public int getNbLine() {
        return nbLine;
    }

    public int getNbColumn() {
        return nbColumn;
    }
}
