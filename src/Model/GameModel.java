package Model;

import java.util.Observable;

/**
 * Created by raphael on 15/11/15.
 */
public class GameModel extends Observable {

    private Color currentPlayer;
    private Tray tray;

    private boolean inGame = false;
    private Color winner = null;

    public GameModel(){

    }

    public void putTocken(TrayCoords coords){
        if (tray != null && inGame && tray.putTocken(coords, currentPlayer)){
            // test victory
            if (tray.testVictory(currentPlayer)){

            }


            // update view
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
        this.inGame = true;

        this.setChanged();
        this.notifyObservers();
    }

}
