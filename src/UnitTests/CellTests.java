package UnitTests;


import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.Cell;
import Model.Color;
import Model.TrayCoords;

/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 16/11/15.
 */
public class CellTests {

    @Test
    public void cell_reset(){

    	TrayCoords coords = new TrayCoords(200,200);
    	Point graphicPoint = new Point(100,100);


		Cell cell = new Cell(coords, graphicPoint);
    	assertEquals("Color must be EMPTY", cell.getColor(), Color.EMPTY );

		cell.setColor(Color.RED);
    	cell.resetColor();

    	assertEquals("Color must be EMPTY", cell.getColor(), Color.EMPTY );
		assertTrue(cell.getColor().equals(Color.EMPTY));
    }
}
