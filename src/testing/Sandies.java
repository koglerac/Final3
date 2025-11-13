package testing;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Sandies {
	public static ArrayList<Sandy> sandies;
	

	public Sandies(int num) {
		sandies = new ArrayList<>();
		//this.home = home;
		for (int i = 0; i < num; i++) {
			Sandy sandy = new Sandy();
			sandies.add(sandy);
		}
	}
	
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		for (Sandy sa : sandies) {
			sa.draw(g2);
		}
	}

	public void reset() {
		for (Sandy sa : sandies) {
			sa.reset();
		}
	}


}
