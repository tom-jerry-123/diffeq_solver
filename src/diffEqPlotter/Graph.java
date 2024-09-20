/*
 * Implementation file for the graph
 */

package diffEqPlotter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

public class Graph extends Plot {
	//Variables for graph dimensions
	private static final int PADDING = 20;
	public static final String FUNC = "X"; //identifier string for function
	public static final String FIRST = "X\'"; //identifier string for first derivative
	public static final String SEC = "X\""; //identifier string for second derivative
	
	private static HashMap<String, Boolean> funcToGraph = new HashMap<String, Boolean>(); 
	//tells application whether we should graph function, first derivative, or second derivative
	
	private double x_max = 5, x_min = -5; // maximum and minimum of function graphed
	private double t_max = 10; // maximum and minimum time interval to graph; t_min > 0
	private final double t_min = 0; // time minimum locked at t_min = 0;
	private double dt = 0.005;
	
	/*
	 * Private internal functions for graphing
	 */
	private Vector computeNext(Vector prev) {
		try {
			double x_i = prev.getFunc(), v_i = prev.getFirst(), a_i = prev.getSecond();
			//declare and initialize next vector, representing next point on the euler-method approximation curve
			Vector next = new Vector(x_i + v_i * dt, v_i + a_i * dt);
			double A2 = equation.evaluateA_2();
			if (A2 == 0) {
				next.setSecond(0);
			}
			else {
				double a = (equation.evaluateB() - equation.evaluateA_1() * next.getFirst() - equation.evaluateA_0() * next.getFunc())/A2;
				next.setSecond(a);
			}
			return next;
		}
		catch (NullPointerException e) {
			System.out.println("Error: invalid differential equation input.");
			return null;
		}
		catch (Exception e) {
			System.out.println("Something unforeseen went wrong.");
			return null;
		}
	}
	
	private void graph(Graphics g) {
		
		double t = this.t_min;
		
		//Determine the proper vector for the initial conditions
		Vector next = equation.getInitCond();
		try {
			double A2 = equation.evaluateA_2();
			if (A2 == 0) {
				next.setSecond(0);
			}
			else {
				double a = (equation.evaluateB() - equation.evaluateA_1() * next.getFirst() - equation.evaluateA_0() * next.getFunc())/A2;
				next.setSecond(a);
			}
		}
		catch (NullPointerException e) {
			System.out.println("Error: Graph has not been inputted yet, or input is invalid.");
			return;
		}
		catch (Exception e) {
			//usually, I'll give error message for exception; but here, next = null will trigger error message in while loop
			System.out.println("Some unforeseen error occured with graphing.");
			next = null;
			return;
		}
		
		while (t < this.t_max) {
			if (next == null) {
				System.out.println("Something went wrong with graphing. Check if input is valid!");
				return;
			}
			//check if we need to graph function; if so, graph
			if (funcToGraph.get(FUNC)) {
				this.plotPoint(g, next.getFunc(), t);
			}
			//check if we need to graph first derivative; if so, graph
			if (funcToGraph.get(FIRST)) {
				this.plotPoint(g, next.getFirst(), t, Color.BLUE);
			}
			//check if we need to graph second derivative
			if (funcToGraph.get(SEC)) {
				this.plotPoint(g, next.getSecond(), t, Color.RED);
			}
			next = computeNext(next);
			t += dt;
		}
	}
	
	//plots a point, by first converting x, t coordinates on the cartesian plane to the x, y coordinates of the graphics plane
	//Then drawing the relevant point by calling Paint.drawPoint
	private void plotPoint(Graphics g, double val, double t) {
		//val = value of function to plot (t, x) -> coordinate system of function graph; (x, y) -> coordinate system of java gui
		int x = (int)(PADDING + (t - t_min) / (this.t_max - this.t_min) * (this.getWidth() - 2 * PADDING));
		int y = (int)(PADDING + (this.x_max - val) / (this.x_max - this.x_min) * (this.getHeight() - 2 * PADDING));
		Paint.drawPoint(g, x, y);
	}
	
	//overloaded plotPoint function
	private void plotPoint(Graphics g, double val, double t, Color color) {
		int x = (int)(PADDING + (t - t_min) / (this.t_max - this.t_min) * (this.getWidth() - 2 * PADDING));
		int y = (int)(PADDING + (this.x_max - val) / (this.x_max - this.x_min) * (this.getHeight() - 2 * PADDING));
		Paint.drawPoint(g, x, y, color);
	}
	
	/*
	 * Private internal functions for reading from settings cache
	 */
	
	private Double readDoubleFromCache(HashMap <String, String> cache, String str) {
		try {
			double a = Double.parseDouble(cache.get(str));
			return a;
		}
		catch (Exception e) {
			System.out.println("Unreadable input / input is not a number for " + str);
			return null;
		}
	}
	
	/*
	 * Public methods
	 */
	
	public void updateSettings(HashMap <String, String> cache) {
		Double dbl_input = readDoubleFromCache(cache, "x_min");
		
		if (dbl_input != null) {
			if (dbl_input > 0) {
				System.out.println("Value for x_min too large (x_min should be non-positive).");
			}
			else {
				this.x_min = dbl_input;
			}
		}
		
		dbl_input = readDoubleFromCache(cache, "x_max");
		if (dbl_input != null) {
			if (dbl_input < 0) {
				System.out.println("Value of x_max too small (x_max should be non-negative).");
			}
			else {
				this.x_max = dbl_input;
			}
		}
		
		Double maximum = readDoubleFromCache(cache, "t_max");
		maximum = maximum != null ? maximum : this.t_max;
		
		if (maximum > 0) {
			this.t_max = maximum;
		}
		else {
			System.out.println("t_max should be positive, right?");
		}
	}
	
	/*
	 * Public methods. Getter and setter methods
	 */
	
	public static void setFuncToGraph(boolean zero, boolean first, boolean second) {
		funcToGraph.put(FUNC, zero);
		funcToGraph.put(FIRST, first);
		funcToGraph.put(SEC, second);
	}
	
	//get hashmap to determine which function are set to be graphed
	public static Boolean getFuncToGraph(String s) {
		if (funcToGraph.get(s) != null) {
			return funcToGraph.get(s);
		}
		System.out.println("Bad query for key \'" + s + "\' to funcToGraph in Graph class.");
		return null;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("Serif", Font.BOLD, 14));
		
		int x_origin = PADDING; //x-coordinate of origin in GUI coordinate sys.; corresponds to t = 0.
		//y-coord. corresponds to x = 0;
		int y_origin = (int)((this.x_max) / (this.x_max - this.x_min) * (getHeight() - 2 * PADDING) + PADDING);
		
		
		//Draw the axis
		Paint.drawArrow(g, x_origin, y_origin, x_origin, PADDING);
		Paint.drawArrow(g, x_origin, y_origin, x_origin, getHeight() - PADDING);
		Paint.drawArrow(g, x_origin, y_origin, getWidth() - PADDING, y_origin);
		g.drawString("T", getWidth() - PADDING - 5, y_origin - 5);
		g.drawString(String.valueOf(this.t_max), getWidth() - PADDING - 10, y_origin + 15);
		
		//Draw y-direction axis (the x-axis, as x is plotted in y-dir) Labels
		if (y_origin < getHeight()/10) {
			// t axis too close to the tope of screen. Omit x_max label
			g.drawString("X", 2, getHeight() - PADDING + 5);
			g.drawString(String.valueOf(this.x_min), x_origin + 5, getHeight() - PADDING + 5);
		}
		else if (y_origin > getHeight() * 9 / 10) {
			// t axis too close to the bottom of screen. Omit x_min label
			g.drawString("X", 2, PADDING + 5);
			g.drawString(String.valueOf(this.x_max), x_origin + 5, PADDING + 5);
		}
		else {
			// t axis comfortably in the middle. Draw as normal.
			g.drawString("X", 2, PADDING + 5);
			g.drawString(String.valueOf(this.x_max), x_origin + 5, PADDING + 5);
			g.drawString(String.valueOf(this.x_min), x_origin + 5, getHeight() - PADDING + 5);
		}
		
		//do the graphing
		
		// This is to do exception handling as to not crash the program
		try {
			if (funcToGraph.get(FUNC) || funcToGraph.get(FIRST) || funcToGraph.get(SEC)) {
				//if this works, all good
			}
		}
		catch (NullPointerException e) {
			System.out.println("!!!!!\nError to coder: funcToGraph not initialized\n!!!!!");
			return;
		}
		catch (Exception e) {
			System.out.println("Something went wrong.");
			return;
		}
		
		if (funcToGraph.get(FUNC) || funcToGraph.get(FIRST) || funcToGraph.get(SEC)) {
			graph(g);
		}
		else {
			System.out.println("You have not selected a function to graph. Please select one.");
		}
	}
}