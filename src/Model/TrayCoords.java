package Model;


/**
 * Created by raphael on 15/11/15.
 */
public class TrayCoords {
    private int column;
    private int line;

    public TrayCoords(int line, int column){
        this.setColumn(column);
        this.setLine(line);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof TrayCoords){
            return ((TrayCoords) obj).getColumn() == this.getColumn() && ((TrayCoords) obj).getLine() == this.getLine();
        }
        return false;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "Line = " + line + " ; Column = " + column;
    }
}
