package UnitTests;

import Model.TrayCoords;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by raphael on 23/11/15.
 */
public class TrayCoordsTest {

    @Test
    public void equals_notNull(){
        TrayCoords trayCoords = new TrayCoords(1,1);
        assertFalse("tray coords is not null", trayCoords.equals(null));
    }

    @Test
    public void equals_sameInstance(){
        TrayCoords trayCoords = new TrayCoords(1,1);
        assertTrue("traycoords must be equals with him self", trayCoords.equals(trayCoords));
    }

    @Test
    public void equals_DifferenceInstance(){
        TrayCoords trayCoords1 = new TrayCoords(1,2);
        TrayCoords trayCoords2 = new TrayCoords(1,2);
        assertTrue("Instances have the same X and Y", trayCoords1.equals(trayCoords2));
    }

    @Test
    public void notEquals_DifferentCoords(){
        TrayCoords trayCoords1 = new TrayCoords(1,2);
        TrayCoords trayCoords2 = new TrayCoords(2,1);
        assertFalse("Instances have not the same X and Y", trayCoords1.equals(trayCoords2));
    }

    @Test
    public void notEquals_sameX(){
        TrayCoords trayCoords1 = new TrayCoords(1,2);
        TrayCoords trayCoords2 = new TrayCoords(1,1);
        assertFalse("Instances have the same X but not same Y", trayCoords1.equals(trayCoords2));
    }

    @Test
    public void notEquals_sameY(){
        TrayCoords trayCoords1 = new TrayCoords(1,2);
        TrayCoords trayCoords2 = new TrayCoords(2,2);
        assertFalse("Instances have the same Y but not same X", trayCoords1.equals(trayCoords2));
    }
}
