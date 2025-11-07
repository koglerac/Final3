package testing;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Enemies {

	private ArrayList<Enemy> enemies;
	
	public Enemies(int num) {
		enemies = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Enemy enemy = new Enemy();
			enemies.add(enemy);
		}
	}
	

	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		for (Enemy En : enemies) {
			En.draw(g2);
			En.itimer();
		}
	}


	public void follow(Luke luke, House house, Weapon lightsaber) {
		for (Enemy En : enemies) {
			En.collision(house, luke, lightsaber);
			}
		//chat line
		enemies.removeIf(e -> !e.alive);
		
		
	}
	
	public ArrayList<Enemy> getEnenmies() {
		return enemies;
	}

	public void EnemyIsCollidingWithEnemy() {
	    for (Enemy En : enemies) {
	    	En.bump(enemies, En);
	    }
	    
	}
	public void LukeisCollidingWithEnemy(Luke luke) {
	    for (Enemy en : enemies) {
	    	//edited chat code for luke instead of alternate enemies
		            double ox = luke.x - en.x;
		            double oy = luke.y - en.y;
		            double d = Math.sqrt(ox*ox + oy*oy);
		            double minDist = en.radius + luke.radius;
		            if (d < minDist && d != 0) {
		            	luke.health --;
		            	en.alive = false;
		            }
	        	
	        }
	    }


	public void setSpeed(int speed) {
		for (Enemy en: enemies) {
			en.dx=0;
			en.dy=0;
		}
	}
	

	public boolean checkIfWon() {
		if (enemies.size() == 0) {
			return true;
		}else {
			return false;
		}
	}

	public void removeDeadEnemies() {
		enemies.removeIf(e -> !e.alive);
	}
	
//	public void removeAll() {
//		for (Enemy e : enemies) {
//			enemies.remove(e);
//		}
//	}
	

}
