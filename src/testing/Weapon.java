package testing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Weapon {
	private final int damage = 1;
	private boolean spriteLoaded = false;
	private BufferedImage sprite;
	int x;
	int y;
	int width = 10;
	int height = 25;
	int swingspeed = 2;
	
	public Weapon(Luke luke) {
		this.x = luke.x;
		this.y = luke.y;
		try {
            sprite = ImageIO.read(Luke.class.getResource("Blue_Lightsaber.png"));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false;
        }
	}
	
	public void draw(Graphics g) {
		if (spriteLoaded) { 
			g.drawImage(sprite, x, y, width, height, null);
		}
	}
	
}