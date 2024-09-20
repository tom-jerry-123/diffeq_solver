package mathParser;
/*
 * Implementation of unary operators / functions
 * Currently only support sin, cos, tan, ln, sqrt
 * These are the most common functions to show up in differential equations for physics
 * Other functions are unlikely to appear
 */
public interface UnaryOp {
	public abstract double compute(double x);
}

class Sine extends Operator implements UnaryOp {
	private static final String REPSTR = "sin";
	private static final int PREC = 3;
	private static final int NUM_OPRND = 1;
	
	public String getRepStr() {
		return REPSTR;
	}
	
	public int getPrecedence() {
		return PREC;
	}
	
	public int getNumOperand() {
		return NUM_OPRND;
	}
	
	public double compute(double x) {
		return Math.sin(x);
	}
}

class Cosine extends Operator implements UnaryOp {
	private static final String REPSTR = "cos";
	private static final int PREC = 3;
	private static final int NUM_OPRND = 1;
	
	public String getRepStr() {
		return REPSTR;
	}
	
	public int getPrecedence() {
		return PREC;
	}
	
	public int getNumOperand() {
		return NUM_OPRND;
	}
	
	public double compute(double x) {
		return Math.cos(x);
	}
}

class Tangent extends Operator implements UnaryOp {
	private static final String REPSTR = "tan";
	private static final int PREC = 3;
	private static final int NUM_OPRND = 1;
	
	public String getRepStr() {
		return REPSTR;
	}
	
	public int getPrecedence() {
		return PREC;
	}
	
	public int getNumOperand() {
		return NUM_OPRND;
	}
	
	public double compute(double x) {
		return Math.tan(x);
	}
}

class NaturalLog extends Operator implements UnaryOp {
	private static final String REPSTR = "ln";
	private static final int PREC = 3;
	private static final int NUM_OPRND = 1;
	
	public String getRepStr() {
		return REPSTR;
	}
	
	public int getPrecedence() {
		return PREC;
	}
	
	public int getNumOperand() {
		return NUM_OPRND;
	}
	
	public double compute(double x) {
		return Math.log(x);
	}
}

class SquareRoot extends Operator implements UnaryOp {
	private static final String REPSTR = "sqrt";
	private static final int PREC = 3;
	private static final int NUM_OPRND = 1;
	
	public String getRepStr() {
		return REPSTR;
	}
	
	public int getPrecedence() {
		return PREC;
	}
	
	public int getNumOperand() {
		return NUM_OPRND;
	}
	
	public double compute(double x) {
		return Math.sqrt(x);
	}
}

class Negation extends Operator implements UnaryOp {
	private static final String REPSTR = "N";
	private static final int PREC = 2;
	private static final int NUM_OPRND = 1;
	
	public String getRepStr() {
		return REPSTR;
	}
	
	public int getPrecedence() {
		return PREC;
	}
	
	public int getNumOperand() {
		return NUM_OPRND;
	}
	
	public double compute(double x) {
		return -x;
	}
}
