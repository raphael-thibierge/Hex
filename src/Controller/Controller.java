/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 15/11/15.
 */
package Controller;

import Model.*;
import Model.Exceptions.BadTraySizeException;
import Model.Exceptions.GameRunningException;
import Model.Shape;

import java.awt.*;

public class Controller {
    private GameModel model;


    public Controller(GameModel model) throws NullPointerException{
        if (model == null)
            throw  new NullPointerException("model in Controller's constructor is null");
        this.model = model;
    }

    public void newGame(int size) throws BadTraySizeException, GameRunningException {
        model.newGame(size);
    }

    /**
     * Stop
     * @param size
     * @throws BadTraySizeException
     */
    public void forceNewGame(int size) throws BadTraySizeException {
        this.model.stopGame();
        try {
            this.model.newGame(size);
        } catch (GameRunningException e) {
            e.printStackTrace();
        }
    }


    public void placeToken(Point point){
        if (this.model.isInGame()) {
            // return a valid coords if point is in a cell else return null
            TrayCoords coords = this.model.clickOnGrid(point);
            if (coords != null){
                this.model.putTocken(coords);
            }
        }
    }

    public void setGraphicGameSize(int width, int height){
        if (width > 0 && height > 0) {
            model.setGraphicSize(width, height);
        }
    }

    public void changeTrayForm(Shape shape){
        model.changeTrayForm(shape);
    }
}
