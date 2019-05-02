package project;

import java.awt.image.BufferedImage;

public class Osprey extends Player{
	
	//int xloc, yloc, xIncr, yIncr;
	//int imageWidth, imageHeight;
	BufferedImage[][] imageArray;
	
	Osprey(int x, int y, int xInc, int yInc, int iW, int iH, GameObjectEnum GobjEnum) {
		super(x, y, xInc, yInc, iW, iH, GobjEnum);
	}
	
	
	/**
	 * updates location(xloc and yloc) for osprey object
	 * @param none
	 * @return none
	 * @author Ken Chan
	 */
	public void move() {
		//xloc += xIncr;
		yloc += yIncr;
		if (yloc < 0) {
			yloc = 0;
			
		}
		if(this.yloc + this.imageHeight >= View.frameHeight) {
			yIncr = -50;
			yloc = View.frameHeight - this.imageHeight;
		}
		if(this.yloc <= 100) {
			yIncr = 0;
		}
	}

/*
	@Override
	public int getxLoc() {
		return this.xloc;
	}
	@Override
	public int getyLoc() {
		// TODO Auto-generated method stub
		return this.yloc;
	}
	@Override
	public int getxIncr() {
		// TODO Auto-generated method stub
		return this.xIncr;
	}
	@Override
	public int getyIncr() {
		// TODO Auto-generated method stub
		return this.yIncr;
	}
	@Override
	public int getimageWidth() {
		// TODO Auto-generated method stub
		return this.imageWidth;
	}
	@Override
	public int getimageHeight() {
		// TODO Auto-generated method stub
		return this.imageHeight;
	}
	@Override
	public void setxLoc(int x) {
		// TODO Auto-generated method stub
		this.xloc = x;
	}
	@Override
	public void setyLoc(int y) {
		// TODO Auto-generated method stub
		this.xloc = y;
	}
	@Override
	public void setxIncr(int xI) {
		// TODO Auto-generated method stub
		this.xIncr = xI;
	}
	@Override
	public void setyIncr(int yI) {
		System.out.println("yIncr set");
		// TODO Auto-generated method stub
		this.yIncr = yI;
	}
	*/
}