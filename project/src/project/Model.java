package project;



public class Model {
	Player p;
	ScoringObject[] s;
	Scoring score;
	
	public Model() {
		if (View.getContent() == "g1") {
			p = new Osprey(100,100,0,0);
		}
		if (View.getContent() == "g2") {
			p = new ClapperRail(100,100,0,0);
		}
	}
	/**
	 * updates all of the player and scoring objects based on world and keypresses by calling the objects' move() methods
	 * @param none
	 * @return none
	 */
	public void updateGame() {
		
	}

}