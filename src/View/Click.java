package View;

import Controller.Controller;
import Model.GameModel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by raphael on 15/11/15.
 */
public class Click extends MouseInputAdapter{
    private Controller controller;
    private GameModel model;

    public Click(Controller controller, GameModel model) {
        this.controller = controller;
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (this.model.isInGame()){
            this.controller.placeToken(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }
}
