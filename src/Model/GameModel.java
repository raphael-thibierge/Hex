/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 15/11/15.
 */
package Model;

import Model.Exceptions.BadTraySizeException;
import Model.Exceptions.InitGameModelErrorException;
import Model.Exceptions.GameRunningException;

import java.awt.*;
import java.util.Observable;

public class GameModel extends Observable {

    private Color currentPlayer;
    private Tray tray;

    private boolean inGame = false;
    private Color winner = null;
    private int width = 0;
    private int height = 0;


    public GameModel() throws InitGameModelErrorException {
        try {
            this.tray = new Tray(Tray.standardSize, Tray.standardSize);
            this.newGame(Tray.standardSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitGameModelErrorException();
        }
    }

    public void putTocken(TrayCoords coords){
        // if token is placed on tray
        if (inGame && tray.putTocken(coords, currentPlayer)){
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

    public void newGame(int size) throws BadTraySizeException, GameRunningException {
        // if a game is running, throw exception
        if (!this.tray.isEmpty() && isInGame())
            throw new GameRunningException();

        //  edit tray's sizes
        this.tray.editSize(size);
        this.tray.setGraphicSize(this.width, this.height);
        // set first player color
        this.currentPlayer = Color.BLUE;
        // game start
        this.inGame = true;
        // notify
        this.setChanged();
        this.notifyObservers();
    }

    public void stopGame(){
        this.inGame = false;
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

    public void setGraphicSize(int width, int height) {
        this.width = width;
        this.height = height;

        if (tray != null) {
            this.tray.setGraphicSize(this.width, this.height);
        }

        this.setChanged();
        this.notifyObservers();
    }

    public TrayCoords clickOnGrid(Point point) {
        return this.tray.clickOnGrid(point);
    }
}
