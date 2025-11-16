package testing;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

/**
 * Describes Luke's movements and basic game mechanics.
 * 
 * @author andresma, koglerac
 */

public class LukeGamePanel extends JPanel {

	private final HomeComponent canvas = new HomeComponent();
	private boolean pause = false;
	
	
	//adds Luke to game screen
	public LukeGamePanel() {
    	this.setLayout(new BorderLayout(8, 8));
        this.add(canvas, BorderLayout.CENTER);
        this.setBackground(canvas.BG);
        this.buildKeys();
    	
    }
	    
	//Assigns movement keys to Luke's sprite
	private void buildKeys() {
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	        	switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> canvas.setMovingLeft(true);
                case KeyEvent.VK_RIGHT -> canvas.setMovingRight(true);
                case KeyEvent.VK_UP -> canvas.setMovingUp(true);
                case KeyEvent.VK_DOWN -> canvas.setMovingDown(true);
                case KeyEvent.VK_ESCAPE -> pause();
                case KeyEvent.VK_SPACE -> canvas.lightsaberSpin(true);
	        	}
	        }
	        
	        
	        @Override
	        public void keyReleased(KeyEvent e) {
	            switch (e.getKeyCode()) {
	                case KeyEvent.VK_LEFT -> canvas.setMovingLeft(false);
	                case KeyEvent.VK_RIGHT -> canvas.setMovingRight(false);
	                case KeyEvent.VK_UP -> canvas.setMovingUp(false);
	                case KeyEvent.VK_DOWN -> canvas.setMovingDown(false);
	            }
	        }
	    });
		
}
	//flips when game ends
	public boolean getGameOver() {
		if (canvas.gameOver == false) {
			return false;
		}
		return true;
	}

	//optional game pause
	private void pause() {
		if (pause == true) {
			canvas.timer.start();
			pause = false;
		}
		else {
			canvas.timer.stop();
			pause = true;
		}
	}
}
