package project;

import java.awt.image.BufferedImage;

public class Osprey extends Player{
	//BufferedImage[][] imageArray;
	
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
		//if(this.yloc + this.imageHeight >= View.frameHeight) {
			//yIncr = -35;
			//yloc = View.frameHeight - this.imageHeight;
		//}
		/*
		if(this.yloc <= 80) {
			yIncr = 0;
		}
		*/
		super.updateBounds();
	}
}