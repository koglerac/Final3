package testing;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Creates sand pieces for sandstorm mode.
 * 
 * @author koglerac, andresma
 */

public class Sandies {
	public static ArrayList<Sandy> sandies;
	
	//creates arraylist of sand pieces
	public Sandies(int num) {
		sandies = new ArrayList<>();
		//this.home = home;
		for (int i = 0; i < num; i++) {
			Sandy sandy = new Sandy();
			sandies.add(sandy);
		}
	}
	
	//draws sand array
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		for (Sandy sa : sandies) {
			sa.draw(g2);
		}
	}

	//resets sand particles' location
	public void reset() {
		for (Sandy sa : sandies) {
			sa.reset();
		}
	}


}
