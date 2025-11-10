package testing;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Weapon {
	private final int damage = 1;
	private boolean spriteLoaded = false;
	private BufferedImage sprite;
	int x;
	int y;
	int width = 15;
	int height = 30;
	int swingspeed = 2;
	public Shape shape;
	
	public Weapon(Luke luke) {
		this.x = luke.x;
		this.y = luke.y;
		try {
            sprite = ImageIO.read(Luke.class.getResource("Blue_Lightsaber.png"));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false;
        }
		updateShape();
	}
	public void updatePos(Luke luke, boolean left, boolean right, boolean up, boolean down) {
		if (left) {
			this.x = luke.x - luke.radius - width;
			this.y = luke.y - height / 2;
		}
		if (right) {
			this.x = luke.x + luke.radius;
			this.y = luke.y - height / 2;
		}
		if (up) {
			this.x = luke.x - width / 2;
			this.y = luke.y - 2 * luke.radius - height / 2;
		}
		if (down) {
			this.x = luke.x - width / 2;
			this.y = luke.y + 2 * luke.radius - height / 2;
		}
		else
			System.out.println("Not up");
		updateShape();
	}
	public void draw(Graphics g) {
		if (spriteLoaded) { 
			g.drawImage(sprite, x, y, width, height, null);
		}
	}
	
	public void updateShape() {
		shape = new Rectangle2D.Double(x, y, width, height);
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public int getDamage() {
		return damage;
	}
	
}