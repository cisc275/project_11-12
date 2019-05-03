package project;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

public class Model {
	
	Player p;
	ArrayList<ScoringObject> scoringObjects = new ArrayList<>();
	Scoring score;
	int count;
	Point[] g2locations = new Point[8];
	Point[] clapperlocations = new Point[8];
	int G2Y = 300;
	int G2Y2 = 450;
	int G2X = (View.frameWidth/5);
	boolean[] g2occupancy = new boolean[8];
	Random r = new Random();
	GameObjectStorage GobjS = new GameObjectStorage();
	//initial values/positions for cr
	int CRX_I = 140;
	int CRY_I = 250;
	int CR_Y = 150;
	int CR_X = 160;
	int CRX_INCR_I = 0;
	int CRY_INCR_I = 0;
	int CR_IMH = 50;
	int CR_IMW = 50;
	
	//initial values/positions for o
	int OX_I = (View.frameWidth / 2) - 100;
	int OY_I = 100;
	int OX_INCR_I = 0;
	int OY_INCR_I = 0;
	int O_IMH = 75;
	int O_IMW = 110;
	
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
	
	//levels for both scoring objects (game1)
	int SO_LEVEL1 = 275;
	int SO_LEVEL2 = 375;
	int SO_LEVEL3 = 475;
	
	public void addGameObjectStorageToModel(GameObjectStorage GobjS) {
		this.GobjS = GobjS;
	}
	
	/**
	 * updates all of the player and scoring objects based on world and keypresses by calling the objects' 
	 * move() methods
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void updateGame() {
		if (View.getContent() == "g1") {
			this.updateGameOne();
		}
		if (View.getContent() == "g2") {
			this.updateGameTwo();
			}
		}
	
	/**
	 * Updates the state of Game 2: Clapper Rail:
	 * creates and removes scoringObjects (food and trash), updates the location of the player.
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void updateGameTwo() {
		if (count % 70 == 0) {
			for (int i=0; i < 3; i++) {
				createFoodOrTrash();
			}
		}
		count ++;
		GobjS.getPlayer().move();
		updateFoodAndTrash();
	}
	
	/**
	 * Creates a new scoringObject that is "placed" in a random vacant Point from g2locations, and updates the 
	 * respective g2occupancy to true. The method uses the scoringObject's index (same for g2locations & g2occupancy)
	 * as its ID so that this specific scoringObject's g2locations & g2occupancy can be referenced and updated later on.
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void createFoodOrTrash() {
		int rand = r.nextInt(8);
		while (g2occupancy[rand] == true) {
			rand = r.nextInt(8);
		}
		String ID = Integer.toString(rand);
		int pointValue = foodOrTrash();
		GameObjectEnum gobje;
		if(pointValue == 1) {
			gobje = GameObjectEnum.g2Food;
		}
		else {
			gobje = GameObjectEnum.g2Trash;
		}
		GobjS.getScoringObjects().add(new ScoringObject(g2locations[rand].x,g2locations[rand].y, 0, 0, pointValue, ID, 30, 30, gobje));
		g2occupancy[rand] = true;
	}
	
	
	/**
	 * Iterates through scoringObjects arraylist and removes scoringObjects whose "lifetime" is over and should be removed from game.
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void updateFoodAndTrash() {
		Iterator<ScoringObject> it = GobjS.getScoringObjects().iterator();
		while (it.hasNext()) {
			ScoringObject o = it.next();
			if (o.lifetime>o.g2_lifetime) {
				it.remove();
				g2occupancy[Integer.parseInt(o.ID)] = false;
			}
			else {
				o.lifetime++;
				//collisionG2();
			}
		}
	}
	
	/**
	 * If g2occupancy has a location, it iterates through scoringObjects arraylist and removes scoringObjects 
	 * whose location is the same as the clapper rail's location and updates the score appropriately based on 
	 * if food or trash was at that location
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void eatFoodOrTrash() {
		int loc = findClapperRail();
		if (g2occupancy[loc] != false) {
			Iterator<ScoringObject> it = GobjS.getScoringObjects().iterator();
			while (it.hasNext()) {
				ScoringObject o = it.next();
				if (Integer.parseInt(o.ID) == loc){
					score.updateScore(o);
					it.remove();
					g2occupancy[Integer.parseInt(o.ID)] = false;
					System.out.println(score.totalScore);
				}
			}
		}
		else {
			//do nothing (clapper rail is not on a spot with food or trash)
		}
	}
	
	/**
	 * Creates a new point representing the clapper rail's x and y location. Goes through the clapperlocations
	 * array to see if the clapper rail is at one of those 8 positions and returns the location.
	 * 
	 * @param none
	 * @return (int) location of clapper rail
	 * @author Anna Bortle
	 */
	public int findClapperRail() {
		int loc = -1;
		Point cr_loc = new Point(GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc());
		for (int i = 0; i < clapperlocations.length; i++) {
			if (cr_loc.equals(clapperlocations[i])) {
				return i; 
			}
		}
		return loc;
	}
	
	/**
	 * Updates the state of Game 1: Osprey:
	 * updates the scoringObjects (fish and seaweed), updates the location of the player.
	 *
	 * @param none
	 * @return none
	 * @author Hannah Bridge
	 */
	public void updateGameOne() {	
		//System.out.println("Game 1 updated");
		this.updateGameOneScoringObjects(GobjS.getScoringObjects());
		GobjS.getPlayer().move();
	}
	
	/**
	 * Updates the scoring objects for game 1: osprey:
	 * goes through the scoringObjects array list and removes fish and seaweed if they are off screen and creates
	 * new ones each time
	 * 
	 * @param (ArrayList) scoringObjects
	 * @return none
	 * @author Hannah Bridge
	 */
	public void updateGameOneScoringObjects(ArrayList<ScoringObject> scoringObjects) {
		for(int i = 0; i < scoringObjects.size(); i++) {
			scoringObjects.get(i).move();
			if(scoringObjects.get(i).ID.equals("Fish1") || scoringObjects.get(i).ID.equals("Fish2") || scoringObjects.get(i).ID.equals("Fish3")) {
				if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 1) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneFish(1));
				} 
				else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 2) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneFish(2));
				}
				else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).pointValue == 3) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneFish(3));
				}
			}
			if(scoringObjects.get(i).ID.equals("Seaweed1") || scoringObjects.get(i).ID.equals("Seaweed2") || scoringObjects.get(i).ID.equals("Seaweed3")) {
				if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == -4) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneSeaweed(1));
				} 
				else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == -3) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneSeaweed(2));
				}
				else if(this.checkIfScoringObjectIsOffScreen(scoringObjects.get(i)) && scoringObjects.get(i).xIncr == -2) {
					scoringObjects.remove(i);
					scoringObjects.add(this.createGameOneSeaweed(3));
				}
			}
		}
	}
	
	/**
	 * Checks if the scoringObject is off the screen: returns true if it is, false otherwise
	 * 
	 * @param (ScoringObject) obj
	 * @return (boolean) true/false
	 * @author Ken Chan
	 */
	public boolean checkIfScoringObjectIsOffScreen(ScoringObject obj) {
		if(obj.xloc + obj.imageWidth <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Initializes game 1 by setting new osprey player and adding scoring objects (fish, seaweed) for each level
	 * 
	 * @param none
	 * @return none
	 * @author Hannah Bridge
	 */
	public void initializeGameOne() {
		GobjS.setPlayer(new Osprey(OX_I, OY_I, OX_INCR_I, OY_INCR_I, O_IMW, O_IMH, GameObjectEnum.g1Osprey));
		
		GobjS.getScoringObjects().add(this.createGameOneFish(1));
		GobjS.getScoringObjects().add(this.createGameOneFish(2));
		GobjS.getScoringObjects().add(this.createGameOneFish(3));	
		GobjS.getScoringObjects().add(this.createGameOneSeaweed(1));
		GobjS.getScoringObjects().add(this.createGameOneSeaweed(2));
		GobjS.getScoringObjects().add(this.createGameOneSeaweed(3));
		
	}
	
	/**
	 * Initializes game 2 by creating the 8 locations for the clapper rail (4 on the top row, 4 on the bottom row)
	 * and setting new clapper rail player
	 * 
	 * @param none
	 * @return none
	 * @author Ken Chan
	 */
	public void initializeGameTwo() {
		for (int i = 0; i < 8; i++) {
			g2occupancy[i] = false;
			if (i < 4) {
				clapperlocations[i] = new Point(CRX_I+CR_X*(i), CRY_I);	
				g2locations[i] = new Point(G2X*(i+1), G2Y);
			}
			else {
				clapperlocations[i] = new Point(CRX_I+CR_X*(i-4), CRY_I+CR_Y);
				g2locations[i] = new Point(G2X*(i-3), G2Y2);
			}
		}	
		
		GobjS.setPlayer(new ClapperRail(CRX_I, CRY_I, CRX_INCR_I, CRY_INCR_I, CR_IMW, CR_IMH, GameObjectEnum.g2ClapperRail));
		score = new Scoring();
	}

	/**
	 * Creates new fish scoring objects for levels 1, 2, 3
	 * 
	 * @param (int) fishLevel
	 * @return null
	 * @author Hannah Bridge
	 */
	public ScoringObject createGameOneFish(int fishLevel) {
		if(fishLevel == 1) {
			return (new ScoringObject(FX_I, SO_LEVEL1, F1_SPEED, FY_INCR_I, F1_PV, "Fish1", F1_IMW, F_IMH, GameObjectEnum.g1Fish1));
		} if(fishLevel == 2) {
			return (new ScoringObject(FX_I, SO_LEVEL2, F2_SPEED, FY_INCR_I, F2_PV, "Fish2", F2_IMW, F_IMH, GameObjectEnum.g1Fish2));
		} if(fishLevel == 3) {
			return (new ScoringObject(FX_I, SO_LEVEL3, F3_SPEED, FY_INCR_I, F3_PV, "Fish3", F3_IMW, F_IMH, GameObjectEnum.g1Fish3));
		}
		return null;
	}
	
	/**
	 * Creates new seaweed scoring objects for levels 1, 2, 3
	 * 
	 * @param (int) seaweedLevel
	 * @return null
	 * @author Hannah Bridge
	 */
	public ScoringObject createGameOneSeaweed(int seaweedLevel) {
		if(seaweedLevel == 1) {
			return (new ScoringObject(SWX_I, SO_LEVEL1, SW1_SPEED, SWY_INCR_I, SW_PV, "Seaweed1", SW_IMW, SW_IMH, GameObjectEnum.g1Seaweed));
		}
		if(seaweedLevel == 2) {
			return (new ScoringObject(SWX_I, SO_LEVEL2, SW2_SPEED, SWY_INCR_I, SW_PV, "Seaweed2", SW_IMW, SW_IMH, GameObjectEnum.g1Seaweed));
		} 
		if(seaweedLevel == 3) {
			return (new ScoringObject(SWX_I, SO_LEVEL3, SW3_SPEED, SWY_INCR_I, SW_PV, "Seaweed3", SW_IMW, SW_IMH, GameObjectEnum.g1Seaweed));
		}
		return null;
	}
	
	/**
	 * Returns either a -1 or 1.
	 * Return value used to assign a point value to a scoringObject in Game 2 (Clapper Rail).
	 * A value of -1 denotes trash, and 1 denotes food.
	 * 
	 * @param none
	 * @return integer: random -1 or 1
	 * @author Anna Bortle
	 */
	public int foodOrTrash() {
		int i = r.nextInt();
		if (i%2 == 0) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	public GameObjectStorage getGobjS() {
		return this.GobjS;
	}

	/**
	 * For the Clapper Rail game, collisionG2 creates new rectangle objects and get their bounds. It creates a rectangle
	 * for the player and 3 more rectangles for the scoring objects. If the bounds intersect with one another,
	 * then a collision is detected.
	 * 
	 * 
	 * @param none
	 * @return none
	 * @author Brendan Azueta
	 */
	
	public boolean collisionG2() {
		Rectangle CR = GobjS.getPlayer().getBounds();
		Rectangle o1 = GobjS.getScoringObjects().get(0).getBounds();
		Rectangle o2 = GobjS.getScoringObjects().get(1).getBounds();
		Rectangle o3 = GobjS.getScoringObjects().get(2).getBounds();
		
		if(CR.intersects(o1) || CR.intersects(o2) || CR.intersects(o3)) {
			System.out.println("Collision detected");
			foodOrTrash();
			return true;
			
		} else {
			return false;
		}
	}
	
	
}



