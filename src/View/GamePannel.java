package View;

import Controller.Controller;
import Model.Cell;
import Model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by raphael on 20/11/15.
 */
public class GamePannel extends JPanel {

    GameModel model;
    Controller controller;

    public GamePannel(int width, int height, GameModel tray, Controller controller){
        super();
        this.model = tray;
        this.controller = controller;

        this.setSize(width, height);

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                controller.setGameSize(getWidth(), getHeight());
            }

            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayTray(g);
    }

    private void displayTray(Graphics g){

        if (this.model.getTray() != null){
            // for each tray's cells
            for (Cell cell : this.model.getTray().getCellList()){
                if (cell != null){
                    // draw background cell
                    g.setColor(cell.getColor().getJavaColor());
                    g.fillPolygon(cell);

                    // draw border cell
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
