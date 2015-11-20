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
		TrayCoords tc = new TrayCoords(1,3);
		
		
		assertTrue("Mustn't be null", t.getCell(tc) != null);
		assertEquals("color must be EMPTY", t.getCell(tc).getColor(), Color.EMPTY);
		assertEquals("coord must be equals", t.getCell(tc).getCoords(),tc);
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
	
	@Test
	public void tray_isFull()
	{
		Tray t = new Tray(2,2);
		
		assertFalse("tray is not full", t.isFull());
		
		t.putTocken(new TrayCoords(0,0), Color.BLUE);
		t.putTocken(new TrayCoords(0,1), Color.RED);
		t.putTocken(new TrayCoords(1,0), Color.BLUE);
		t.putTocken(new TrayCoords(1,1), Color.RED);
		
		assertTrue("tray is full", t.isFull());
	}
	
	@Test
	public void tray_testVictory()
	{
		
		// BLEU : de gauche à droite
		// ROUGE : de haut en bas
		
		// Teste pour chaque joueur :
		
		// Une grille sans gagnant
		// Une grille où rouge gagne
		// Une grille où bleu gagne
		// Une grille où bleu & rouge gagne (thoriquement impossible)
		// Une grille où bleu à le placement gagnant de rouge
		// Une grille où rouge à le placement gagnant de bleu
		
		
		// Pas de gagnant
		
		Tray t = new Tray(3,3);
		assertFalse("blue didn't won",t.testVictory(Color.BLUE));
		assertFalse("red didn't won",t.testVictory(Color.RED));
		
		//Bleu gagne, rouge perd
		
		t.putTocken(new TrayCoords(0,0), Color.BLUE);
		t.putTocken(new TrayCoords(1,1), Color.BLUE);
		t.putTocken(new TrayCoords(2,2), Color.BLUE);
		   
		//B00
		//0B0
		//00B
		   
		assertTrue("blue won",t.testVictory(Color.BLUE));
		assertFalse("red didn't won",t.testVictory(Color.RED));
		 
		//Bleu gagne, rouge gagne
		
		t.putTocken(new TrayCoords(0,2), Color.RED);
		t.putTocken(new TrayCoords(2,1), Color.RED);
		t.putTocken(new TrayCoords(1,0), Color.RED);
		
		//B0R
		//RBR
		//0RB
		
		assertTrue("blue won",t.testVictory(Color.BLUE));
		assertTrue("red won",t.testVictory(Color.RED));
		
		// Rouge gagne, bleu perd
		
		t.getCell(new TrayCoords(2,2)).setColor(Color.EMPTY);
		
		//B0R
		//RBR
		//0R0
		
		assertTrue("red won",t.testVictory(Color.RED));
		assertFalse("blue didn't won",t.testVictory(Color.BLUE));
		
		// Grille où bleu à le placement gagnant de rouge et vis versa
		
		Tray t2 = new Tray(3,3);
		
		t2.putTocken(new TrayCoords(0,0), Color.BLUE);
		t2.putTocken(new TrayCoords(1,1), Color.BLUE);
		t2.putTocken(new TrayCoords(2,0), Color.BLUE);
		
		t2.putTocken(new TrayCoords(1,0), Color.RED);
		t2.putTocken(new TrayCoords(0,1), Color.RED);
		t2.putTocken(new TrayCoords(1,2), Color.RED);
		
		//BR0
		//RBR
		//B00
		
		assertFalse("blue didn't won",t2.testVictory(Color.BLUE));
		assertFalse("red didn't won",t2.testVictory(Color.RED));
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
}
