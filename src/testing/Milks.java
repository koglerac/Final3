package testing;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *  Handles a collection of BlueMilk objects in the game.
 *  @author andresma
 */
public class Milks {
	//list of BlueMilk objects
private ArrayList<BlueMilk> milks;
	
	//constructs milks with specified number of milks
	public Milks(int num) {
		milks = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			BlueMilk milky = new BlueMilk();
			milks.add(milky);
		}
	}
	
	//draw all milks
	public void draw(Graphics2D g2) {
		for (BlueMilk m : milks) {
			m.draw(g2);
		}
	}

	//return list of milks
	public ArrayList<BlueMilk> getMilks() {
		return milks;
	}


	//Handle collision of milks with Luke
	public void collideWithMilk(Luke luke) {
		for (BlueMilk m : milks) {
			m.collideWithMilk(luke);
		}
	}
}
