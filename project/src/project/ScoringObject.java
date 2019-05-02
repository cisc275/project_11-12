package project;

import java.awt.image.BufferedImage;

public class ScoringObject extends GameObject{
	String ID;
	int pointValue;
	BufferedImage[][] imageArray;
	//Game 1 Variables
	
	
	//Game 2 Variables
	final static int g2_lifetime = 65;
	int lifetime = 0;
	
	ScoringObject(){
		super();
	}
	
	ScoringObject(int x, int y, int xInc, int yInc, int pV, String ID, int iW, int iH, GameObjectEnum GobjEnum){
		super(x, y, xInc, yInc, iW, iH, GobjEnum);
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
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	public String toString() {
		return this.ID + String.valueOf(xloc);
	}
}