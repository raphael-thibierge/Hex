package View;

import Controller.Controller;
import Model.GameModel;
import sun.applet.Main;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by raphael on 15/11/15.
 */
public class MainView extends JFrame implements Observer {

    private GameModel model;
    private Controller controller;
    private int width;
    private int height;
    private Click mouseClick;
    private JPanel gamePannel;

    public MainView(Controller controller, GameModel model) throws NullPointerException{
        if (controller == null || model == null){
            throw new NullPointerException();
        }

        this.controller = controller;
        this.model = model;

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o != null && o instanceof GameModel){
            displayGrid();
        }
    }

    private void displayGrid(){

    }
}
