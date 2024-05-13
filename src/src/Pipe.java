
public class Pipe {
	private double length, diameter;
	private Loc point1, point2;
	
	public Pipe(Loc point1, Loc point2, double diameter) {
		this.point1 = point1;
		this.point2 = point2;
		this.diameter = diameter;
		this.length = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
	}
}
