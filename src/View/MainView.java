/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 15/11/15.
 */

package View;

import Controller.Controller;
import Model.GameModel;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.util.Observable;
import java.util.Observer;

public class MainView extends JFrame implements Observer {

    private GameModel model;
    private Controller controller;

    private Click mouseClick;
    private JPanel gamePannel;
    private Menu menu;

    private static int standardWidth = 700;
    private static int standardHeight = 480;
    private static int minimalWidth = 300;
    private static int minimalHeight = 300;


    public MainView(Controller controller, GameModel model) throws NullPointerException{
        if (controller == null){
            throw new NullPointerException("controller in MainView's constructor is null");
        }
        if (model == null){
            throw new NullPointerException("model in MainView's constructor is null");
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
        this.menu = new Menu(this.controller);
        this.setJMenuBar(menu);

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


