package testing;

public class level {
	public static int level;
	
	public level(int n) {
		this.level = n;
	}
	
	public void nextlevel() {
		level++;
		
	}
	
	public int getLevel() {
		return level;
	}
	
	public void reset() {
		this.level = 1;
	}
}
