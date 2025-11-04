package testing;

import java.awt.Graphics;

public class Weapon {
	private final int damage = 1;
	private boolean spriteLoaded;
	int x;
	int y;
	int width = 10;
	int height = 25;
	int swingspeed = 2;
	
	public Weapon(Luke luke) {
		this.x = luke.x;
		this.y = luke.y;
		
	}
	
	public void draw(Graphics g) {
		if (spriteLoaded) {  
			g.drawRect(this.x, this.y, width, height);
		}
	}
}