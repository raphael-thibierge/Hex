/**
 * Created by  Raphael Thibierge and Arthur Pavarino (S3A) on 02/12/15.
 */
package UnitTests;

import Model.*;
import Model.Exceptions.BadTraySizeException;
import Model.Exceptions.GameModelHasNoTrayException;
import Model.Exceptions.GameRunningException;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class GameModelTest {
    @Test
    public void putTocken_changePlayerTest() throws BadTraySizeException {
        GameModel gameModel = new GameModel();
        try {
            gameModel.newGame(2);
        } catch (GameRunningException e) {
            e.printStackTrace();
        }

        // first turn
        TrayCoords trayCoords = new TrayCoords(0,0);
        gameModel.putTocken(trayCoords);
        assertTrue("Cell's color must be blue ( first player )", gameModel.getTray().getCell(trayCoords).getColor().equals(Color.BLUE));

        // second turn
        trayCoords = new TrayCoords(1,0);
        gameModel.putTocken(trayCoords);
        assertTrue("Cell's color must be red ( second player )", gameModel.getTray().getCell(trayCoords).getColor().equals(Color.RED));
    }

    @Test
    public void putTocken_victoryWithWinnerTest() throws BadTraySizeException {
        GameModel gameModel = new GameModel();
        try {
            gameModel.newGame(2);
        } catch (GameRunningException e) {
            e.printStackTrace();
        }

        // first turn
        gameModel.putTocken(new TrayCoords(0,0));
        assertTrue("No winner", gameModel.getWinner() == null);
        // second turn
        gameModel.putTocken(new TrayCoords(0, 1));
        assertTrue("No winner", gameModel.getWinner() == null);
        // third turn
        gameModel.putTocken(new TrayCoords(1,0));
        // victory is well tested if game is finished and winner != null
        assertFalse("Game is finished", gameModel.isInGame());
        assertTrue("A player won the game", gameModel.getWinner() != null);
    }

    @Test
    public void newGameTest() throws BadTraySizeException {
        // init game
        GameModel gameModel = new GameModel();
        try {
            gameModel.newGame(5);
        } catch (GameRunningException e) {
            e.printStackTrace();
        }
        // game has begun
        assertTrue("New game has begun", gameModel.isInGame());
        assertTrue("No winner yet", gameModel.getWinner() == null);
        // tray has the good dimension
        assertTrue("Tray line number is 5", gameModel.getTray().getNbLine() == 5);
        assertTrue("Tray column number is 5", gameModel.getTray().getNbColumn() == 5);
    }

    @Test
    public void changeTrayForm() throws BadTraySizeException, GameModelHasNoTrayException {
        GameModel gameModel = new GameModel();
        try {
            gameModel.newGame(5);
        } catch (GameRunningException e) {
            e.printStackTrace();
        }
        // change tray form a first time
        gameModel.changeTrayForm(Shape.verticalLozange);
        assertTrue("Shape must be verticalLozange", gameModel.getTray().getShape().equals(Shape.verticalLozange));
        // change tray form a second time
        gameModel.changeTrayForm(Shape.horizontalLozange);
        assertTrue("Shape must be horizontalLozange", gameModel.getTray().getShape().equals(Shape.horizontalLozange));
    }
}
