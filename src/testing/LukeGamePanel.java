package testing;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;



public class LukeGamePanel extends JPanel {

	private final HomeComponent canvas = new HomeComponent();
	private boolean pause = false;
	
	public LukeGamePanel() {
    	this.setLayout(new BorderLayout(8, 8));
        this.add(canvas, BorderLayout.CENTER);
        this.setBackground(canvas.BG);
        this.buildKeys();
    	
    }
	    
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
                //case KeyEvent.VK_SPACE -> canvas.lightsaberSwing(true);
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

	public boolean getGameOver() {
		if (canvas.gameOver == false) {
			return false;
		}
		return true;
	}
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
