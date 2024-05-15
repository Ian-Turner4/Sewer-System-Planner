
public class Pipe {
	double length;
	int x1, x2, y1, y2;
	
	public Pipe(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}
