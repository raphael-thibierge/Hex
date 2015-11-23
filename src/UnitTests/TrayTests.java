package UnitTests;

import static org.junit.Assert.*;

import Model.Cell;
import org.junit.Test;

import Model.Color;
import Model.Tray;
import Model.TrayCoords;

import java.util.ArrayList;

public class TrayTests {
	
	@Test
	public void tray_getCell()
	{
		Tray t = new Tray(5,5);
		TrayCoords tc = new TrayCoords(1,3);
		
		assertTrue("Mustn't be null", t.getCell(tc) != null);
		assertEquals("color must be EMPTY", t.getCell(tc).getColor(), Color.EMPTY);
		assertEquals("coords must be equals", t.getCell(tc).getCoords(),tc);
	}

	@Test
	public void getCellOutOfArray(){
		Tray tray = new Tray(2,2);

		// NEGATIVE VALUES
		assertTrue("Must be null", tray.getCell(new TrayCoords(0,-1)) == null);
		assertTrue("Must be null", tray.getCell(new TrayCoords(-1,0)) == null);
		assertTrue("Must be null", tray.getCell(new TrayCoords(-1,-1)) == null);

		// ARRAY INDEX OUT OF BOUNDS
		assertTrue("Must be null", tray.getCell(new TrayCoords(2,2)) == null);
		assertTrue("Must be null", tray.getCell(new TrayCoords(1,2)) == null);
		assertTrue("Must be null", tray.getCell(new TrayCoords(2,1)) == null);
	}

	
	@Test
	public void trayInit()
	{
		Tray t = new Tray(10,10);
		for (int line = 0 ; line < t.getNbLine()  ; line++) {
			for (int column = 0; column < t.getNbColumn(); column++) {
				assertEquals("color must be EMPTY", t.getCell(new TrayCoords(column, line)).getColor(), Color.EMPTY);
			}
		}
	}
	
	@Test
	public void tray_isFull()
	{
		Tray t = new Tray(2,2);
		assertFalse("tray is not full", t.isFull());

		// fill tray
		t.putTocken(new TrayCoords(0,0), Color.BLUE);
		t.putTocken(new TrayCoords(0,1), Color.RED);
		t.putTocken(new TrayCoords(1,0), Color.BLUE);
		t.putTocken(new TrayCoords(1,1), Color.RED);

		assertTrue("tray is full", t.isFull());
	}

	private void shortDiagonal(Color color){
		Tray t = new Tray(3,3);
		t.putTocken(new TrayCoords(0,0), color);
		t.putTocken(new TrayCoords(1,1), color);
		t.putTocken(new TrayCoords(2,2), color);
		assertTrue(color + " won", t.testVictory(color));
	}

	@Test
	public void victory_ShortDiagonal(){
		shortDiagonal(Color.RED);
		shortDiagonal(Color.BLUE);
	}


	@Test
	public void victory_HorizontalVertical(){
		int size = 8;
		for (int i = 0; i < size; i++){
			Tray blueTray = new Tray(size, size);
			Tray redTray = new Tray(size, size);
			for (int y = 0 ; y < size ; y++){
				assertTrue("Red Tocken ("+i+";"+y+")", redTray.putTocken(new TrayCoords(i, y), Color.RED));
				assertTrue("Blue Tocken ("+y+";"+i+")", blueTray.putTocken(new TrayCoords(y, i), Color.BLUE));
			}
			assertTrue("Red victory ! (line "+i+")", redTray.testVictory(Color.RED));
			assertTrue("Blue victory ! (column "+i+")", blueTray.testVictory(Color.BLUE));
		}
	}

	@Test
	public void noVictoryAfterInitTray(){
		Tray t = new Tray(3,3);
		assertFalse("blue didn't won",t.testVictory(Color.BLUE));
		assertFalse("red didn't won",t.testVictory(Color.RED));

	}

	
	@Test
	public void tray_testValideCoords()
	{
		Tray t = new Tray(5,5);
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(6,6)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(2,6)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(6,2)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(-2,2)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(2,-2)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(-3,-3)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(-3,-6)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(-6,5)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(5,5)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(4,5)));
		assertFalse("Coords are invalid",t.valideCoords(new TrayCoords(4,5)));
		
		assertTrue("Coords are valid",t.valideCoords(new TrayCoords(0,0)));
		assertTrue("Coords are valid",t.valideCoords(new TrayCoords(2,2)));
		assertTrue("Coords are valid",t.valideCoords(new TrayCoords(1,4)));
		assertTrue("Coords are valid",t.valideCoords(new TrayCoords(4,1)));
	}
	
	@Test
	public void tray_TestPutTocken(){
		
		Tray t = new Tray(3,3);
		t.putTocken(new TrayCoords(0,0), Color.BLUE);
		assertTrue(t.getCell(new TrayCoords(0,0)).getColor() == Color.BLUE);
		assertFalse(t.getCell(new TrayCoords(0,0)).getColor() == Color.RED);
		assertFalse(t.getCell(new TrayCoords(0,0)).getColor() == Color.EMPTY);
		
		t.putTocken(new TrayCoords(0,1), Color.RED);
		assertTrue(t.getCell(new TrayCoords(0,1)).getColor() == Color.RED);
		assertFalse(t.getCell(new TrayCoords(0,1)).getColor() == Color.BLUE);
		assertFalse(t.getCell(new TrayCoords(0,1)).getColor() == Color.EMPTY);
		
		assertFalse(t.putTocken(new TrayCoords(0,0), Color.RED));
		assertFalse(t.putTocken(new TrayCoords(0,0), Color.BLUE));
		assertTrue(t.putTocken(new TrayCoords(1,0), Color.RED));
		assertTrue(t.putTocken(new TrayCoords(1,1), Color.BLUE));
	}

	@Test
	public void neighborTest_leftTopCornerCell(){
		Tray tray = new Tray(3,3);
		Cell cell = tray.getCell(new TrayCoords(0,0));
		System.out.println(tray.getNeighborCellsList(cell).size());
		ArrayList<Cell> list = tray.getNeighborCellsList(cell);
		assertTrue(tray.getNeighborCellsList(cell).size() == 3);
		// not finished
	}

}