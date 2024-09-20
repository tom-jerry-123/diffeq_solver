/*
 * Paint class
 * Used for static drawing methods; very convenient
 */

package diffEqPlotter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Paint {
	//draw arrow from (x1, y1) to (x2, y2)
	public static void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
		double mag = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
		double sine = (y2-y1)/mag, cosine = (x2-x1)/mag;
		g.drawLine(x1, y1, x2, y2);
		//figure out the math 
		Polygon arrowHead = new Polygon(new int[] {(int)(x2 + 4 * cosine), (int)(x2 + 3*sine), (int)(x2 - 3*sine)}, new int[] {(int)(y2 + 4 * sine), (int)(y2 - 3*cosine), (int)(y2 + 3 * cosine)}, 3);
		g.drawPolygon(arrowHead);
		g.fillPolygon(arrowHead);
	}
	
	//draw a point at graphic coordinates x, y, with default color (black)
	public static void drawPoint(Graphics g, int x, int y) {
		g.fillOval(x-1, y-1, 2, 2);
	}
	
	//draw point with specified color
	public static void drawPoint(Graphics g, int x, int y, Color color) {
		Color original = g.getColor();
		g.setColor(color);
		g.fillOval(x-1, y-1, 2, 2);
		g.setColor(original);
	}
}
