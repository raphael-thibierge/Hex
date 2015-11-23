package View;

import Model.Cell;
import Model.Tray;

import javax.swing.*;
import java.awt.*;

/**
 * Created by raphael on 20/11/15.
 */
public class GamePannel extends JPanel {

    Tray tray;

    public GamePannel(int width, int height, Tray tray){
        super();
        this.tray = tray;
        this.setSize(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayTray(g);
    }

    private void displayTray(Graphics g){

        if (this.tray != null){
            // for each tray's cells
            for (Cell cell : this.tray.getCellList()){
                if (cell != null){
                    // draw cell
                    g.setColor(cell.getColor().getJavaColor());
                    g.fillPolygon(cell);

                    if (cell.getCoords().getLine() == 0)
                        g.setColor(Color.BLUE);
                    else if (cell.getCoords().getColumn() == 0 )
                        g.setColor(Color.RED);
                    else g.setColor(Color.black);
                    g.drawPolygon(cell);
                }
            }
        }
    }
}
