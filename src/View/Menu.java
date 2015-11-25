package View;

import Controller.Controller;
import Model.Tray;
import Model.Shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by arthur on 20/11/15.
 */
public class Menu extends JMenuBar {

    private Controller controller;

    private ActionListener buttonControl = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent arg0) {

            if( arg0.getSource() == newg) {
                controller.newGame(Tray.standardSize);
            }
            else if (arg0.getSource() == g5) {
                controller.newGame(5);
            }
            else if (arg0.getSource() == g7) {
                controller.newGame(7);
            }

            else if (arg0.getSource() == g10) {
                controller.newGame(10);
            }

            else if (arg0.getSource() == g12) {
                controller.newGame(12);
            }

            else if (arg0.getSource() == classique) {
                controller.changeTrayForm(Shape.verticalLozange);
            }

            else if (arg0.getSource() == loz) {
                controller.changeTrayForm(Shape.horizontalLozange);
            }

        }};

    public Menu(Controller controller){
        this.controller = controller;
        initMenu();
    }

    private JMenu game;
    private JMenu trayOptions;
    private JMenu formOptions;
    private JMenuItem newg;
    private JMenuItem g5;
    private JMenuItem g7;
    private JMenuItem g10;
    private JMenuItem g12;
    private JMenuItem classique;
    private JMenuItem loz;

    private void initMenu(){

        // First Menu
        game = new JMenu();
        game.setText("Jeu");
        game.setMnemonic(KeyEvent.VK_J);
        this.add(game);

        // Item of "game"
        newg = new JMenuItem("Nouvelle partie");
        game.add(newg);
        newg.addActionListener(this.buttonControl);

        // Second Menu
        trayOptions = new JMenu();
        trayOptions.setText("Grille");
        trayOptions.setMnemonic(KeyEvent.VK_G);
        this.add(trayOptions);

        //Item of "TrayOptions"
        g5 = new JMenuItem("Grille 5x5");
        trayOptions.add(g5);
        g5.addActionListener(this.buttonControl);

        g7 = new JMenuItem("Grille 8x8");
        trayOptions.add(g7);
        g7.addActionListener(this.buttonControl);

        g10 = new JMenuItem("Grille 10x10");
        trayOptions.add(g10);
        g10.addActionListener(this.buttonControl);

        g12= new JMenuItem("Grille 12x12");
        trayOptions.add(g12);
        g12.addActionListener(this.buttonControl);

        // Third Menu
        formOptions = new JMenu();
        formOptions.setText("Forme");
        formOptions.setMnemonic(KeyEvent.VK_F);
        this.add(formOptions);

        //Item of "TrayForms"
        classique = new JMenuItem("Classique");
        formOptions.add(classique);
        classique.addActionListener(this.buttonControl);

        loz = new JMenuItem("Losange");
        formOptions.add(loz);
        loz.addActionListener(this.buttonControl);

    }

}
