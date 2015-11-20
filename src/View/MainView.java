package View;

import Controller.Controller;
import Model.GameModel;
import sun.applet.Main;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by raphael on 15/11/15.
 */
public class MainView extends JFrame implements Observer {

    private GameModel model;
    private Controller controller;
    private int width = 550;
    private int height = 500;
    private Click mouseClick;
    private JPanel gamePannel;

    public MainView(Controller controller, GameModel model) throws NullPointerException{
        if (controller == null || model == null){
            throw new NullPointerException();
        }
        this.setSize(this.width, this.height);

        this.controller = controller;
        this.model = model;
        this.model.addObserver(this);

        this.mouseClick = new Click(this.controller, this.model);
        this.addMouseListener(mouseClick);

        // set game pannel
        this.gamePannel = new GamePannel(this.width, this.height, this.model.getTray());
        this.setContentPane(this.gamePannel);
        gamePannel.setLocation(0,0);

        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o != null && o instanceof GameModel){
            this.repaint();
            if (!this.model.isInGame()){
                if (this.model.getWinner() != null){
                    JOptionPane.showMessageDialog(null, "Winner : " + this.model.getWinner().toString());
                }
            }
        }
    }


}
