package testing;

public class level {
	public int level;
	
	public level(int n) {
		this.level = n;
	}
	
	public void nextlevel() {
		level++;
	}
	
	public int getLevel() {
		return level;
	}
}
