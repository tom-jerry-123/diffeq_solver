//Encapsulates information about vectors on vector fields

package diffEqPlotter;

public class Vector {
	private double func, first, second; 
	//func -> value of function; first -> value of first derivative; second -> value of second derivative
	private double mag; //magnitude of vector. calculated using first and second
	
	Vector() {
		func = 0;
		first = 0;
		second = 0;
		mag = 0;
	}
	
	Vector(double func, double first) {
		this.func = func;
		this.first = first;
		this.second = 0;
		this.mag = 0;
	}
	
	/*
	 * Public getters and setters
	 */
	
	public double getFunc() {
		return this.func;
	}
	
	public double getFirst() {
		return this.first;
	}
	
	public double getSecond() {
		return this.second;
	}
	
	public double getMag() {
		return this.mag;
	}
	
	public void setFunc(double func) {
		this.func = func;
	}
	
	public void setFirst(double first) {
		this.first = first;
	}
	
	public void setSecond(double second) {
		this.second = second;
		this.mag = Math.sqrt(first*first + second*second); // do this at the same time
	}
}
