package Model;

import java.util.Observable;

/**
 * Created by raphael on 15/11/15.
 */
public class GameModel extends Observable {

    private Color currentPlayer;
    private Tray tray;

    public GameModel(){

    }

    public void putTocken(TrayCoords coords){
        if (tray.putTocken(coords, currentPlayer)){

            this.setChanged();
            this.notifyObservers();
        }
    }

    private void nextPlayer(){
        currentPlayer = Color.oppositeColor(currentPlayer);
    }

    public void newGame(){
        this.tray = new Tray(10,10);
        this.currentPlayer = Color.BLUE;

        this.setChanged();
        this.notifyObservers();
    }

}
