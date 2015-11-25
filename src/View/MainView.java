package View;

import Controller.Controller;
import Model.GameModel;
import Model.Shape;
import Model.Tray;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by raphael on 15/11/15.
 */
public class MainView extends JFrame implements Observer {

    private GameModel model;
    private Controller controller;

    private Click mouseClick;
    private JPanel gamePannel;
    private JMenuBar mb;

    private static int standardWidth = 600;
    private static int standardHeight = 400;
    private static int minimalWidth = 300;
    private static int minimalHeight = 300;


    public MainView(Controller controller, GameModel model) throws NullPointerException{
        if (controller == null || model == null){
            throw new NullPointerException();
        }

        this.controller = controller;
        this.model = model;
        this.model.addObserver(this);

        this.setTitle("HEX - THIBIERGE PAVARINO S3A");
        this.setIconImage(new ImageIcon("icone.png").getImage());

        // set size
        this.setSize(standardWidth, standardHeight);
        this.setMinimumSize(new DimensionUIResource(minimalWidth,minimalHeight));

        // set game pannel
        this.gamePannel = new GamePannel(standardWidth, standardHeight, model, controller);
        this.setContentPane(this.gamePannel);

        // set mouse control
        this.mouseClick = new Click(this.controller, this.model);
        this.gamePannel.addMouseListener(mouseClick);

        // init menu control
        this.mb = new Menu(this.controller);
        this.setJMenuBar(mb);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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


