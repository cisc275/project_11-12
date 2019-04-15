package project;

import java.awt.image.BufferedImage;
import java.util.*;

public class Fox{
	int xloc;
	int yloc;
	int xincr;
	int yincr;
	ArrayList<Question> questions= new ArrayList<>();
	BufferedImage[][] imageArray;
	
	Fox(int xloc,int yloc,int xincr,int yincr){
		this.xloc =xloc;
		this.yloc = yloc;
		this.xincr = xincr;
		this.yincr = yincr;
		
	}
	
	/**
	 * updates x and y locations based on the x and y increment
	 * @param none
	 * @return none
	 * @author Celeste Lemus
	 */
	public void moveEnter() {
		xloc+=xincr;
		yloc += yincr;
		
		
	}
	
	/**
	 * updates x and y locations based on the negative x and y increment
	 * @param none
	 * @return none
	 * @author Celeste Lemus
	 */
	public void moveExit() {
		xloc+=-xincr;
		yloc +=-yincr;
		
	}

}