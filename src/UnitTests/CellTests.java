package UnitTests;


import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.Cell;
import Model.Color;
import Model.TrayCoords;

/**
 * Created by raphael on 16/11/15.
 */
public class CellTests {

    @Test
    public void cell_reset(){

    	TrayCoords tc = new TrayCoords(200,200);
    	Point p = new Point(100,100);
    	Cell c = new Cell(tc, p);
    	c.reset();
    	
    	assertEquals("Color must be EMPTY", c.getColor(), Color.EMPTY );
    }
}
