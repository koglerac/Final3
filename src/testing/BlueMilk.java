package testing;

import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlueMilk {
	int x, y;
    int radius = 5;
    private BufferedImage sprite;
    private boolean spriteLoaded = false;
    public Shape shape;   
	
    public BlueMilk(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            sprite = ImageIO.read(Luke.class.getResource("Luke.220.png"));
            spriteLoaded = (sprite != null);
        } catch (IOException | IllegalArgumentException ex) {
            spriteLoaded = false; // fallback to oval
        }
        updateShape();
    }
}
