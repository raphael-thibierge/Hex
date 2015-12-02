package View;

import javax.swing.*;
import javax.swing.text.StringContent;
import java.awt.event.ActionListener;

/**
 * Created by  Raphael Thibierge and Arthur Pavarino (S3A) on 02/12/15.
 */
public class TraySizeButton extends JMenuItem {
    private int traySize;

    public TraySizeButton(int size, ActionListener actionListener){
        super("Grille " + size + "x" + size);
        this.traySize = size;
        this.addActionListener(actionListener);
    }

    public TraySizeButton(String text, int size, ActionListener actionListener){
        super(text);
        this.traySize = size;
        this.addActionListener(actionListener);
    }

    public int getTraySize() {
        return traySize;
    }
}
