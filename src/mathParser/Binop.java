package mathParser;
/*
 * implementation file of all binary operators
 */
public interface Binop {
	public abstract double compute(double x, double y);
}

class Add extends Operator implements Binop {
	private static final String repStr = "+";
	private static final int precedence = 0; //priority in order of operations
	private static final int numOperand = 2;
	
	public double compute(double x, double y) {
		return x + y;
	}
	
	public String getRepStr() {
		return repStr;
	}
	
	public int getPrecedence() {
		return precedence;
	}
	
	public int getNumOperand() {
		return numOperand;
	}
}

class Subtract extends Operator implements Binop {
	private static final String repStr = "-";
	private static final int precedence = 0;
	private static final int numOperand = 2;
	
	public double compute(double x, double y) {
		return x - y;
	}
	
	public double compute(double y) {
		return -y;
	}
	
	public String getRepStr() {
		return repStr;
	}
	
	public int getPrecedence() {
		return precedence;
	}
	
	public int getNumOperand() {
		return numOperand;
	}
}

class Multiply extends Operator implements Binop {
	private static final String repStr = "*";
	private static final int precedence = 1;
	private static final int numOperand = 2;
	
	public double compute(double x, double y) {
		return x * y;
	}
	
	public String getRepStr() {
		return repStr;
	}
	
	public int getPrecedence() {
		return precedence;
	}
	
	public int getNumOperand() {
		return numOperand;
	}
}

class Divide extends Operator implements Binop {
	private static final String repStr = "/";
	private static final int precedence = 1;
	private static final int numOperand = 2;
	
	//x -> previous operand, y -> current operand
	public double compute(double x, double y) {
		return x / y;
	}
	
	public String getRepStr() {
		return repStr;
	}
	
	public int getPrecedence() {
		return precedence;
	}
	
	public int getNumOperand() {
		return numOperand;
	}
}
