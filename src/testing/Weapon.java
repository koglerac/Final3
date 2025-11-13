package testing;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Weapon {
	private int damage;
	private boolean spriteLoaded = false;
	private BufferedImage sprite;
	int x;
	int y;
	int width = 15;
	int height = 30;
	int swingspeed = 2;
	public Shape shape;
	double saberangle = 0;
	boolean spinning = false;
	int spinTimer;
	int spinCooldown = 0;
	
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
		if (spinning) {
			damage = 2;
			shape = new Ellipse2D.Double(luke.x - luke.radius * 2, luke.y - luke.radius * 2, luke.radius * 4, luke.radius * 4);
//			System.out.println("Spinning");
		}
		else {
			damage = 1;
			shape = new Rectangle2D.Double(luke.x - width / 2, luke.y - luke.radius*2 - height / 2, width, height);
			AffineTransform rotate = AffineTransform.getRotateInstance(saberangle, luke.x, luke.y);
			shape = rotate.createTransformedShape(shape);
		}
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void Spin() {
		if (!spinning && spinCooldown <= 0) {
		spinTimer = 50;
		spinning = true;
		}
	}
	
	public void tick(Luke luke) {
        if (spinning) {
            spinTimer--;
            if (spinTimer <= 0) {
                spinning = false;
                spinCooldown = 100;
            }
        }
        else if (spinCooldown > 0) {
        	spinCooldown--;
        }
        updateShape(luke);
    }
	
	public boolean canSpin() {
		return !spinning && spinCooldown == 0;
	}
}