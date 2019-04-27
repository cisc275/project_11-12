package project;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Model {
	
	Player p;
	ArrayList<ScoringObject> scoringObjects = new ArrayList<>();
	Scoring score;
	int count;
	Point[] g2locations = new Point[8];
	int g2y = 300;
	int g2y2 = 450;
	int g2x = (View.frameWidth/5);
	boolean[] g2occupancy = new boolean[8];
	
	//initial values/positions for cr
	int crx_i = 140;
	int cry_i = 250;
	int crxIncr_i = 0;
	int cryIncr_i = 0;
	int cr_imh = 50;
	int cr_imw = 50;
	
	//initial values/positions for o
	int ox_i = (View.frameWidth / 2) - 100;
	int oy_i = 100;
	int oxIncr_i = 0;
	int oyIncr_i = 0;
	int o_imh = 35;
	int o_imw = 100;
	
	int flevel1 = 275;
	int flevel2 = 375;
	int flevel3 = 475;
	int f1speed = -5;
	int f2speed = -4;
	int f3speed = -3;
	
	/**
	 * updates all of the player and scoring objects based on world and keypresses by calling the objects' move() methods
	 * @param none
	 * @return none
	 */
	public void updateGame() {
		if (View.getContent() == "g1") {
			this.updateGameOne();
		}
		if (View.getContent() == "g2") {
			this.updateGameTwo();
			}
		}
	
	
	public void updateGameTwo() {
		count ++;
		p.move();
		if (count > ScoringObject.g2_lifetime) {
			View.randflag = true;
			count = 0;
		}
	}
	public void updateGameOne() {
		if(scoringObjects.isEmpty()) {
			this.createGameOneFish(1);
			this.createGameOneFish(2);
			this.createGameOneFish(3);
		}
		else {
			for(int i = 0; i < scoringObjects.size(); i++) {
				scoringObjects.get(i).move();
				if(this.checkIfFishIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 1) {
					scoringObjects.remove(i);
					this.createGameOneFish(1);
				} else if(this.checkIfFishIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 2) {
					scoringObjects.remove(i);
					this.createGameOneFish(2);
				}
				else if(this.checkIfFishIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 3) {
					scoringObjects.remove(i);
					this.createGameOneFish(3);
				}
			}
		}
		p.move();
	}
	public boolean checkIfFishIsOffScreen(ScoringObject obj) {
		if(obj.xloc + obj.imageWidth <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public void runGameOne() {
		//System.out.println("created osprey");
		p = new Osprey(ox_i, oy_i, oxIncr_i, oyIncr_i, o_imw, o_imh);
		
	}
	
	public void runGameTwo() {
		//System.out.println("create clapper rail");
		p = new ClapperRail(crx_i, cry_i, crxIncr_i, cryIncr_i, cr_imw, cr_imh);
		
		for (int i = 0; i < 8; i++) {
			g2occupancy[i] = false;
			if (i < 4) {
				g2locations[i] = new Point((View.frameWidth/3)-50 + g2x*i, g2y);
			}
			else {
				g2locations[i] = new Point((View.frameWidth/3) + g2x*(i-3), g2y2);
			}
		}		
	}
	
	public void createGameOneFish(int fishLevel) {
		//System.out.println("Created Fish");
		if(fishLevel == 1) {
			scoringObjects.add(new ScoringObject((View.frameWidth - 100), flevel1, f1speed, 0, 1, "Fish1", 50, 50));
		} if(fishLevel == 2) {
			scoringObjects.add(new ScoringObject((View.frameWidth - 100), flevel2, f2speed, 0, 2, "Fish2", 50, 50));
		} if(fishLevel == 3) {
			scoringObjects.add(new ScoringObject((View.frameWidth - 100), flevel3, f3speed, 0, 3, "Fish3", 50, 50));
		}
	}	
}

