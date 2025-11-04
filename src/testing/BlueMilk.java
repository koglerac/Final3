package testing;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlueMilk {
	int x, y;
    int radius = 50;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;
    public Shape shape;   
	
    public BlueMilk(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            sprite = ImageIO.read(BlueMilk.class.getResource("BlueMilk-modified.png"));
            System.out.println("Sprite loaded: " + spriteLoaded);
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false; // fallback to oval
        }
        updateShape();
    }
    
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
    
    public void updateShape() {
		shape = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
	}
	
	public Shape getShape() {
        return shape;
    }
}
