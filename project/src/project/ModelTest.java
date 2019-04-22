/**
 * 
 */
package project;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Anna Bortle
 *
 */
public class ModelTest {

	@Test
	public void clapperRailTest() {
		ClapperRail cr = new ClapperRail(0,0,2,2,100,100);
		assertNotNull(cr);
		
		//test "setters"
		cr.setxLoc(10);
		cr.setyLoc(10);
		cr.setxIncr(5);
		cr.setyIncr(-5);
		
		//test move()
		cr.move();
		
		//test "getters"
		int actualx = cr.getxLoc();
		int actualy = cr.getyLoc();
		int actualxIncr = cr.getxIncr();
		int actualyIncr = cr.getyIncr();
		int actualimagewid = cr.getimageWidth();
		int actualimagehgt = cr.getimageHeight();
		
		assertEquals(actualx, cr.xloc);
		assertEquals(actualy, cr.yloc);
		System.out.println(actualxIncr);
		System.out.println(cr.xIncr);
		assertEquals(actualxIncr, cr.xIncr);
		assertEquals(actualyIncr, cr.yIncr);
		assertEquals(actualimagewid, cr.imageWidth);
		assertEquals(actualimagehgt, cr.imageHeight);
		
	}
	
	@Test
	public void modelTest() {
		Model m = new Model();
		View v = new View();
		
		
		assertNotNull(m.scoringObjects);
		assertNotNull(m.g2locations);
		assertNotNull(m.g2occupancy);
		
		m.updateGame();
	
		
	}

}
