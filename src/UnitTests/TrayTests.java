package UnitTests;

import org.junit.Test;

import Model.Tray;

public class TrayTests {
	
	@Test
	public void tray_reset()
	{
		Tray t = new Tray(10,10);
		for (int line = 0 ; line < t.getNbLine()  ; line++)
			for (int column = 0 ; column < t.getNbColumn() ; column++)
			{
			//assertEquals("",t[line,column],
			}
	}

}
