package mainPackage;


/*
 * Abstract class MainFrameWindow, subclass of abstract class Window
 * Used for common code in Vector Field and Graph windows
 * Exception: Menu class (for main menu / starting page) direct child of Window
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.HashMap;

public abstract class MainFrameWindow extends Window {
	protected JPanel inputBar, navBar, plotBar;
	//maps each jtextfield to its string identifier I know were to put input from each textfield
	private HashMap<JTextField, String> textfieldMap;
	
	//Other classes will access user input for diff eq from inputCache. Acts like a buffer
	private static HashMap<String, String> inputCache;
	
	MainFrameWindow() {
		super();
	}
	
	MainFrameWindow(int x, int y) {
		super(x, y);
		//declare subpanels
		inputBar = new JPanel();
		navBar = new JPanel();
		plotBar = new JPanel();
		textfieldMap = new HashMap<JTextField, String>();
		inputCache = new HashMap<String, String>();
	}
	
	public void initialize() {
		//add subPanels
		panel.setLayout(new BorderLayout());
		
		panel.add(navBar, BorderLayout.NORTH);
		panel.add(inputBar, BorderLayout.WEST);
		panel.add(plotBar, BorderLayout.CENTER);
		
		//add borders
		navBar.setBorder(BorderFactory.createLineBorder(Color.black));
		inputBar.setBorder(BorderFactory.createLineBorder(Color.black));
		plotBar.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Call functions to set up subpanels
		setUpNavBar();
		setUpInputBar();
		setUpPlotBar();
	}
	
	//Relevant set-up functions. To be implemented by child classes
	protected abstract void setUpNavBar();
	protected abstract void setUpInputBar();
	protected abstract void setUpPlotBar();
	
	/*
	 * This Method does the heavy lifting when setting up the input bar. 
	 * Call method below to set up all the input bar components
	 * Then, take the buttons set-up and add relevant action listeners
	 */
	protected HashMap <String, JButton> addInputBarComponents() {
		HashMap <String, JButton> buttonMap = new HashMap <String, JButton>();
		
		inputBar.setLayout(new GridBagLayout());
		inputBar.setMaximumSize(new Dimension((int)(0.25*this.width), this.height));
		
		//initialize input cache to default null values; each key corresponds to a text field.
		inputCache.put("A_2_input", "");
		inputCache.put("A_1_input", "");
		inputCache.put("A_0_input", "");
		inputCache.put("B_input", "");
		inputCache.put("v_0_input", "");
		inputCache.put("x_0_input", "");
		
		//Declare relevant components
		//row 1
		JLabel diffEq = new JLabel("Differential Equation:");
		//row 2
		JLabel A_2_label = new JLabel("A_2 (x) = ");
		JTextField A_2_input = new JTextField(10);
		textfieldMap.put(A_2_input, "A_2_input");
		//row 3
		JLabel A_1_label = new JLabel("A_1 (x) = ");
		JTextField A_1_input = new JTextField(10);
		textfieldMap.put(A_1_input, "A_1_input");
		//row 4
		JLabel A_0_label = new JLabel("A_0 (x) = ");
		JTextField A_0_input = new JTextField(10);
		textfieldMap.put(A_0_input, "A_0_input");
		//row 5
		JLabel B_label = new JLabel("B (x) = ");
		JTextField B_input = new JTextField(10);
		textfieldMap.put(B_input, "B_input");
		//row 6
		JLabel initCond = new JLabel("Initial Conditions:");
		//row 7
		JLabel v_init = new JLabel("x\'(0) = ");
		JTextField v_0_input = new JTextField(5); //input field for initial value of dx/dt
		textfieldMap.put(v_0_input, "v_0_input");
		//row 8
		JLabel x_init = new JLabel("x(0) = ");
		JTextField x_0_input = new JTextField(5); //input field for initial value of x
		textfieldMap.put(x_0_input, "x_0_input");
		//rows 9
		JButton jbtUpdate = new JButton("Update");
		JButton jbtAnimate = new JButton("Animate");
		//row 10
		JButton jbtSettings = new JButton("Settings"); //settings for window, zoom, field size
		
		//declare relevant constraints
		GridBagConstraints cstr = new GridBagConstraints();
		
		//add components
		
		cstr.anchor = GridBagConstraints.LINE_START;
		cstr.weightx = 0.5;
		cstr.weighty = 0.1;
		
		//Row 1
		cstr.gridx = 0;
		cstr.gridy = 0;
		cstr.gridwidth = 2;
		inputBar.add(diffEq, cstr);
		
		//Row 2
		cstr.gridwidth = 1;
		
		cstr.gridx = 0;
		cstr.gridy = 1;
		inputBar.add(A_2_label, cstr);
		
		cstr.gridx = 1;
		cstr.gridy = 1;
		inputBar.add(A_2_input, cstr);
		
		//Row 3
		cstr.gridwidth = 1;
		cstr.gridy = 2;
		
		cstr.gridx = 0;
		inputBar.add(A_1_label, cstr);
		
		cstr.gridx = 1;
		inputBar.add(A_1_input, cstr);
		
		//Row 4
		cstr.gridwidth = 1;
		cstr.gridy = 3;
		
		cstr.gridx = 0;
		inputBar.add(A_0_label, cstr);
		
		cstr.gridx = 1;
		inputBar.add(A_0_input, cstr);
	
		//Row 5
		cstr.gridwidth = 1;
		cstr.gridy = 4;
		
		cstr.gridx = 0;
		inputBar.add(B_label, cstr);
		
		cstr.gridx = 1;
		inputBar.add(B_input, cstr);
				
		
		//there may be a display field for the input equation here
		//row 6
		cstr.gridwidth = 2;
		cstr.gridx = 0;
		cstr.gridy = 5;
		inputBar.add(initCond, cstr);
		
		//Row 7
		cstr.gridwidth = 1;
		cstr.gridy = 6;
		
		cstr.gridx = 0;
		inputBar.add(v_init, cstr);
		
		cstr.gridx = 1;
		inputBar.add(v_0_input, cstr);
		
		//Row 8
		cstr.gridwidth = 1;
		cstr.gridy = 7;
		
		cstr.gridx = 0;
		inputBar.add(x_init, cstr);
		
		cstr.gridx = 1;
		inputBar.add(x_0_input, cstr);
		
		//Row 9
		cstr.gridwidth = 1;
		cstr.gridy = 8;
		
		cstr.gridx = 0;
		inputBar.add(jbtUpdate, cstr);
		
		cstr.gridx = 1;
		inputBar.add(jbtAnimate, cstr);
		
		//Row 10
		cstr.gridwidth = 2;
		cstr.gridy = 9;
		cstr.gridx = 0;
		inputBar.add(jbtSettings, cstr);
		
		/*
		 * Add Textfield action listeners
		 */
		A_2_input.addActionListener(new textfieldListener());
		A_1_input.addActionListener(new textfieldListener());
		A_0_input.addActionListener(new textfieldListener());
		B_input.addActionListener(new textfieldListener());
		v_0_input.addActionListener(new textfieldListener());
		x_0_input.addActionListener(new textfieldListener());
		
		buttonMap.put("Update", jbtUpdate);
		buttonMap.put("Animate", jbtAnimate);
		buttonMap.put("Settings", jbtSettings);
		
		//Pass this to child class so child class can add appropriate action listeners
		return buttonMap;
	}
	
	//we need to allow other classes access to the cache
	public HashMap<String, String> getCache() {
		return inputCache;
	}
	
	/*
	 * Textfield listener will work in the same way for both the graph window and vector field window
	 */
	class textfieldListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//check if source is textfield; though this should always be the case as this listener should only be added to textfields
			if (textfieldMap.get(e.getSource()) != null) {
				inputCache.put(textfieldMap.get(e.getSource()), ((JTextField)e.getSource()).getText());
				System.out.println("Successfully put \"" + ((JTextField)e.getSource()).getText() + "\" under \"" + textfieldMap.get(e.getSource()) + "\".");
			}
		}
	}
}
