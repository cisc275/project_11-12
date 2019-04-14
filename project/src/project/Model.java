package project;

import java.awt.Point;

public class Model {
	static ClapperRail cr;
	Osprey o;
	ScoringObject[] s;
	Scoring score;
	
	public static Point[] g2locations;
	public static boolean[] g2occupancy;
	
	public Model() {
		//
		Scoring scoring = new Scoring();
		
		o = new Osprey(100,100,0,0);
		
		cr = new ClapperRail(160,200,0,0);
		/*
		for (int i = 0; i < 8; i++) {
			g2occupancy[i] = false;
			if (i < 4) {
				g2locations[i] = new Point(130*i, 400);
			}
			else {
				g2locations[i] = new Point(130*i, 500);
			}
		}
		*/
	}
	
	/**
	 * updates all of the player and scoring objects based on world and keypresses by calling the objects' move() methods
	 * @param none
	 * @return none
	 */
	public static void updateGame() {
		cr.move();
	}

}