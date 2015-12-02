package Model.Exceptions;

/**
 * Created by raphael on 02/12/15.
 */
public class GameRunningException extends Exception {
    public GameRunningException() {
        super("A game is running");
    }
}
