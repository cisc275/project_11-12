package project;

//revision 1
import java.util.*;
public class GameObjectStorage {
	Player p;
	Fox f;
	ArrayList<ScoringObject> scoringObjects; 
	Scoring score;
	
	GameObjectStorage(){
		this.scoringObjects = new ArrayList<ScoringObject>();
	}
	
	public Fox getFox() {
		return this.f;
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
	public void setFox(Fox f) {
		this.f = f;
	}
	public void setScoringObjects(ArrayList<ScoringObject> scoringObjects) {
		this.scoringObjects = scoringObjects;
	}
	public void setScore(Scoring s) {
		this.score = s;
	}
}