package testing;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Milks {
private ArrayList<BlueMilk> milks;
	
	public Milks(int num) {
		milks = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			BlueMilk milky = new BlueMilk();
			milks.add(milky);
		}
	}
	

	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		for (BlueMilk m : milks) {
			m.draw(g2);
		}
	}

	
	public ArrayList<BlueMilk> getMilks() {
		return milks;
	}
}
