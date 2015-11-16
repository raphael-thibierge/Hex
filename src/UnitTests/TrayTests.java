package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Color;
import Model.Tray;
import Model.TrayCoords;

public class TrayTests {
	
	@Test
	public void tray_getCell()
	{
		Tray t = new Tray(5,5);
		assertTrue("Mustn't be null", t.getCell(new TrayCoords(1,1)) != null);
		//assertEquals("color must be EMPTY", t.getCell(new TrayCoords(1,1)).getColor(), Color.EMPTY);
		
		
	
	}
	
	
	@Test
	public void tray_reset()
	{
		Tray t = new Tray(10,10);
		for (int line = 0 ; line < t.getNbLine()  ; line++)
			for (int column = 0 ; column < t.getNbColumn() ; column++)
			{
			assertEquals("color must be EMPTY", t.getCell(new TrayCoords(column, line)).getColor(), Color.EMPTY);
			}
	}

}
