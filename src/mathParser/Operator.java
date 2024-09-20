package mathParser;
/*
 * Class used to store information about operators
 */

import java.util.HashMap;

public abstract class Operator implements Comparable<Operator> {
	//String: represents operator. int: represents precedence; greater int -> higher precedence
	private static HashMap<String, Operator> opList = new HashMap<String, Operator>();
	
	//List of operators
	//Binary Operators
	public static final Add ADD = new Add();
	public static final Subtract SUB = new Subtract();
	public static final Multiply MUL = new Multiply();
	public static final Divide DIV = new Divide();
	
	//Unary operators
	public static final Sine SIN = new Sine();
	public static final Cosine COS = new Cosine();
	public static final Tangent TAN = new Tangent();
	public static final NaturalLog LN = new NaturalLog();
	public static final SquareRoot SQRT = new SquareRoot();
	public static final Negation NEG = new Negation();
	
	
	//This is not quite an operator
	public static final OpenBracket OPEN = new OpenBracket();
	
	public int compareTo(Operator op) {
		return this.getPrecedence() - op.getPrecedence();
	}
	
	private static void init() {
		opList.put(ADD.getRepStr(), ADD);
		opList.put(SUB.getRepStr(), SUB);
		opList.put(MUL.getRepStr(), MUL);
		opList.put(DIV.getRepStr(), DIV);
		
		opList.put(SIN.getRepStr(), SIN);
		opList.put(COS.getRepStr(), COS);
		opList.put(TAN.getRepStr(), TAN);
		opList.put(LN.getRepStr(), LN);
		opList.put(SQRT.getRepStr(), SQRT);
		opList.put(NEG.getRepStr(), NEG);
		//Operator classes not on this list: "("
	}
	
	public static boolean isOp(String s) {
		if (opList.size() == 0) {
			init();
		}
		return opList.get(s) != null;
	}
	
	public static Operator getOp(String s) {
		if (opList.size() == 0) {
			init();
		}
		return opList.get(s);
	}
	
	public String toString() {
		return this.getRepStr();
	}
	
	public abstract String getRepStr();
	public abstract int getPrecedence();
	public abstract int getNumOperand();
}

/*
 * Special characters
 */

class OpenBracket extends Operator {
	private static final String repStr = "(";
	private static final int precedence = -1; //special character; technically no precedence
	private static final int numOperand = 0;
	
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
