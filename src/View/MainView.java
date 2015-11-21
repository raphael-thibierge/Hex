package View;

import Controller.Controller;
import Model.GameModel;
import sun.applet.Main;

import javax.swing.*;
import javax.swing.event.MenuListener;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    private JMenuBar mb;
    private JMenu game;
    private JMenu trayOptions;
    private JMenu formOptions;
    private JMenuItem newg;
    private JMenuItem g5;
    private JMenuItem g8;
    private JMenuItem g10;
    private JMenuItem g12;
    private JMenuItem classique;
    private JMenuItem loz;
    private ActionListener ButtonControl = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if( arg0.getSource() == newg)
			{
				model.getTray().initTray();
				
			}
			else if (arg0.getSource() == g5)
			{
				model.getTray().editSize(5);
				model.getTray().initTray();
			}
			else if (arg0.getSource() == g8)
			{
				model.getTray().editSize(10);
				model.getTray().initTray();
			}
			
			else if (arg0.getSource() == g10)
			{
				model.getTray().editSize(12);
				model.getTray().initTray();
			}
			
			else if (arg0.getSource() == g12)
			{
				model.getTray().editSize(12);
				model.getTray().initTray();
			}
			else if (arg0.getSource() == classique)
			{
				model.getTray().editTrayForm(60,2);
			}
			else if (arg0.getSource() == loz)
			{
				model.getTray().editTrayForm(60,1);
			}
			
			repaint();
		}};

    public MainView(Controller controller, GameModel model) throws NullPointerException{
        if (controller == null || model == null){
            throw new NullPointerException();
        }
        this.setSize(this.width, this.height);

        this.controller = controller;
        this.model = model;
        this.model.addObserver(this);
        this.setTitle("HEX - THIBIERGE PAVARINO S3A");

        this.mouseClick = new Click(this.controller, this.model);
        this.addMouseListener(mouseClick);

        // set game pannel
        this.gamePannel = new GamePannel(this.width, this.height, this.model.getTray());
        this.setContentPane(this.gamePannel);
        gamePannel.setLocation(0,0);
        
        // The menu bar
        this.mb = new JMenuBar();
        this.setJMenuBar(mb);
        
        // First Menu
        game = new JMenu();
        game.setText("Jeu");
        game.setMnemonic(KeyEvent.VK_J);
        mb.add(game);
        
        // Item of "game"
        newg = new JMenuItem("Nouvelle partie");
        game.add(newg);
        newg.addActionListener(ButtonControl);
        
        // Second Menu
        trayOptions = new JMenu();
        trayOptions.setText("Grille");
        trayOptions.setMnemonic(KeyEvent.VK_G);
        mb.add(trayOptions);
        
        //Item of "TrayOptions"
        g5 = new JMenuItem("Grille 5x5");
        trayOptions.add(g5);
        g5.addActionListener(ButtonControl);
        
        g8 = new JMenuItem("Grille 8x8");
        trayOptions.add(g8);
        g8.addActionListener(ButtonControl);
        
        g10 = new JMenuItem("Grille 10x10");
        trayOptions.add(g10);
        g10.addActionListener(ButtonControl);
        
        g12= new JMenuItem("Grille 12x12");
        trayOptions.add(g12);
        g12.addActionListener(ButtonControl);
        
        // Third Menu
        formOptions = new JMenu();
        formOptions.setText("Forme");
        formOptions.setMnemonic(KeyEvent.VK_F);
        mb.add(formOptions);
        
        //Item of "TrayForms"
        classique = new JMenuItem("Classique");
        formOptions.add(classique);
        classique.addActionListener(ButtonControl);
        
        loz = new JMenuItem("Losange");
        formOptions.add(loz);
        loz.addActionListener(ButtonControl);
        
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("zbraa");
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


