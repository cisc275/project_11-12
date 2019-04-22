package project;

import java.awt.image.BufferedImage;

public class ClapperRail implements Player{
	
	int xloc;
	int yloc;
	int xIncr;
	int yIncr;
	int imageWidth, imageHeight;
	BufferedImage[][] imageArray;
	
	ClapperRail(int xloc, int yloc, int xIncr, int yIncr, int iW, int iH){
		this.xloc = xloc;
		this.yloc = yloc;
		this.xIncr = xIncr;
		this.yIncr = yIncr;
		this.imageWidth = iW;
		this.imageHeight = iH;
	}
	
	
	/**
	 * updates x location and y location for clapper rail object
	 * @param none
	 * @return none
	 * @author Hannah Bridge
	 */
	public void move() {
		//System.out.println("clapper rail move method called.");
	
		xloc += xIncr;
		xIncr = 0;
		yloc += yIncr;
		yIncr = 0;
	}

	@Override
	public int getxLoc() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		this.yIncr = yI;
	}
	
}