package UnitTests;

import static org.junit.Assert.*;

import Model.Cell;
import Model.Exceptions.BadTraySizeException;
import org.junit.Test;

import Model.Color;
import Model.Tray;
import Model.TrayCoords;

/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 23/11/15.
 */

public class TrayTests {
	
	@Test
	public void tray_getCell() throws BadTraySizeException {
		Tray tray = new Tray(5,5);
		TrayCoords coords = new TrayCoords(1,3);
		
		assertTrue("Mustn'tray be null", tray.getCell(coords) != null);
		assertEquals("color must be EMPTY", tray.getCell(coords).getColor(), Color.EMPTY);
		assertEquals("coords must be equals", tray.getCell(coords).getCoords(),coords);
	}

	@Test
	public void getCellOutOfArray() throws BadTraySizeException {
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
	public void trayInit() throws BadTraySizeException {
		Tray tray = new Tray(10,10);
		for (int line = 0 ; line < tray.getNbLine()  ; line++) {
			for (int column = 0; column < tray.getNbColumn(); column++) {
				assertEquals("color must be EMPTY", tray.getCell(new TrayCoords(column, line)).getColor(), Color.EMPTY);
			}
		}
	}
	
	@Test
	public void tray_isFull() throws BadTraySizeException {
		Tray tray = new Tray(2,2);
		assertFalse("tray is not full", tray.isFull());

		// fill tray
		tray.putTocken(new TrayCoords(0, 0), Color.BLUE);
		tray.putTocken(new TrayCoords(0, 1), Color.RED);
		tray.putTocken(new TrayCoords(1, 0), Color.BLUE);
		tray.putTocken(new TrayCoords(1, 1), Color.RED);

		assertTrue("tray is full", tray.isFull());
	}

	private void shortDiagonal(Color color) throws BadTraySizeException {
		Tray tray = new Tray(3,3);
		tray.putTocken(new TrayCoords(0, 0), color);
		tray.putTocken(new TrayCoords(1, 1), color);
		tray.putTocken(new TrayCoords(2, 2), color);
		assertTrue(color + " won", tray.testVictory(color));
	}

	@Test
	public void victory_ShortDiagonal(){
        try {
            shortDiagonal(Color.RED);
            shortDiagonal(Color.BLUE);
        } catch (BadTraySizeException e) {
            e.printStackTrace();
        }
	}


	@Test
	public void victory_HorizontalVertical() throws BadTraySizeException {
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
	public void noVictoryAfterInitTray() throws BadTraySizeException {
		Tray tray = new Tray(3,3);
		assertFalse("blue didn't won", tray.testVictory(Color.BLUE));
		assertFalse("red didn't won", tray.testVictory(Color.RED));

	}

	
	@Test
	public void tray_testValideCoords() throws BadTraySizeException {
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
	public void tray_TestPutTocken() throws BadTraySizeException {
		
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

	private boolean testNbNeighbor(Tray tray, TrayCoords coords, int expected){
		return tray.getNeighborCellsList(tray.getCell(coords)).size() == expected;
	}

	@Test
	public void neighborListTest_leftTopCornerCell() throws BadTraySizeException {
		Tray tray = new Tray(3,3);
		// corners
		assertTrue(testNbNeighbor(tray, new TrayCoords(0,0), 3));
		assertTrue(testNbNeighbor(tray, new TrayCoords(0,2), 2));
		assertTrue(testNbNeighbor(tray, new TrayCoords(2,2), 3));
		assertTrue(testNbNeighbor(tray, new TrayCoords(2,0), 2));

		// middle
		assertTrue(testNbNeighbor(tray, new TrayCoords(1,1), 6));

		// others
		assertTrue(testNbNeighbor(tray, new TrayCoords(0,1), 4));
		assertTrue(testNbNeighbor(tray, new TrayCoords(1,0), 4));
		assertTrue(testNbNeighbor(tray, new TrayCoords(1,2), 4));
		assertTrue(testNbNeighbor(tray, new TrayCoords(2,1), 4));
	}

	private void testNeighbors(Tray tray, Cell cell){
		Cell test;
		test = tray.getCell(new TrayCoords(cell.getCoords().getLine(),cell.getCoords().getColumn()+1));
		if (test != null)
			assertTrue(tray.getNeighborCellsList(cell).contains(test));

		test = tray.getCell(new TrayCoords(cell.getCoords().getLine(),cell.getCoords().getColumn()-1));
		if (test != null)
			assertTrue(tray.getNeighborCellsList(cell).contains(test));

		test = tray.getCell(new TrayCoords(cell.getCoords().getLine()+1,cell.getCoords().getColumn()));
		if (test != null)
			assertTrue(tray.getNeighborCellsList(cell).contains(test));

		test = tray.getCell(new TrayCoords(cell.getCoords().getLine()-1,cell.getCoords().getColumn()));
		if (test != null)
			assertTrue(tray.getNeighborCellsList(cell).contains(test));

		test = tray.getCell(new TrayCoords(cell.getCoords().getLine()+1,cell.getCoords().getColumn()+1));
		if (test != null)
			assertTrue(tray.getNeighborCellsList(cell).contains(test));

		test = tray.getCell(new TrayCoords(cell.getCoords().getLine()-1,cell.getCoords().getColumn()-1));
		if (test != null)
			assertTrue(tray.getNeighborCellsList(cell).contains(test));
	}

	@Test
	public void neighborListTest_containsGoodOne() throws BadTraySizeException {
		Tray tray = new Tray(12,12);
		for (Cell cell : tray.getCellList()){
			testNeighbors(tray, cell);
		}
	}



}