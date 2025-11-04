package testing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class House {
	private ArrayList<Shape> walls;
	
	public House(){
		this.walls = new ArrayList<>();
		walls.add(new Rectangle2D.Double(600, 325, 300, 25));      
		walls.add(new Rectangle2D.Double(600, 525, 300, 25));      
		walls.add(new Rectangle2D.Double(600, 325, 25, 75));      
		walls.add(new Rectangle2D.Double(600, 475, 25, 75));      
		walls.add(new Rectangle2D.Double(875, 325, 25, 75));      
		walls.add(new Rectangle2D.Double(875, 475, 25, 75));            
	}
	
	public ArrayList<Shape> getWalls() {
		return walls;
	}

	public void draw(Graphics g) {
		Color og = g.getColor();
	
		g.setColor(Color.GRAY);
		g.fillRect(600, 325, 300, 25);
		g.fillRect(600, 525, 300, 25);
		g.fillRect(600, 325, 25, 75); 
		g.fillRect(600, 475, 25, 75); 
		g.fillRect(875, 325, 25, 75); 
		g.fillRect(875, 475, 25, 75); 
		
		
		g.setColor(Color.black);
		g.drawRect(600, 325, 300, 25);
		g.drawRect(600, 525, 300, 25);
		g.drawRect(600, 325, 25, 75); 
		g.drawRect(600, 475, 25, 75);
		g.drawRect(875, 325, 25, 75); 
		g.drawRect(875, 475, 25, 75); 
		
		
		g.setColor(og);
		
	}

}
