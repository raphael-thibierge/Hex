package Controller;

import Model.GameModel;
import Model.Tray;
import Model.TrayCoords;

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

    public void putToken(TrayCoords coords){
        model.putTocken(coords);
    }

    public void newGame(){
        model.newGame();
    }


}
