package project;

import java.awt.image.BufferedImage;

public class ScoringObject {
	int xloc,yloc,xIncr,yIncr;
	String ID;
	int pointValue;
	BufferedImage[][] imageArray;
	ScoringObject(int x, int y, int xInc, int yInc, int pV, String ID){
		this.xloc = x;
		this.yloc = y;
		this.xIncr = xInc;
		this.yIncr = yInc;
		this.pointValue = pV;
		this.ID = ID;
	}
	
	/**
	 * updates location(xloc and yloc) for  ScoringObject object
	 * @param none
	 * @return none
	 */
	public void move() {
		this.xloc += this.xIncr;
		this.yloc += this.yIncr;
	}
	
	
}