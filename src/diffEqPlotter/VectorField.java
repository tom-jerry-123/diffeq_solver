/*
 * Implementation File for vector fields
 * 
 * Conventions: i counts rows from top to bottom; j counts columns from left to right
 */

/*
 * Something is wrong with vector field plotter.
 */
package diffEqPlotter;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VectorField extends Plot {
	private static final int PADDING = 20;
	//settings for different vector fields
	private int num_x = 20, num_y = 20; //number of vectors in x-direction, number of vectors in y (first derivative)-direction
	private double x_min = -10, x_max = 10, y_min = -10, y_max = 10; //max / min coordinates in x, y directions
	
	private Vector[][] vectArray; //array storing vectors
	/*
	 * Using default constructor
	 */
	
	public void buildArray() {
		vectArray = new Vector[num_y][num_x];
		//i = 0 -> top row; j= 0 -> leftmost column
		for (int i=0; i<num_y; i++) {
			for (int j=0; j<num_x; j++) {
				vectArray[i][j] = new Vector(x_min + (double)(j+1)/(num_x+1) * (x_max - x_min), y_max - (double)(i+1)/(num_y+1) * (y_max - y_min));
				double sec;
				try {
					//interestingly, input validation is done well enough in evaluate function that no errors pop up here
					double a_2 = equation.evaluateA_2(), a_1 = equation.evaluateA_1(), a_0 = equation.evaluateA_0(), b = equation.evaluateB();
					sec = (b - a_1 * vectArray[i][j].getFirst() - a_0 * vectArray[i][j].getFunc())/ a_2;
					vectArray[i][j].setSecond(sec);
				}
				catch (Exception e) {
					System.out.println("***");
					System.out.println("Some error in differential equation / equation not inputted. Vector Array set to null.");
					System.out.println("***");
					vectArray = null;
					return;
				}
				
				//System.out.println("x-y coodinates for vector in row " + i + " and col " + j + ": " + vectArray[i][j].getFunc() + ", " + vectArray[i][j].getFirst());
			}
		}
	}
	
	//Function to update settings. Function to update input is under plot superclass
	public void updateSettings(HashMap<String, String> cache) {
		Double input;
		input = readFromCache(cache, "num_x");
		if (input != null) {
			if (input < 10) {
				System.out.println(input + " is too few vectors to draw (num_x not updated).");
			}
			else if (input > 30) {
				System.out.println("Too many vectors to draw!");
			}
			else {
				this.num_x = (int)(double)input;
			}
		}
		
		input = readFromCache(cache, "num_y");
		if (input != null) {
			if (input < 10) {
				System.out.println(input + " is too few vectors to draw (num_y not updated).");
			}
			else if (input > 30) {
				System.out.println("Too many vectors to draw!");
			}
			else {
				this.num_y = (int)(double)input;
			}
		}
		
		Double minimum = null, maximum = null;
		input = readFromCache(cache, "x_min");
		minimum = input != null ? input : this.x_min;
		
		input = readFromCache(cache, "x_max");
		maximum = input != null ? input : this.x_max;
		
		if (minimum < maximum) {
			this.x_min = minimum;
			this.x_max = maximum;
		}
		else {
			System.out.println("Invalid input for window range in the x (horizontal) direction!");
		}
		
		minimum = null;
		maximum = null;
		input = readFromCache(cache, "y_min");
		minimum = input != null ? input : this.y_min;
		
		input = readFromCache(cache, "y_max");
		maximum = input != null ? input : this.y_max;
		
		if (minimum < maximum) {
			this.y_min = minimum;
			this.y_max = maximum;
		}
		else {
			System.out.println("Invalid input for window range in the y (vertical) direction!");
		}
	}
	
	private Double readFromCache(HashMap<String, String> cache, String str) {
		/*
		if (cache.get(str) == null) {
			return null;
		}
		*/
		
		try {
			double a = Double.parseDouble(cache.get(str));
			return a;
		}
		catch (Exception e) {
			System.out.println("Unreadable input for " + str);
			return null;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Font axisLabelFont = new Font("Serif", Font.BOLD, 14);
		g.setFont(axisLabelFont);
		
		/*
		 * Return here to draw axis and axis labels properly for fringe cases.
		 */
		
		//vertical axis
		int x_origin = (int)(Math.abs(x_min)/(x_max - x_min)*(getWidth() - 2*PADDING) + PADDING); // x coordinate of origin in GUI coordinate system
		int y_origin = (int)(Math.abs(y_max)/(y_max - y_min)*(getHeight() - 2*PADDING) + PADDING); //y- coordinate of origin; recall that y-direction represents values of dx/dt
		
		Paint.drawArrow(g, x_origin, y_origin, x_origin, PADDING);
		Paint.drawArrow(g, x_origin, y_origin, x_origin, getHeight() - PADDING);
		
		if (y_origin < getHeight()/10) {
			//x-axis is too close to the top of the screen -> omit label for y_max (maximum value of x_dot)
			g.drawString(".", x_origin + 10, getHeight() - 25);
			g.drawString("X", x_origin + 5, getHeight() - 10);
			g.drawString(String.valueOf(this.y_min), x_origin - 35, getHeight() - 10);
		}
		else if (y_origin > getHeight()*9/10) {
			//x-axis is too close to the top of the screen -> omit label for y_min (minimum value of x_dot)
			g.drawString(".", x_origin + 10, 10);
			g.drawString("X", x_origin + 5, 25);
			g.drawString(String.valueOf(this.y_max), x_origin - 35, 25);
		}
		else {
			//draw as normal
			g.drawString(".", x_origin + 10, 10);
			g.drawString("X", x_origin + 5, 25);
			g.drawString(String.valueOf(this.y_max), x_origin - 35, 25);
			g.drawString(String.valueOf(this.y_min), x_origin - 35, getHeight() - 10);
		}
		
		//horizontal axis
		Paint.drawArrow(g, x_origin, y_origin, 20, y_origin);
		Paint.drawArrow(g, x_origin, y_origin, getWidth()-20, y_origin);
		
		if (x_origin < getWidth()/10) {
			// y-axis is too close to the left of screen -> omit x_min
			g.drawString("X", getWidth() - 20, y_origin - 5);
			g.drawString(String.valueOf(this.x_max), getWidth() - 30, y_origin + 20);
		}
		else if (x_origin > getWidth()*9/10) {
			// y-axis is too close to the right of the screen
			g.drawString("X", getWidth() - 20, y_origin - 5);
			g.drawString(String.valueOf(this.x_min), 5, y_origin + 20);
		}
		else {
			g.drawString("X", getWidth() - 20, y_origin - 5);
			g.drawString(String.valueOf(this.x_max), getWidth() - 30, y_origin + 20);
			g.drawString(String.valueOf(this.x_min), 5, y_origin + 20);
		}
		
		
		//construct array of vectors
		buildArray();
		
		//Draw vectors
		if (vectArray == null) {
			System.out.println("Vector Field will be plotted once a proper differential equation gets entered.");
		}
		else {
			//System.out.println("Width of window:" + this.getWidth());
			for (int i=0; i<num_y; i++) {
				for (int j=0; j<num_x; j++) {
					//calculate coordinates of vector
					
					//System.out.println("Sine theta: " + vectArray[i][j].getFirst() / vectArray[i][j].getMag());
					int x1 = (int)( (vectArray[i][j].getFunc() - x_min)/(x_max - x_min) * this.getWidth() - 10 * vectArray[i][j].getFirst() / vectArray[i][j].getMag() );
					int y1 = (int)( (y_max - vectArray[i][j].getFirst())/(int)(y_max - y_min) * this.getHeight() + 10 * vectArray[i][j].getSecond() / vectArray[i][j].getMag() );
					int x2 = (int)( (vectArray[i][j].getFunc() - x_min)/(int)(x_max - x_min) * this.getWidth() + 10 * vectArray[i][j].getFirst() / vectArray[i][j].getMag() );
					int y2 = (int)( (y_max - vectArray[i][j].getFirst())/(int)(y_max - y_min) * this.getHeight() - 10 * vectArray[i][j].getSecond() / vectArray[i][j].getMag() );
					//System.out.println("Drawing coodinates for vector in row " + i + " and col " + j + ": " + x1 + ", " + y1 + ", " + x2 + ", " + y2);
					Paint.drawArrow(g, x1, y1, x2, y2);
				}
			}
		}
		//
		//g.drawLine(10, 10, 100, 100);
		//g.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 2 * 50, 2 * 50);
	}
}
