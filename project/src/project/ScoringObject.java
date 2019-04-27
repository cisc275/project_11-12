package project;

import java.awt.image.BufferedImage;

public class ScoringObject {
	int xloc,yloc,xIncr,yIncr;
	int imageWidth, imageHeight;
	String ID;
	int pointValue;
	BufferedImage[][] imageArray;
	final static int g2_lifetime = 65;
	
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

	public int getXloc() {
		return xloc;
	}

	public void setXloc(int xloc) {
		this.xloc = xloc;
	}

	public int getYloc() {
		return yloc;
	}

	public void setYloc(int yloc) {
		this.yloc = yloc;
	}

	public int getxIncr() {
		return xIncr;
	}

	public void setxIncr(int xIncr) {
		this.xIncr = xIncr;
	}

	public int getyIncr() {
		return yIncr;
	}

	public void setyIncr(int yIncr) {
		this.yIncr = yIncr;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	
}
	
