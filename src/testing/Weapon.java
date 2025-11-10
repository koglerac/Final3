package testing;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
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
	double saberangle = 0;
	
	public Weapon(Luke luke) {
		this.x = luke.x - width / 2;
		this.y = luke.y - 2 * luke.radius - height / 2;
		try {
            sprite = ImageIO.read(Luke.class.getResource("Blue_Lightsaber.png"));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false;
        }
		updateShape(luke);
	}
	public void updatePos(Luke luke, boolean left, boolean right, boolean up, boolean down) {
		this.x = luke.x - width / 2;
		this.y = luke.y - 2 * luke.radius - height / 2;
		if (left) saberangle = -Math.PI / 2;
		if (right) saberangle = Math.PI / 2;
		if (up) saberangle = 0;	
		if (down) saberangle = Math.PI;
			
		updateShape(luke);
	}
	public void draw(Graphics g) {
		if (spriteLoaded) { 
			g.drawImage(sprite, x, y, width, height, null);
		}
	}
	
	private void updateShape(Luke luke) {
		shape = new Rectangle2D.Double(luke.x - width / 2, luke.y - luke.radius*2 - height / 2, width, height);
		AffineTransform rotate = AffineTransform.getRotateInstance(saberangle, luke.x, luke.y);
		shape = rotate.createTransformedShape(shape);
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public int getDamage() {
		return damage;
	}
	
}