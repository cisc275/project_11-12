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
	
	@Test
	public void scoringObjectTest() {
		ScoringObject so = new ScoringObject(10,10,1,1,2,"Fish",100,100);
		assertNotNull(so);
		
		//test move()
		so.move();
		
		//test "setters"
		so.setXloc(100);
		so.setYloc(100);
		so.setxIncr(2);
		so.setyIncr(2);
		so.setPointValue(3);
		so.setID("Trash");
		so.setImageWidth(10);
		so.setImageHeight(10);
		
		//test "getters"
		int realx = so.getXloc();
		int realy = so.getYloc();
		int realxIncr = so.getxIncr();
		int realyIncr = so.getyIncr();
		int realpointValue = so.getPointValue();
		String realID = so.getID();
		int realimageW = so.getImageWidth();
		int realimageH = so.getImageHeight();

		assertEquals(realx, so.getXloc());
		assertEquals(realy, so.getYloc());
		System.out.println(realxIncr);
		System.out.println(so.getxIncr());
		assertEquals(realxIncr, so.getxIncr());
		assertEquals(realyIncr, so.getyIncr());
		assertEquals(realpointValue, so.getPointValue());
		assertEquals(realID, so.getID());
		assertEquals(realimageW, so.getImageWidth());
		assertEquals(realimageH, so.getImageHeight());

		
		
	
	}

}