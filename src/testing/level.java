package testing;

/**
 * Represents the current game level and provides methods to
 * advance, retrieve, and reset it.
 * @author andresma
 */

public class level {
	//current level 
	public static int level;
	
	//constructs a new level and sets initial level
	public level(int n) {
		this.level = n;
	}
	
	//increments level
	public void nextlevel() {
		level++;
		
	}
	
	//returns current level
	public int getLevel() {
		return level;
	}
	
	//sets level back to one
	public void reset() {
		this.level = 1;
	}
}
