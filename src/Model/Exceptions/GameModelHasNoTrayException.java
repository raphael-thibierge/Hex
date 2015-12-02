package Model.Exceptions;

/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 02/12/15.
 */
public class GameModelHasNoTrayException extends Exception {
    @Override
    public String toString() {
        return "GameModel has not any tray";
    }
}
