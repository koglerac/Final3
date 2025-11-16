package testing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Creates display window for game.
 * 
 * @author andresma
 */
public class FinalApp {
	private final JFrame frame = new JFrame("Tatoonie");
	private final LukeGamePanel panel = new LukeGamePanel();
	
	
	public FinalApp() {
		frame.setSize(1500, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        //frame.pack();                  // Fit to preferred component sizes
        frame.setLocationRelativeTo(null); // Center on screenon screen
        
	}
	public void show() {
		frame.setVisible(true);
	}
}
