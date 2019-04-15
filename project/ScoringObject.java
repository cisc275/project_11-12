package project;

import java.awt.image.BufferedImage;

public class ScoringObject {
	int xloc,yloc,xIncr,yIncr;
	int imageWidth, imageHeight;
	String ID;
	int pointValue;
	BufferedImage[][] imageArray;
	ScoringObject(int x, int y, int xInc, int yInc, int pV, String ID, int iW, int iH){
		this.xloc = x;
		this.yloc = y;
		this.xIncr = xInc;
		this.yIncr = yInc;
		this.pointValue = pV;
		this.ID = ID;
		this.imageWidth = iW;
		this.imageHeight = iH;
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
	
