/*
 * This is meant to represent point on graph; but it seems to be unnecessary
 */

package diffEqPlotter;

public class Point {
	private double y, t;
	
	Point() {
		y = 0;
		t = 0;
	}
	
	Point(double t, double y) {
		this.t = t;
		this.y = y;
	}
	
	public double getFunc() {
		return y;
	}
	
	public double getTime() {
		return t;
	}
}
