package View;

import Controller.Controller;
import Model.*;
import Model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 20/11/15.
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
                controller.setGraphicGameSize(getWidth(), getHeight());
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

            int nbLine = this.model.getTray().getNbLine();
            int nbColumn = this.model.getTray().getNbColumn();
            int shape = (this.model.getTray().getShape() == Shape.verticalLozange)? 0 : 1;


            // TRAY BORDERS
            for (Cell cell : this.model.getTray().getCellList()){
                if (cell != null){

                    // draw top
                    if (cell.getCoords().getLine() == 0) {
                        // set b
                        g.setColor(Model.Color.BLUE.getJavaColor());
                        g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 3+shape, 3));

                        if ( cell.getCoords().getColumn() == nbColumn -1){

                            g.setColor(Model.Color.RED.getJavaColor());
                            g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 5+shape, 3));
                        }
                        else if (cell.getCoords().getColumn() == 0){
                            g.setColor(Color.BLACK);
                            g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 3+shape, 2));

                            g.setColor(Model.Color.RED.getJavaColor());
                            g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 2+shape, 2));
                        }

                    }
                    // draw down
                    else if (cell.getCoords().getLine() == nbLine-1) {
                        g.setColor(Model.Color.BLUE.getJavaColor());
                        g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 0+shape, 3));

                        if ( cell.getCoords().getColumn() == 0){
                            g.setColor(Model.Color.RED.getJavaColor());
                            g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 2+shape, 3));
                        }
                        else if (cell.getCoords().getColumn() == nbColumn-1){
                            g.setColor(Color.BLACK);
                            g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 0+shape, 2));
                            g.setColor(Model.Color.RED.getJavaColor());
                            g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 5+shape, 2));
                        }
                    }

                    // draw left
                    else if (cell.getCoords().getColumn() == 0) {
                        g.setColor(Model.Color.RED.getJavaColor());
                        g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 2+shape, 3));
                    }

                    // draw right
                    else if (cell.getCoords().getColumn() == nbColumn-1) {
                        g.setColor(Model.Color.RED.getJavaColor());
                        g.fillPolygon(cell.getBorderPolygone(nbLine, nbColumn, 5+shape, 3));
                    }
                }
            }

            // TRAY cells
            for (Cell cell : this.model.getTray().getCellList()){
                if (cell != null){
                    // draw background cell
                    g.setColor(cell.getColor().getJavaColor());
                    g.fillPolygon(cell);

                    // draw border cell
                    g.setColor(Color.black);
                    g.drawPolygon(cell);
                }
            }
        }
    }
}
