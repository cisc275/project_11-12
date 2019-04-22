/**
 * 
 */
package project;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.junit.Test;


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
		assertEquals(actualxIncr, cr.xIncr);
		assertEquals(actualyIncr, cr.yIncr);
		assertEquals(actualimagewid, cr.imageWidth);
		assertEquals(actualimagehgt, cr.imageHeight);
		
	}
	
	@Test
	public void modelTest() {
		Model m = new Model();
		//need view for switching games to test
		View v = new View();
		
		
		assertNotNull(m.scoringObjects);
		assertNotNull(m.g2locations);
		assertNotNull(m.g2occupancy);
		
		m.runGameOne();
		assertNotNull(m.p);
		
		//update game1 enough times to test fish objects
		for (int i = 0; i < 400; i++) {
			m.updateGameOne();
		}
		
		
		m.runGameTwo();
		assertNotNull(m.p);
		//update game2 enough times to test trash/food objects
		for (int i = 0; i < 100; i++) {
			m.updateGameTwo();
		}
		//testing updateGame() for each game
		v.game1Panel();
		m.updateGame();
		v.game2Panel();
		m.updateGame();	
	}
	
	@Test
	public void ospreyTest() {
		Osprey o = new Osprey(0,0,2,2,100,100);
		assertNotNull(o);
		
		//test "setters"
		o.setxLoc(10);
		o.setyLoc(900);
		o.setxIncr(5);
		o.setyIncr(-5);
		
		//test move()
		o.move();
		
		//test "getters"
		int actualx = o.getxLoc();
		int actualy = o.getyLoc();
		int actualxIncr = o.getxIncr();
		int actualyIncr = o.getyIncr();
		int actualimagewid = o.getimageWidth();
		int actualimagehgt = o.getimageHeight();
		
		assertEquals(actualx, o.xloc);
		assertEquals(actualy, o.yloc);
		assertEquals(actualxIncr, o.xIncr);
		assertEquals(actualyIncr, o.yIncr);
		assertEquals(actualimagewid, o.imageWidth);
		assertEquals(actualimagehgt, o.imageHeight);
	}
	
	@Test
	public void questionTest() {
		Question q = new Question();
		assertNotNull(q);
	}
	
	@Test
	public void controllerTest() {
		Controller c = new Controller();
		assertNotNull(c);
		assertEquals(c.upflag, false);
		assertEquals(c.downflag, true);
		
		c.main(null);
		c.keyReleased(null);
		c.keyTyped(null);
			
	}
	
	@Test
	public void scoringTest() {
		Scoring s = new Scoring();
		assertNotNull(s);
		assertNotNull(s.ScoringTable);
		
		ScoringObject sc = new ScoringObject(0, 0, 10, 10, 1, "egg", 100, 100);
		s.updateScore(sc);
		
		int actual = s.amountContained("egg");
		assertEquals(1,actual);
		
	}
	
	@Test
	public void viewTest() {
		View v = new View();
		BufferedImage bi = v.createBufferedImage();
		assertNotNull(bi);
		
		v.game1Panel();
		v.game2Panel();
		v.instructPanel();
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
	
	@Test
	public void foxTest() {
		Fox f = new Fox(5,5,1,1);
		assertNotNull(f);
		
		assertEquals(0, f.questions.size());
		
		//test "setters"
		f.setXloc(20);
		f.setYloc(20);
		f.setXincr(2);
		f.setYincr(-2);
		
		//test moveEnter()
		f.moveEnter();
		
		//test moveExit()
		f.moveExit();
		
		//test "getters"
		int realx = f.getXloc();
		int realy = f.getYloc();
		int realxIncr = f.getXincr();
		int realyIncr = f.getYincr();

		assertEquals(realx, f.xloc);
		assertEquals(realy, f.yloc);
		assertEquals(realxIncr, f.xincr);
		assertEquals(realyIncr, f.yincr);

	}

}
