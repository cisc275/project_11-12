package project;

import java.util.*;
public class GameObjectStorage {
	Player p;
	ArrayList<ScoringObject> scoringObjects; 
	
	GameObjectStorage(){
		this.scoringObjects = new ArrayList<ScoringObject>();
	}
	
	public Player getPlayer() {
		return this.p;
	}
	public ArrayList<ScoringObject> getScoringObjects(){
		return this.scoringObjects;
	}
	public void setPlayer(Player p) {
		this.p = p;
	}
	public void setScoringObjects(ArrayList<ScoringObject> scoringObjects) {
		this.scoringObjects = scoringObjects;
	}
	
}
