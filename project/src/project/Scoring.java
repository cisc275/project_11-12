package project;
import java.util.*;
public class Scoring {
	int totalScore;
	ArrayList<ScoringObject> ScoringTable = new ArrayList<>();
	/**
	 * Adds the given ScoringObject to the ScoringTable and returns true if it was added and false if it wasn't
	 * 
	 * @param scoringobject the scoringobject the method adds to the ScoringTable
	 * @return true or false depending on if the ScoringObject was added to the ScoringTable
	 */
	public boolean updateScore(ScoringObject scoringobject) {
		if(ScoringTable.add(scoringobject)) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Gets the amount of ScoringObjects with the given ID in ScoringTable
	 * 
	 * @param ID the ID the method is trying to find the total amount of in ScoringTable
	 * @return the amount of ScoringObjects with the given ID in ScoringTable
	 */
	public int amountContained(String ID) {
		int totalAmountContained = 0;
		for(ScoringObject o: this.ScoringTable) {
			if (o.ID.equals(ID)) {
				totalAmountContained++;
			}
		}
		return totalAmountContained;
	}
}