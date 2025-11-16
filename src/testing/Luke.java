package testing;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Creates player Luke with dimensions and location.
 * 
 * @author andresma, koglerac
 */

public class Luke {
	int x, y;
    int dx = 0, dy = 0;
    static int health = 3;
    int radius = 15;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;
    public Shape shape;   
	
    //describes Luke's position
    public Luke(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            sprite = ImageIO.read(Luke.class.getResource("Luke.220.png"));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false; // fallback to oval
        }
        updateShape();
    }
    
    //draws Luke
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
    
    //create sprite shape
	public void updateShape() {
		shape = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
	}
	
	//returns sprite shape
	public Shape getShape() {
        return shape;
    }

	//resets Luke's position
	public void reset(int x, int y, HomeComponent home) {
		    this.x = x;
		    this.y = y;
		    this.dx = 0;
		    this.dy = 0;
		    home.movingLeft = false;
		    home.movingRight = false;
		    home.movingUp = false;
		    home.movingDown = false;
		    this.health = 3;
		
	}
    
}
