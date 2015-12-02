package View;

import Controller.Controller;
import Model.GameModel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 15/11/15.
 */
public class Click extends MouseInputAdapter{
    private Controller controller;
    private GameModel model;

    public Click(Controller controller, GameModel model) throws NullPointerException{
        if (controller == null)
            throw new NullPointerException("controller in Click's constructor is null");
        if (model == null)
            throw new NullPointerException("model in Click's constructor is null");

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
