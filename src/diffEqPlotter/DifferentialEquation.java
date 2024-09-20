package diffEqPlotter;
/*
 * Implementation of Differential Equation class. Stores methods related to interpreting input from differential equations
 */

import mathParser.MathExpression;

public class DifferentialEquation {
	/*
	 * Differential equation assumed to be linear
	 * Differential equation taken in the form A(x) x" + B(x) x' + C(x) x = D(x)
	 * A_0(x) = function in front of second derivative
	 * coef_first = B(x) = function in front of first derivative
	 * coef_func = C(x) = function in front of x
	 * rightSide = right side of different
	 */
	private MathExpression A_2, A_1, A_0, B;
	private double x_0, v_0; //Initial Conditions: x_0 -> initial value of x; v_0 -> initial value of dx/dt
	
	DifferentialEquation() {
		x_0 = 0;
		v_0 = 0;
		A_2 = new MathExpression("A_2");
		A_1 = new MathExpression("A_1");
		A_0 = new MathExpression("A_0");
		B = new MathExpression("B");
	}
	
	public void setA_2(String s) {
		A_2.setExpression(s);
	}
	
	public void setA_1(String s) {
		A_1.setExpression(s);
	}
	
	public void setA_0(String s) {
		A_0.setExpression(s);
	}
	
	public void setB(String s) {
		B.setExpression(s);
	}
	
	public void setV_0(double v) {
		v_0 = v;
	}
	
	public void setX_0(double x) {
		x_0 = x;
	}
	
	/*
	 * Methods to evaluate coefficient functions
	 */
	
	public double evaluateA_0() {
		return A_0.evaluate();
	}
	
	public double evaluateA_1() {
		return A_1.evaluate();
	}
	
	public double evaluateA_2() {
		return A_2.evaluate();
	}
	
	public double evaluateB() {
		return B.evaluate();
	}
	
	/*
	 * Methods to get initial conditions
	 */
	
	public Vector getInitCond() {
		return new Vector(this.x_0, this.v_0);
	}
}
