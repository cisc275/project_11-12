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
	boolean[] g2occupancy = new boolean[8];
	
	
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
		if (count > 65) {
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
		p = new Osprey((View.frameWidth / 2) - 100,100,0,0,100,35);
		
	}
	
	public void runGameTwo() {
		//System.out.println("create clapper rail");
		p = new ClapperRail(140,250,0,0,50,50);
		for (int i = 1; i < 9; i++) {
			g2occupancy[i-1] = false;
			if (i < 5) {
				g2locations[i-1] = new Point(140*i + 10, 300);
			}
			else {
				g2locations[i-1] = new Point(140*(i-4) + 10, 450);
			}
		}		
	}
	
	public void createGameOneFish(int fishLevel) {
		//System.out.println("Created Fish");
		if(fishLevel == 1) {
			scoringObjects.add(new ScoringObject((View.frameWidth - 100), 275, -5, 0, 1, "Fish1", 50, 50));
		} if(fishLevel == 2) {
			scoringObjects.add(new ScoringObject((View.frameWidth - 100), 375, -4, 0, 2, "Fish2", 50, 50));
		} if(fishLevel == 3) {
			scoringObjects.add(new ScoringObject((View.frameWidth - 100), 475, -3, 0, 3, "Fish3", 50, 50));
		}
	}	
}

