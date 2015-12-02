package View;

import Model.Shape;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by  Raphael Thibierge and Arthur Pavarino (S3A) on 02/12/15.
 */
public class ShapeButton extends JMenuItem {
    private Shape shape;

    public ShapeButton(String text, Shape shape, ActionListener actionListener) throws NullPointerException{
        super(text);
        if (shape == null)
            throw new NullPointerException("shape in ShapeButton's contructor is null");

        this.shape = shape;
        this.addActionListener(actionListener);
    }

    public Shape getShape() {
        return shape;
    }
}
