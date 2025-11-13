package testing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

public class Sandy {
	public int x,y, radius;
	private boolean spriteLoaded = false;
	private BufferedImage sprite;
	Random rand = new Random();
	
	public Sandy() {
		this.x=rand.nextInt(2000);
		this.y=rand.nextInt(1200);
		this.radius = 10;
		try {
			sprite = ImageIO.read(Sandy.class.getResource("sandstorm_display-modified.png"));
			spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
        	spriteLoaded = false;
        }
				
	}
	
	 public void draw(Graphics g) {
		
			if (spriteLoaded) {  
	    		int drawX = x - radius; 
	    	    int drawY = y - radius;
	    	    int size = radius * 2;
	    	    g.drawImage(sprite, drawX, drawY, size, size, null);
	    	} else {
	    		g.setColor(Color.black);
	    		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	    	
	    
	 }}

	 public void reset() {
		 this.x=rand.nextInt(1500);
		this.y=rand.nextInt(900);
	 }

}
