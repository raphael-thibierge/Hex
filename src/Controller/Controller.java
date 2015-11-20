package Controller;

import Model.GameModel;
import Model.TrayCoords;

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

    public void newGame(){
        model.newGame();
    }

    public void

    placeToken(Point p){
        if (this.model.isInGame() && p != null) {
            TrayCoords coords = this.model.getTray().clickOnGrid(p);
            if (coords != null){
                System.out.println(coords.getLine() + " " + coords.getColumn());
                this.model.putTocken(coords);
            }
        }
    }

    public void setGameSize(int size){
    }
}
