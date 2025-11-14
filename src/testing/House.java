package testing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
/**
 * Represents a house with walls that can block movement in the game.
 * @author andresma
 */
public class House {
	//List of walls that make up the house
	private ArrayList<Shape> walls;
	
	//Construct House object and initialize walls
	public House(){
		this.walls = new ArrayList<>();
		walls.add(new Rectangle2D.Double(600, 325, 300, 25));      
		walls.add(new Rectangle2D.Double(600, 525, 300, 25));      
//		walls.add(new Rectangle2D.Double(600, 325, 25, 75));      
//		walls.add(new Rectangle2D.Double(600, 475, 25, 75));      
//		walls.add(new Rectangle2D.Double(875, 325, 25, 75));      
//		walls.add(new Rectangle2D.Double(875, 475, 25, 75));            
	}
	
	public ArrayList<Shape> getWalls() {
		return walls;
	}

	//draws and fills rectangles making up walls
	public void draw(Graphics g) {
		Color og = g.getColor();
	
		g.setColor(Color.GRAY);
		g.fillRect(600, 325, 300, 25);
		g.fillRect(600, 525, 300, 25);
//		g.fillRect(600, 325, 25, 75); 
//		g.fillRect(600, 475, 25, 75); 
//		g.fillRect(875, 325, 25, 75); 
//		g.fillRect(875, 475, 25, 75); 
		
		
		g.setColor(Color.black);
		g.drawRect(600, 325, 300, 25);
		g.drawRect(600, 525, 300, 25);
//		g.drawRect(600, 325, 25, 75); 
//		g.drawRect(600, 475, 25, 75);
//		g.drawRect(875, 325, 25, 75); 
//		g.drawRect(875, 475, 25, 75); 
		
		
		g.setColor(og);
		
	}

}
