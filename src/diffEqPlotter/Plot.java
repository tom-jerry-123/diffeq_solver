/*
 * Abstract class for code that plots vector fields, graphs
 */

package diffEqPlotter;

import mainPackage.*;

import java.util.HashMap;

import javax.swing.*;

public class Plot extends JPanel {
	static DifferentialEquation equation = new DifferentialEquation();
	
	/* MainFrameWindow InputCache rules
	 * inputCache.put("A_2_input", "");
		inputCache.put("A_1_input", "");
		inputCache.put("A_0_input", "");
		inputCache.put("B_input", "");
		inputCache.put("v_0_input", "");
		inputCache.put("x_0_input", "");
	 */

	//takes everything in input cache of corresponding window and updates it into differential equation
	public void updateInput(HashMap<String, String> inputCache) {
		equation.setA_0(inputCache.get("A_0_input"));
		equation.setA_1(inputCache.get("A_1_input"));
		equation.setA_2(inputCache.get("A_2_input"));
		equation.setB(inputCache.get("B_input"));
		try {
			equation.setV_0(Double.parseDouble(inputCache.get("v_0_input")));
		}
		catch (Exception e) {
			equation.setV_0(0);
			System.out.println("Failed Attempt to parse input for v_0: invalid input or no input");
		}
		
		try {
			equation.setX_0(Double.parseDouble(inputCache.get("x_0_input")));
		}
		catch (Exception e) {
			equation.setX_0(0);
			System.out.println("Failed Attempt to parse input for x_0: invalid input or no input");
		}
	}
}
