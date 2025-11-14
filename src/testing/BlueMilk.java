package testing;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

/**
* Represents a Blue Milk collectible that restores the player's health.
* The milk spawns at a random location near the top or bottom of the screen.
* If the Blue Milk sprite is available, it is drawn as an image; otherwise,
* a simple oval fallback graphic is used.
* @author andresma
*/

public class BlueMilk {
	
	/** X and Y-coordinate of the milk’s center. */
	int x, y;
	
	/** raidus of milk */
    int radius = 10;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;
    public Shape shape;  
    Random rand = new Random();
	
    /** Creates a new BlueMilk object at a random location.*/
    public BlueMilk() {
    	this.x = rand.nextInt(1500);
    	this.y = rand.nextInt(100);
    	
    	//Randomly decides top or bottom for placement
    	if (y>50) { this.y = 900 -y;}
        try {
            sprite = ImageIO.read(BlueMilk.class.getResource("BlueMilk-modified.png"));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false; // fallback to oval
        }
        updateShape();
    }
    
    /**Draws the Blue Milk on the screen.*/
    public void draw(Graphics g) {
    	if (spriteLoaded) {  
    		int drawX = x - radius; 
    	    int drawY = y - radius;
    	    int size = radius * 2;
    	    g.drawImage(sprite, drawX, drawY, size, size, null);
    	} else {
    		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    	}
    	
    }
    /**updates collision based on current position*/
    public void updateShape() {
		shape = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
	}
	
	public Shape getShape() {
        return shape;
    }
	
	/**Checks whether Luke collides with the milk. If so, Luke gains one health
    * and the milk is removed by teleporting it off-screen.*/
	public void collideWithMilk(Luke luke) {
		 double ox = luke.x - x;
		 double oy = luke.y - y;
		 double d = Math.sqrt(ox*ox + oy*oy);
		 double minDist = radius + luke.radius;
		 if (d < minDist && d != 0) {
		      	 luke.health ++;
		      	 this.x=-1000;
		     	 this.y=-1000;
		  }
	}	
	
}
