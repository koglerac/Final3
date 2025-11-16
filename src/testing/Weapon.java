package testing;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Creates the weapon (lightsaber) and handles its movement
 * 
 * @author bettenc1
 */
public class Weapon {
	private int damage;
	private boolean spriteLoaded = false;
	private BufferedImage sprite;
	int x;
	int y;
	int width = 20;
	int height = 40;
	int swingspeed = 2;
	public Shape shape;
	double saberangle = 0;
	boolean spinning = false;
	int spinTimer;
	int spinCooldown = 0;
	// Sets the location of the lightsaber and loads its image.
	public Weapon(Luke luke) {
		this.x = luke.x - width / 2;
		this.y = luke.y - 2 * luke.radius - height / 2;
		try {
            sprite = ImageIO.read(Luke.class.getResource("Blue Lightsaber.png"));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false;
        }
		updateShape(luke);
	}
	//Updates the position of the lightsaber, as well as the rotation angle needed for the rotating image and hitbox
	public void updatePos(Luke luke, boolean left, boolean right, boolean up, boolean down) {
		this.x = luke.x - width / 2;
		this.y = luke.y - 2 * luke.radius - height / 2;
		if (left) saberangle = -Math.PI / 2;
		if (right) saberangle = Math.PI / 2;
		if (up) saberangle = 0;	
		if (down) saberangle = Math.PI;
			
		updateShape(luke);
	}
	//Draws the lightsaber on the screen.
	public void draw(Graphics g) {
		if (spriteLoaded) { 
			g.drawImage(sprite, x, y, width, height, null);
		}
	}
	//Updates the shape of the lightsaber hitbox, as well as the damage value depending on if a spin attack is used.
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
	//Returns the lightsaber shape.
	public Shape getShape() {
		return shape;
	}
	//Returns the lightsaber damage
	public int getDamage() {
		return damage;
	}
	//If Luke is not using a spin attack and the attack is off cooldown, start a spin attack.
	public void Spin() {
		if (!spinning && spinCooldown <= 0) {
		spinTimer = 50;
		spinning = true;
		}
	}
	//Ticks down the timer for the duration of Luke's spin attack, as well as starting and decrementing the spin cooldown
	public void tick(Luke luke) {
        if (spinning) {
            spinTimer--;
            if (spinTimer <= 0) {
                spinning = false;
                spinCooldown = 500;
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