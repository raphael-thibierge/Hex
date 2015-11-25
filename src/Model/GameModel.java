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
        tray = new Tray(Tray.standardSize, Tray.standardSize);
        this.inGame = true;
        this.currentPlayer = Color.BLUE;
    }

    public void putTocken(TrayCoords coords){
        // if token is placed on tray
        if (tray != null && inGame && tray.putTocken(coords, currentPlayer)){
            System.out.println(coords.toString());
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
        // tray initialisation
        if (this.tray != null){
            this.tray.editSize(size);
        } else {
            this.tray = new Tray(size,size);
        }
        // set first player color
        this.currentPlayer = Color.BLUE;
        // game satrt
        this.inGame = true;
        // notify
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

    public void changeTrayForm(Shape shape) {
        this.tray.editTrayForm(shape);
        this.setChanged();
        this.notifyObservers();
    }

    public void setSize(int width, int height) {
        if (tray != null){
            this.tray.setGraphicSize(width, height);
            this.setChanged();
            this.notifyObservers();
        }
    }
}
