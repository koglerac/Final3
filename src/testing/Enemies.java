package testing;

import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * Manages a collection of Enemy objects
 * @author andresma
 */
public class Enemies {

	//List of all enemies
	public static ArrayList<Enemy> enemies;
	
	//reference to main component
	private HomeComponent home; 

	//Creates a new enemy manager and spawns the given number of enemies.
	public Enemies(int num) {
		enemies = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Enemy enemy = new Enemy();
			enemies.add(enemy);
		}
	}
	
	//draws all enemies in list
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		for (Enemy En : enemies) {
			En.draw(g2);
			En.itimer();
		}
	}

	/**Updates all enemies by making them chase Luke, handle wall collisions,
     * process lightsaber hits, and remove dead enemies.
	 */
	public void follow(Luke luke, House house, Weapon lightsaber, HomeComponent home) {
		for (Enemy En : enemies) {
			En.collision(house, luke, lightsaber, home);
			}
		//chat line
		enemies.removeIf(e -> !e.alive);
		
		
	}
	
	public ArrayList<Enemy> getEnenmies() {
		return enemies;
	}

	//Handles enemy–enemy collision resolution
	public void EnemyIsCollidingWithEnemy() {
	    for (Enemy En : enemies) {
	    	En.bump(enemies, En);
	    }
	    
	}
	
	//Checks whether Luke collides with any enemy.
	public void LukeisCollidingWithEnemy(Luke luke, HomeComponent home) {
	    for (Enemy en : enemies) {
	    	//edited chat code for luke instead of alternate enemies
		            double ox = luke.x - en.x;
		            double oy = luke.y - en.y;
		            double d = Math.sqrt(ox*ox + oy*oy);
		            double minDist = en.radius + luke.radius;
		            if (d < minDist && d != 0) {
		            	luke.health --;
		            	en.alive = false;
		            	home.score++;
		            }
	        	
	        }
	    }

	//Sets all enemy speeds to the given value.
	public void setSpeed(int speed) {
		for (Enemy en: enemies) {
			en.dx=0;
			en.dy=0;
		}
	}
	
	//Checks whether the player has defeated all enemies.
	public boolean checkIfWon() {
		if (enemies.size() == 0) {
			return true;
		}else {
			return false;
		}
	}

	// Instantly kills all enemies without removing them.
	public void removeAll() {
		for (Enemy e : enemies) {
			e.Kill();
			
		}
	}
	

}
