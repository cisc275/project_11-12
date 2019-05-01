package project;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Model {
	
	Player p;
	ArrayList<ScoringObject> scoringObjects = new ArrayList<>();
	Scoring score;
	int count;
	Point[] g2locations = new Point[8];
	int G2Y = 300;
	int G2Y2 = 450;
	int G2X = (View.frameWidth/5);
	boolean[] g2occupancy = new boolean[8];
	Random r = new Random();
	
	//initial values/positions for cr
	int CRX_I = 140;
	int CRY_I = 250;
	int CRX_INCR_I = 0;
	int CRY_INCR_I = 0;
	int CR_IMH = 50;
	int CR_IMW = 50;
	
	//initial values/positions for o
	int OX_I = (View.frameWidth / 2) - 100;
	int OY_I = 100;
	int OX_INCR_I = 0;
	int OY_INCR_I = 0;
	int O_IMH = 35;
	int O_IMW = 100;
	
	//initial values/positions for scoring object (fish)
	int FX_I = View.frameWidth - 100;
	int FY_INCR_I = 0;
	int F1_SPEED = -5;
	int F2_SPEED = -4;
	int F3_SPEED = -3;
	int F1_PV = 1;
	int F2_PV = 2;
	int F3_PV = 3;
	int F_IMH = 50;
	int F1_IMW = 50;
	int F2_IMW = 75;
	int F3_IMW = 100;
	
	//initial values/positions for scoring object (seaweed)
	int SWX_I = View.frameWidth;
	int SWY_INCR_I = 0;
	int SW1_SPEED = -4;
	int SW2_SPEED = -3;
	int SW3_SPEED = -2;
	int SW_PV = -1;
	int SW_IMH = 50;
	int SW_IMW = 50;
	
	//levels for both scoring objects
	int SO_LEVEL1 = 275;
	int SO_LEVEL2 = 375;
	int SO_LEVEL3 = 475;
	
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
		
		if (count % 70 == 0) {
			for (int i=0; i < 3; i++) {
				int rand = r.nextInt(8);
				while (g2occupancy[rand] == true) {
					rand = r.nextInt(8);
				}
				String ID = Integer.toString(rand);
				scoringObjects.add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, 0, 0, foodOrTrash(), ID, 30, 30));
				g2occupancy[rand] = true;
			}
		}
		
		count ++;
		p.move();
		
		Iterator<ScoringObject> it = scoringObjects.iterator();
		while (it.hasNext()) {
			ScoringObject o = it.next();
			if (o.lifetime>o.g2_lifetime) {
				it.remove();
				g2occupancy[Integer.parseInt(o.ID)] = false;
			}
			else {
				o.lifetime++;
			}
		}
	}
	public void updateGameOne() {
		if(scoringObjects.isEmpty()) {
			this.createGameOneFish(1);
			this.createGameOneFish(2);
			this.createGameOneFish(3);	
			
			createGameOneSeaweed(1);
			createGameOneSeaweed(2);
			createGameOneSeaweed(3);
		}
		else {
			for(int i = 0; i < scoringObjects.size(); i++) {
				scoringObjects.get(i).move();
				if(scoringObjects.get(i).ID.equals("Fish")) {
					if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 1) {
						scoringObjects.remove(i);
						this.createGameOneFish(1);
				
					} 
					else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 2) {
						scoringObjects.remove(i);
						this.createGameOneFish(2);
					}
					else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 3) {
						scoringObjects.remove(i);
						this.createGameOneFish(3);
					}
				}
				if(scoringObjects.get(i).ID.equals("Seaweed")) {
					if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == -4) {
						scoringObjects.remove(i);
						this.createGameOneSeaweed(1);
					} 
					else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == -3) {
						scoringObjects.remove(i);
						this.createGameOneSeaweed(2);
					}
					else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == -2) {
						scoringObjects.remove(i);
						this.createGameOneSeaweed(3);
					}
				}
			}
		}
		p.move();
	}
	public boolean checkIfScoringObjectIsOffScreen(ScoringObject obj) {
		if(obj.xloc + obj.imageWidth <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void runGameOne() {
		//System.out.println("created osprey");
		p = new Osprey(OX_I, OY_I, OX_INCR_I, OY_INCR_I, O_IMW, O_IMH);
		
	}
	
	public void runGameTwo() {
		//System.out.println("create clapper rail");
		p = new ClapperRail(CRX_I, CRY_I, CRX_INCR_I, CRY_INCR_I, CR_IMW, CR_IMH);
		
		for (int i = 0; i < 8; i++) {
			g2occupancy[i] = false;
			if (i < 4) {
				g2locations[i] = new Point(G2X*(i+1), G2Y);
			}
			else {
				g2locations[i] = new Point(G2X*(i-3), G2Y2);
			}
		}		
	}

	
	public void createGameOneFish(int fishLevel) {
		//System.out.println("Created Fish");
		if(fishLevel == 1) {
			scoringObjects.add(new ScoringObject(FX_I, SO_LEVEL1, F1_SPEED, FY_INCR_I, F1_PV, "Fish", F1_IMW, F_IMH));
		} if(fishLevel == 2) {
			scoringObjects.add(new ScoringObject(FX_I, SO_LEVEL2, F2_SPEED, FY_INCR_I, F2_PV, "Fish", F2_IMW, F_IMH));
		} if(fishLevel == 3) {
			scoringObjects.add(new ScoringObject(FX_I, SO_LEVEL3, F3_SPEED, FY_INCR_I, F3_PV, "Fish", F3_IMW, F_IMH));
		}
	}

	public void createGameOneSeaweed(int level) {
		if(level == 1) {
			scoringObjects.add(new ScoringObject(SWX_I, SO_LEVEL1, SW1_SPEED, SWY_INCR_I, SW_PV, "Seaweed", SW_IMW, SW_IMH));
		}
		if(level == 2) {
			scoringObjects.add(new ScoringObject(SWX_I, SO_LEVEL2, SW2_SPEED, SWY_INCR_I, SW_PV, "Seaweed", SW_IMW, SW_IMH));
		} 
		if(level == 3) {
			scoringObjects.add(new ScoringObject(SWX_I, SO_LEVEL3, SW3_SPEED, SWY_INCR_I, SW_PV, "Seaweed", SW_IMW, SW_IMH));
		}
	}
	

	
	public int foodOrTrash() {
		int i = r.nextInt();
		if (i%2 == 0) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
