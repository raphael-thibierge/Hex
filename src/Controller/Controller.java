package Controller;

import Model.*;
import Model.Shape;

import java.awt.*;

/**
 * Created by raphael on 15/11/15.
 */
public class Controller {
    private GameModel model;


    public Controller(GameModel model) throws NullPointerException{
        if (model == null)
            throw  new NullPointerException();

        this.model = model;
    }

    public void newGame(int size){
        model.newGame(size);
    }


    public void placeToken(Point p){
        if (this.model.isInGame() && p != null) {
            // return a valid coords if p is in a cell else return null
            TrayCoords coords = this.model.getTray().clickOnGrid(p);
            if (coords != null){
                this.model.putTocken(coords);
            }
        }
    }

    public void setGameSize(int width, int height ){
        model.setSize(width, height);
    }

    public void changeTrayForm(Shape shape) {
        model.changeTrayForm(shape);
    }
}
