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
        tray = new Tray(5, 5);
        this.inGame = true;
        this.currentPlayer = Color.BLUE;
    }

    public void putTocken(TrayCoords coords){
        // if token is placed on tray
        if (tray != null && inGame && tray.putTocken(coords, currentPlayer)){
            // test victory

            if (tray.testVictory(currentPlayer)){
                // set winner
                this.winner = currentPlayer;
                // stop game
                this.inGame = false;

            }else if (tray.isFull()){
                // no victory but tray is full so it's end of the game
                // there is no winner
                this.winner = Color.EMPTY;
                // stop game
                this.inGame = false;

            } else{
                // it's next player turn
                this.nextPlayer();
            }

            // update view
            this.setChanged();
            this.notifyObservers();
        }
    }

    private void nextPlayer(){
        currentPlayer = Color.oppositeColor(currentPlayer);
    }

    public void newGame(int size){
        if (this.tray != null){
            this.tray.editSize(size);
        } else {
            this.tray = new Tray(size,size);
        }
        this.currentPlayer = Color.BLUE;
        this.inGame = true;

        this.setChanged();
        this.notifyObservers();
    }


    /*
    * ACCESSORS
    * */

    public Tray getTray() {
        return tray;
    }

    public boolean isInGame() {
        return inGame;
    }

    public Color getWinner() {
        return winner;
    }


    public void changeTrayForm(int form) {
        this.tray.editTrayForm(60, form);
        this.setChanged();
        this.notifyObservers();
    }
}
