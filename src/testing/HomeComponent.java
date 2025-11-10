package testing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class HomeComponent extends JComponent {
	private Luke luke = new Luke(WIDTH/2,HEIGHT/2);
	private Weapon lightsaber = new Weapon(luke);
	private Enemies enemies = new Enemies(1);
	private final House house = new House();
	private Milks milks = new Milks(0);
	final Color BG = new Color(237,201,175);
	public static final int WIDTH = 1500;
	public static final int HEIGHT = 900;
	public boolean movingLeft, movingRight, movingUp, movingDown;
	public boolean gameOver = false;
	private boolean once= true;
	private BufferedImage BGsprite;
	private boolean sandstorm = false;
	private level level = new level(1);
	Timer timer;

	public HomeComponent() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	    timer = new Timer(10, e-> {
	    	int ogX = luke.x;
	        int ogY = luke.y;

	    	if (movingLeft)  luke.x -= 4; 
	        if (movingRight) luke.x += 4; 
	        if (movingUp)    luke.y -= 4; 
	        if (movingDown)  luke.y += 4; 
	        luke.x = Math.max(luke.radius, Math.min(WIDTH - luke.radius, luke.x));
		    luke.y = Math.max(luke.radius, Math.min(HEIGHT - luke.radius, luke.y));
		    luke.updateShape();
		    
		    if (LukeisCollidingWithWall()) {
		        // revert to previous position
		        luke.x = ogX;
		        luke.y = ogY;
		        luke.updateShape();
		    }
		    lightsaber.updatePos(luke, movingLeft, movingRight, movingUp, movingDown);
		    milks.collideWithMilk(luke);
		    enemies.follow(luke, house, lightsaber);
		    enemies.EnemyIsCollidingWithEnemy();
		    enemies.LukeisCollidingWithEnemy(luke);
		    if (luke.health == 0) {
		    	gameOver = true;
		    	//ChatGPT code until space
		    	SwingUtilities.invokeLater(() -> {
		            int choice = JOptionPane.showConfirmDialog(
		                    this,
		                    "Game Over!\nWould you like to play again?",
		                    "Game Over",
		                    JOptionPane.YES_NO_OPTION
		            );

		            
		            
		            if (choice == JOptionPane.YES_OPTION) {
		                restartGame();
		            } else {
		                System.exit(0);
		            }
		        });
		    	timer.stop();
		    }
		    if (enemies.checkIfWon()) {
		    	level.nextlevel() ;
		    	if (level.getLevel()%2==0)  milks = new Milks(1);
		    	if (level.getLevel() == 10) {
		    		enemies = new Enemies(100);}
		    	else if (level.getLevel()==11) {
		    		SwingUtilities.invokeLater(() -> {
			            int choice = JOptionPane.showConfirmDialog(
			                    this,
			                    "You Won! Congratulations on escaping Tatooine\n Would you like to play again?",
			                    "You Won",
			                    JOptionPane.YES_NO_OPTION
			            );
		    			
		    		if (choice == JOptionPane.YES_OPTION) {
		                restartGame();
		            } else {
		                System.exit(0);
		            }
		    		
		    		});
		    		timer.stop();
		    		}
		    	else {
		    		enemies = new Enemies((level.getLevel()-1)*3+1);
		    	}
		    }
	        repaint();
	        });
	    timer.start();
	}
			

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		if (once) {
			GetSand();
			once=false;
		}
		background(g2);
		luke.draw(g2);
		enemies.draw(g2);
        house.draw(g2);
        g2.rotate(lightsaber.saberangle, luke.x, luke.y);
		lightsaber.draw(g2);
		g2.setTransform(old);
        milks.draw(g2);
        showScore(g2);
        showLives(g2);
        Sandstorm(g2);
        g2.setColor(Color.RED);
        g2.draw(lightsaber.shape);
	}
	
	
	public void Sandstorm(Graphics2D graphics2) {
		Random rand = new Random();
		
		if (level.level>=2) {
			int randomNum = rand.nextInt(1);
	    		if (randomNum == 1) { //SANDSTORM!!
	    			luke.x=luke.x-2;
	    			
	    			graphics2.setFont(new Font("Verdana", Font.BOLD, 25));
	    		    graphics2.setColor(Color.BLUE);
	    		    graphics2.drawString("SANDSTORM!!", 10, 100); // x=10, y=30
	    		}
		}		
	}
	
	public void GetSand() {
	    try {
	        BGsprite = ImageIO.read(Luke.class.getResource("sand.png"));
	    } catch (IOException | IllegalArgumentException ex) {
	        System.err.println("Failed to load sand.png: " + ex.getMessage());
	        BGsprite = null;
	    }
	}

	public void background(Graphics2D graphics2) {
	    if (BGsprite != null) {
	        graphics2.drawImage(BGsprite, 0, 0, 1500, 900, null);
	    } else {
	        graphics2.setColor(new Color(237, 201, 175));
	        graphics2.fillRect(0, 0, 1500, 900);
	    }
	}
	
	public void moveHorizontal(int dx) {
        luke.x += dx;
//        lightsaber.x += dx;
        repaint(); 
	}
	
	public void moveVertical(int dy) {
		luke.y+=dy;
//		lightsaber.y += dy;
		repaint();
	}
	
	public void showScore(Graphics2D graphics2) {
		int score = 2;
		graphics2.setFont(new Font("Verdana", Font.BOLD, 25));
	    graphics2.setColor(Color.BLUE);
	    graphics2.drawString("Score: <<SCORE NOT ACCURATE>>" + score, 10, 30); // x=10, y=30
	    graphics2.drawString("Level: " + level.getLevel(), 1370, 30);
	}
	public void showLives(Graphics2D graphics2) {
		int lives = Luke.health;
		graphics2.setFont(new Font("Verdana", Font.BOLD, 25));
	    graphics2.setColor(Color.BLUE);
	    graphics2.drawString("Lives: " + lives, 10, 65);
	}
	
	private boolean LukeisCollidingWithWall() {
	    for (Shape wall : house.getWalls()) {
	        if (luke.shape.intersects(wall.getBounds2D()))
	        {
	            return true;
	        }
	    }
	    return false;
	}
	private void restartGame() {
		resetGame();
        gameOver = false;
        timer.start();	
	}
	
	private void resetGame() {
        luke.reset(WIDTH/2, HEIGHT/2, this);
        enemies.removeAll();
        enemies = new Enemies(1);
        level.reset();
        System.out.println(level.getLevel());
    }

	
	
	public void setMovingLeft(boolean b)  { movingLeft = b; }
	public void setMovingRight(boolean b) { movingRight = b; }
	public void setMovingUp(boolean b)    { movingUp = b; }
	public void setMovingDown(boolean b)  { movingDown = b; }
}