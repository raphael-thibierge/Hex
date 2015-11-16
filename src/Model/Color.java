package Model;

/**
 * Created by raphael on 15/11/15.
 */
public enum Color {
    RED(java.awt.Color.RED),
    BLUE(java.awt.Color.BLUE),
    EMPTY(java.awt.Color.GRAY);

    private final java.awt.Color javaColor;

    private Color(java.awt.Color javaColor){

        this.javaColor = javaColor;
    }

    public java.awt.Color getJavaColor(){
        return this.javaColor;
    }

    public static Color oppositeColor(Color color){
        if (color != null){
            if (color.equals(BLUE))
                return RED;
            else if (color.equals(RED))
                return BLUE;
        }
        return null;
    }


}
