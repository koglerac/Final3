package testing;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy {
	private BufferedImage sprite;
    private boolean spriteLoaded = false;
    Random rand = new Random();
    int x, y;
    int dx, dy;
    int health;
    int radius;
    Shape shape;
    private boolean movingLeft, movingRight, movingUp, movingDown;
    boolean alive = true;
    
    public Enemy() {
    	this.x = rand.nextInt(1500);
    	this.y = rand.nextInt(100);
    	if (y>50) { this.y = 900 -y;}
    	int randomNum = rand.nextInt(6);
    	try{
    		if (randomNum == 0 || randomNum == 1 || randomNum == 2) {
    			sprite = ImageIO.read(Enemy.class.getResource("raider.png"));
                spriteLoaded = (sprite != null);
                health = 1;
                radius = 15;
                dx = 3; dy = 3;
	    	}else if(randomNum ==4||randomNum == 3){
	    		sprite = ImageIO.read(Enemy.class.getResource("trooper.png"));
                spriteLoaded = (sprite != null);
                health = 2;
                radius=20;
                dx=2; dy=2;
	    	}else {
	    		sprite = ImageIO.read(Enemy.class.getResource("trooperCaptain.png"));
                spriteLoaded = (sprite != null);
                health = 3;
                radius=25;
                dx=1; dy=1;
	    	}
    	}catch (IOException | IllegalArgumentException ex) {
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
    public void chase(Luke luke) {
        if (x < luke.x) {x+=dx;} 
        if (x > luke.x) {x-=dx;}
        if (y < luke.y) {y+=dy;}
        if (y > luke.y) {y-=dy;}
    }
    
    public void updateShape() {
		shape = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
	}
	
	public Shape getShape() {
        return shape;
    }
	
	public void collision(House house, Luke luke, Weapon lightsaber) {
		int ogX = x;
		int ogY = y;
		this.chase(luke);
		if (movingLeft)  x -= dx;
        if (movingRight) x += dx;
        if (movingUp)    y -= dy;
        if (movingDown)  y += dy;
        x = Math.max(radius, Math.min(1500 - radius, x));
	    y = Math.max(radius, Math.min(900 - radius, y));
	    updateShape();
	    
	    if (EnemyisCollidingWithWall(house)) {
	        // revert to previous position
	        if (x>750) x=ogX+1;
	    	if (x<=750) x = ogX-1;
	        y = ogY;
	        updateShape();
	    }
	    
	    if (EnemyGettingSabered(lightsaber)) {
	    	health = health - 1;
	    	if (health >= 0) {
	    		alive = false;
	    	}
	    }
	    
	    
	}
	private boolean EnemyisCollidingWithWall(House house) {
		for (Shape wall : house.getWalls()) {
	        if (this.shape.intersects(wall.getBounds2D()))
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean EnemyGettingSabered(Weapon lightsaber) {
		if (this.shape.intersects(lightsaber.getShape().getBounds2D())) {
			return true;
		}
		return false;
	}
		
	public void setMovingLeft(boolean b)  { movingLeft = b; }
	public void setMovingRight(boolean b) { movingRight = b; }
	public void setMovingUp(boolean b)    { movingUp = b; }
	public void setMovingDown(boolean b)  { movingDown = b; }

	public void bump(ArrayList<Enemy> enemies, Enemy en) {
		for (Enemy other : enemies) {
			//ChatGPT
	         if (other != this) {
	            double ox = other.x - x;
	             double oy = other.y - y;
	             double d = Math.sqrt(ox*ox + oy*oy);
	             double minDist = radius + other.radius;
	             if (d < minDist && d != 0) {
	                 // Move both apart along the line connecting centers
	                 double overlap = 0.5 * (minDist - d);
	                 x -= overlap * (ox / d);
	                 y -= overlap * (oy / d);
	                 other.x += overlap * (ox / d);
	                 other.y += overlap * (oy / d);
	                }

		}
	}
	}
	
	public void hit() {
		
	}
	

	}

