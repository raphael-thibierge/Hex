package View;

import Controller.Controller;
import Model.Exceptions.BadTraySizeException;
import Model.Exceptions.GameRunningException;
import Model.Tray;
import Model.Shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 20/11/15.
 */
public class Menu extends JMenuBar {

    private Controller controller;

    private ActionListener buttonControl = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent arg0) {

            Object source = arg0.getSource();

            if (source instanceof TraySizeButton){
                try {
                    controller.newGame(((TraySizeButton) source).getTraySize());
                } catch (GameRunningException e) {
                    if (JOptionPane.showConfirmDialog(null, "Une partie est en cours, voulez-vous commencer une nouvelle partie ?",
                                                            "Nouvelle partie", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)
                            == JOptionPane.OK_OPTION)
                    {
                        try {
                            controller.forceNewGame(((TraySizeButton) source).getTraySize());
                        } catch (BadTraySizeException e1) {
                            JOptionPane.showMessageDialog(null, " Taille de grille incorrecte ("
                                    + e1.getNbLine() + ";" + e1.getNbColumn() + ")", "Nouvelle partie", JOptionPane.OK_OPTION);
                        }
                    }
                } catch (BadTraySizeException e) {
                    JOptionPane.showMessageDialog(null, " Taille de grille incorrecte ("
                            + e.getNbLine() + ";" + e.getNbColumn() + ")", "Nouvelle partie", JOptionPane.OK_OPTION);
                }
            }

            else if (source instanceof ShapeButton) {
                controller.changeTrayForm(((ShapeButton) source).getShape());
            }

        }};

    public Menu(Controller controller){
        this.controller = controller;
        initMenu();
    }


    private void initMenu(){

        // First Menu
        JMenu gameMenu = new JMenu("Jeu");
        gameMenu.setMnemonic(KeyEvent.VK_J);
        gameMenu.add(new TraySizeButton("Nouvelle partie", Tray.standardSize, buttonControl));
        this.add(gameMenu);

        // Second Menu
        JMenu traySizeMenu = new JMenu("Grille");
        traySizeMenu.setMnemonic(KeyEvent.VK_G);
        traySizeMenu.add(new TraySizeButton(5, this.buttonControl));
        traySizeMenu.add(new TraySizeButton(7, this.buttonControl));
        traySizeMenu.add(new TraySizeButton(10, this.buttonControl));
        traySizeMenu.add(new TraySizeButton(12, this.buttonControl));
        this.add(traySizeMenu);


        // Third Menu
        JMenu shapeMenu = new JMenu("Forme");
        shapeMenu.setMnemonic(KeyEvent.VK_F);
        shapeMenu.add(new ShapeButton("Classique", Shape.verticalLozange, this.buttonControl));
        shapeMenu.add(new ShapeButton("Losange", Shape.horizontalLozange, this.buttonControl));
        this.add(shapeMenu);
    }
}
