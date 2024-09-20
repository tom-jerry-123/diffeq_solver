package mathParser;
/*
 * Abstract syntax tree for creating
 */
public class MathExpression {
	private String identifier;
	private String inputEq;
	private String[] tokenizedEq, postfixEq;
	private int tokenized_size, postfix_size;
	
	//whether the equation is valid
	private Boolean isValid = null; 
	
	
	 public MathExpression() {
		 //default constructor, do nothing
		 inputEq = "";
		 tokenized_size = 0;
		 postfix_size = 0;
	 }
	 
	 public MathExpression(String identifier) {
		 this.identifier = identifier;
		 inputEq = "";
		 tokenized_size = 0;
		 postfix_size = 0;
	 }
	 
	 public MathExpression(String identifier, String eq) {
		 inputEq = eq;
		 this.identifier = identifier;
		 tokenizedEq = null;
		 postfixEq = null;
		 postfix_size = 0;
		 tokenized_size = 0;
		 tokenize();
		 toPostfix();
	 }
	 
	 public Double evaluate() {
		 Stack operandStack = new Stack();
		 
		 if (isValid != null && !isValid) {
			 System.out.println("***Expression may be invalid. Fix the equation before evaluating***");
		 }
		 for (int i=0; i<postfix_size; i++) {
			 if (Operator.isOp(postfixEq[i])) {
				 Operator curOp = Operator.getOp(postfixEq[i]);
				 if (curOp.getNumOperand() == 2) {
					 //we have a binary operator. need to pop two operands
					 try {
						 double a = (Double)operandStack.pop(), b = (Double)operandStack.pop();
						 Double result = ((Binop)curOp).compute(b, a);
						 operandStack.push(result);
					 }
					 catch (NullPointerException e) {
						 isValid = false;
						 return null;
					 }
					 catch (Exception e) {
						 System.out.println("Some error occurred");
						 System.out.println(e);
					 }
				 }
				 if (curOp.getNumOperand() == 1) {
					 //unary operator, only need one operand
					 try {
						 double a = (Double)operandStack.pop();
						 Double result = ((UnaryOp)curOp).compute(a);
						 operandStack.push(result);
					 }
					 catch (NullPointerException e) {
						 printNullPtrError(e);
						 isValid = false;
						 return null;
					 }
					 catch (Exception e) {
						 printUnknownError(e);
					 }
				 }
			 }
			 /*
			  * Assuming the expression is valid, 
			  * if current token is not an operator, 
			  * it must be an operand
			  */
			 else {
				 try {
					 operandStack.push(Double.parseDouble(postfixEq[i]));
				 }
				 catch (NumberFormatException e) {
					 printNumFormatError(e, postfixEq[i]);
					 isValid = false;
					 return null;
				 }
				 catch (Exception e) {
					 printUnknownError(e);
				 }
			 }
		 }
		 
		 //try block to check if evaluation successful (equation valid)
		 //if evaluation is successful, we should end up with one (non-null) double number in stack
		 try {
			 double a = (Double)(operandStack.pop());
			 isValid = true;
			 return a;
		 }
		 catch (NullPointerException e) {
			 //null pointer, so we don't have a number result. Likely that some previous step went wrong
			 printNullPtrError(e);
			 isValid = false;
		 }
		 catch (Exception e) {
			 printUnknownError(e);
		 }
		 return null;
	 }
	 
	 //Convert raw input string to tokenized array of operands, parenthesis and operators
	 //Code works, though there could be some redundancy involved in parts about recognizing letters and digits
	 private void tokenize() {
		 String rawString = inputEq.replaceAll("\\s", "");
		 tokenizedEq = new String[100];
		 tokenized_size = 0;
		 
		 String cur = ""; //represents current token
		 for (int i=0; i < rawString.length(); i++) {
			 if (rawString.charAt(i) == '(' || rawString.charAt(i) == ')') {
				 if (!cur.equals("")) {
					 tokenizedEq[tokenized_size] = cur;
					 tokenized_size++;
					 cur = "";
				 }
				 tokenizedEq[tokenized_size] = "" + rawString.charAt(i);
				 tokenized_size++;
			 }
			 else if (rawString.charAt(i) == '-') {
				 //determine is negative is negation operator or subtraction operator
				 //negation operator can only occur after '(' or at the start of the expression
				 if (i== 0 || rawString.charAt(i-1) == '(') {
					 //negation operator; we represent all negative numbers as negations of positive numbers for convenience sake
					 tokenizedEq[tokenized_size] = Operator.NEG.getRepStr();
					 tokenized_size++;
				 }
				 else {
					 //put subtraction operator
					 tokenizedEq[tokenized_size] = Operator.SUB.getRepStr();
					 tokenized_size++;
				 }
			 }
			 //if operator is +, *, or /
			 else if ("+*/".indexOf("" + rawString.charAt(i)) != -1) {
				 if (!cur.equals("")) {
					 tokenizedEq[tokenized_size] = cur;
					 tokenized_size++;
					 cur = "";
				 }
				 tokenizedEq[tokenized_size] = "" + rawString.charAt(i);
				 tokenized_size++;
			 }
			 else if (Character.isLetter(rawString.charAt(i))) {
				 //if we're on a run of characters (i.e. cur != ""), and if the current run of characters are letters
				 if (!cur.equals("") && Character.isLetter(cur.charAt(0))) {
					 //that means we're on a run of letters;
					 cur = cur + rawString.charAt(i);
				 }
				 else if (cur.equals("")) {
					 //that means we finished a run of characters, or we're at the start, or we just put in an operator
					 cur = "" + rawString.charAt(i);
				 }
				 else {
					 //we were on a run of numbers
					 //finish the run
					 tokenizedEq[tokenized_size] = cur;
					 tokenized_size++;
					 cur = "";
					 //start new run of letters
					 cur = "" + rawString.charAt(i);
				 }
			 }
			 else if (Character.isDigit(rawString.charAt(i)) || rawString.charAt(i) == '.') {
				 //we have a character for a positive 
				 //check if we're on a run of number characters
				 if (!cur.equals("") && (Character.isDigit(cur.charAt(0)) || cur.charAt(0) == '.')) {
					 //that means we're on a run of numbers
					 cur = cur + rawString.charAt(i);
				 }
				 else if (cur.equals("")) {
					 //that means we finished a run of characters, or we're at the start, or we just put in an operator
					 cur = "" + rawString.charAt(i);
				 }
				 else {
					 //we were on a run of letters
					 //finish the run
					 tokenizedEq[tokenized_size] = cur;
					 tokenized_size++;
					 cur = "";
					 //start new run of letters
					 cur = "" + rawString.charAt(i);
				 }
			 }
		 }
		 
		 if (!cur.equals("")) {
			 //we have one more number we need to add
			 tokenizedEq[tokenized_size] = cur;
			 tokenized_size++;
		 }
	 }
	 
	 /**
	  * Convert Raw Expression to postfix so it can be evaluated
	  * Raw Expression in array tokenizedEq comes as tokens of operands and operators
	  */
	 private void toPostfix() {
		 postfixEq = new String[100]; //This is probably plenty of space
		 postfix_size = 0;
		 
		 //create stack for conversion
		 Stack opStack = new Stack();
		 for (int i=0; i<tokenized_size; i++) {
			 if (Operator.getOp(tokenizedEq[i]) != null) {
				 //that means the token is a valid operator
				 //Note: all unary operators have the highest precedence
				 
				 //while top operator in stack has higher or equal precedence, add it to expression
				 while(!opStack.isEmpty() && Operator.getOp(tokenizedEq[i]).compareTo(((Operator)opStack.peek())) <= 0) {
					 postfixEq[postfix_size] = ((Operator)opStack.pop()).getRepStr();
					 postfix_size++;
				 }
				 opStack.push(Operator.getOp(tokenizedEq[i]));
			 }
			 else if (tokenizedEq[i].equals("(")) {
				 opStack.push(Operator.OPEN);
			 }
			 else if (tokenizedEq[i].equals(")")) {
				 while (!opStack.isEmpty() && !((Operator)opStack.peek()).getRepStr().equals("(") ) {
					 //while not encountering open parentheses, pop operators
					 postfixEq[postfix_size] = ((Operator)opStack.pop()).getRepStr();
					 postfix_size++;
				 }
				 
				 //second clause of or is redundant; if opStack is not empty, then the top element must be "("; clause there only fo
				 if (opStack.isEmpty() || !((Operator)opStack.peek()).getRepStr().equals("(")) {
					 isValid = false;
					 System.out.println("***\nError! Invalid Expression: too many closed parentheses\n***");
				 }
				 else {
					 opStack.pop();
				 }
			 }
			 else {
				 try {
					 Double.parseDouble(tokenizedEq[i]);
				 }
				 catch (NumberFormatException e) {
					 printNumFormatError(e, tokenizedEq[i]);
					 isValid = false;
				 }
				 catch (Exception e) {
					 printUnknownError(e);
				 }
				 postfixEq[postfix_size] = tokenizedEq[i];
				 postfix_size++;
			 }
		 }
		 
		 //insert all remaining operators
		 while (!opStack.isEmpty()) {
			 if (((Operator)opStack.peek()).getRepStr().equals("(")) {
				 isValid = false;
				 System.out.println("***\nError! Invalid Expression: too many open brackets.\n***");
			 }
			 else {
				 postfixEq[postfix_size] = ((Operator)opStack.pop()).getRepStr();
				 postfix_size++;
			 }
		 }
	 }
	 
	 public void setExpression(String eq) {
		 inputEq = eq;
		 tokenizedEq = null;
		 postfixEq = null;
		 postfix_size = 0;
		 tokenized_size = 0;
		 isValid = null;
		 tokenize();
		 toPostfix();
	 }
	 
	 public String getInputEq() {
		 return this.inputEq;
	 }
	 
	 public void printPostfix() {
		 System.out.println();
		 for (int i=0; i<this.postfix_size; i++) {
			 System.out.print(this.postfixEq[i] + " ");
		 }
		 System.out.println();
	 }
	 
	 public void printTokenized() {
		 System.out.println();
		 for (int i=0; i<this.tokenized_size; i++) {
			 System.out.print(this.tokenizedEq[i] + " ");
		 }
		 System.out.println();
	 }
	 
	 /*
	  * Used to print possible errors due to bad user input
	  */
	 private void printNumFormatError(NumberFormatException e, String s) {
		 System.out.println("***");
		 System.out.println("Error in expression \"" + identifier + "\"");
		 System.out.println("Invalid Expression: unrecognized operand/operator \"" + s + "\"");
		 System.out.println("System error message:" + e);
		 System.out.println("***");
	 }
	 
	 private void printNullPtrError(NullPointerException e) {
		 System.out.println("***");
		 System.out.println("Error in expression \"" + identifier + "\"");
		 System.out.println("Invalid Expression: Missing Operand.");
		 System.out.println("System error message:" + e);
		 System.out.println("***");
	 }
	 
	 private void printUnknownError (Exception e) {
		 System.out.println("***");
		 System.out.println("Error in expression \"" + identifier + "\"");
		 System.out.println("Some unforeseen error occurred.");
		 System.out.println("System error message:" + e);
		 System.out.println("***");
	 }
}
