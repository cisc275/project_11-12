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

	public int getXincr() {
		return xincr;
	}

	public void setXincr(int xincr) {
		this.xincr = xincr;
	}

	public int getYincr() {
		return yincr;
	}

	public void setYincr(int yincr) {
		this.yincr = yincr;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	

}