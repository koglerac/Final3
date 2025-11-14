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

/**
 * Represents an enemy that chases Luke, collides with walls, reacts to being
 * hit by the lightsaber, and interacts with other enemies.
 * 
 * @author andresma
 */
public class Enemy {
	private BufferedImage sprite;
    private boolean spriteLoaded = false;
    Random rand = new Random();
    
    /**
     * Enemy's individual specifications
     */
    int x, y;
    int dx, dy;
    int health;
    int radius;
    Shape shape;
    
    /**
     * Enemy directional flags
     */
    private boolean movingLeft, movingRight, movingUp, movingDown;
    boolean alive = true;
    boolean invincible = false;
    public int itimer = 0;
    
    // reference to main component
    private HomeComponent home; 

    
    /**
     * Creates a new enemy at a random location and initializes its type,
     * sprite, speed, radius, and health.
     */
    public Enemy() {
    	this.x = rand.nextInt(1500);
    	this.home = home;
    	
    	//spawn near top or bottom
    	this.y = rand.nextInt(100);
    	if (y>50) { this.y = 900 -y;}
    	int randomNum = rand.nextInt(6);
    	try{
    		if (randomNum == 0 || randomNum == 1 || randomNum == 2) {
    			//Raider, fast but weak
    			sprite = ImageIO.read(Enemy.class.getResource("raider.png"));
                spriteLoaded = (sprite != null);
                health = 1;
                radius = 15;
                dx = 3; dy = 3;
	    	}else if(randomNum ==4||randomNum == 3){
	    		//Trooper, medium speed, medium health
	    		sprite = ImageIO.read(Enemy.class.getResource("trooper.png"));
                spriteLoaded = (sprite != null);
                health = 2;
                radius=20;
                dx=2; dy=2;
	    	}else {
	    		//Captain, slow, but high health
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
    
    /**
     * Draws enemy on screen
     */
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
    
    /**
     *  Moves the enemy one step closer to Luke
     */
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
	
	/** Handles all enemy interactions: chasing Luke, wall collisions,
     * reacting to lightsaber hits, and taking damage.
     */
	public void collision(House house, Luke luke, Weapon lightsaber, HomeComponent home) {
		int ogX = x;
		int ogY = y;
		
		//Move toward user
		this.chase(luke);
		
		//movement flags
		if (movingLeft)  x -= dx;
        if (movingRight) x += dx;
        updateShape();
        if (movingUp)    y -= dy;
        if (movingDown)  y += dy;
        
        //keep in bounds
        x = Math.max(radius, Math.min(1500 - radius, x));
	    y = Math.max(radius, Math.min(900 - radius, y));
	    updateShape();
	    
	    //Wall collision resolution
	    if (EnemyisCollidingWithWall(house)) {
	        if (x>750) x=ogX+1;
	    	if (x<=750) x = ogX-1;
	        y = ogY;
	        
	    	updateShape();
	    }
	    
	    //Hit by Lightsaber
	    if (EnemyGettingSabered(lightsaber)) {
	    	if (!invincible) {
	    		dx = -dx;
	    		dy = -dy;
	    		health = health - lightsaber.getDamage();
	    		invincible = true;
	    		itimer = 50;
	    	}
	    	if (health <= 0) {
	    		alive = false;
	    		home.score++;
	    	}
	    }
	    
	    
	}
	
	//Collision between enemy and walls
	private boolean EnemyisCollidingWithWall(House house) {
		for (Shape wall : house.getWalls()) {
	        if (this.shape.intersects(wall.getBounds2D()))
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	//checks whether enemy is colliding with weapon
	private boolean EnemyGettingSabered(Weapon lightsaber) {
		if (this.shape.intersects(lightsaber.getShape().getBounds2D())) {
			return true;
		}
		return false;
	}
	
	//marks as killed
	public void Kill() {
		alive = false;
	}
		
	//movement flags
	public void setMovingLeft(boolean b)  { movingLeft = b; }
	public void setMovingRight(boolean b) { movingRight = b; }
	public void setMovingUp(boolean b)    { movingUp = b; }
	public void setMovingDown(boolean b)  { movingDown = b; }

	//Prevents enemies from overlapping by pushing them apart
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
	
	/**
     * Updates the invincibility timer. Once it hits zero, the enemy becomes
     * vulnerable again and movement is restored.
     */
	public void itimer() {
		if (invincible) {
			itimer--;
		}
		if (itimer == 0 && invincible) {
			dx = -dx;
			dy = -dy;
			invincible = false;
		}
	}
	}

