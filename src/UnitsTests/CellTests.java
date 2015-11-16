package UnitsTests;

import Model.Cell;
import Model.Color;
import Model.GraphicPosition;
import Model.TrayCoords;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by raphael on 15/11/15.
 */
public class CellTests {


    @Test
    public void reset_Test(){
        Cell cell = new Cell(new TrayCoords(0,0), new GraphicPosition(50,50));
        cell.setColor(Color.BLUE);
        cell.reset();
        assertTrue("Color must be empty", cell.getColor() == Color.EMPTY);

    }

}
