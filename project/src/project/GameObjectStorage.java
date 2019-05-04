package project;

//revision 1
import java.util.*;
public class GameObjectStorage {
	Player p;
	ArrayList<ScoringObject> scoringObjects; 
	Scoring score;
	
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
	public void setScore(Scoring s) {
		this.score = s;
	}
}