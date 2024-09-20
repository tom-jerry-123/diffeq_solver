package mainPackage;
/*
 * Settings window for vector field
 */

import javax.swing.*;

import diffEqPlotter.VectorField;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class VectFieldSettings extends SideFrameWindow {
	private static final double X_WEIGHT = 0.22, Y_WEIGHT = 0.18; //size of grid cells
	
	private HashMap<JTextField, String> textfieldMap;
	private HashMap<String, String> settingsCache;
	
	VectFieldSettings() {
		super();
		textfieldMap = new HashMap<JTextField, String> ();
		settingsCache = new HashMap<String, String> ();
	}
	
	VectFieldSettings(int x, int  y) {
		super(x, y);
		textfieldMap = new HashMap<JTextField, String> ();
		settingsCache = new HashMap<String, String> ();
	}
	
	VectFieldSettings(int x, int y, String name) {
		super(x, y, name);
		textfieldMap = new HashMap<JTextField, String> ();
		settingsCache = new HashMap<String, String> ();
	}
	
	public void initialize() {
		panel.setLayout(new GridBagLayout());
		
		JLabel title = new JLabel("Vector Field Window Settings");
		JLabel x_min_label = new JLabel("X_MIN"); //for the smallest x coordinate
		JTextField x_min_input = new JTextField(5);
		JLabel x_max_label = new JLabel("X_MAX"); //for the largest x coordinate
		JTextField x_max_input = new JTextField(5);
		JLabel y_min_label = new JLabel("Y_MIN"); //for the smallest y coordinate
		JTextField y_min_input = new JTextField(5);
		JLabel y_max_label = new JLabel("Y_MAX"); //for the largest y coordinate
		JTextField y_max_input = new JTextField(5);
		JLabel num_x_label = new JLabel("NUM_X"); //for the number of columns of vector arrows
		JTextField num_x_input = new JTextField(5);
		JLabel num_y_label = new JLabel("NUM_Y"); //number of rows of vector arrows
		JTextField num_y_input = new JTextField(5);
		JButton jbtSave = new JButton("Save Changes");
		JButton jbtCancel = new JButton("Cancel");
		
		//Style setup
		title.setFont(new Font("Serif", Font.BOLD, 18));
		
		//Set up grid bag constraints, then adding element
		GridBagConstraints cstr = new GridBagConstraints();
		//adding title
		cstr.gridx = 0;
		cstr.gridy = 0;
		cstr.gridwidth = 4;
		cstr.weighty = 0.20;
		cstr.weightx = 0.25;
		cstr.insets = new Insets(0, 0, 0, 0);
		panel.add(title, cstr);
		
		//Reset gridwidth to 1;
		cstr.gridwidth = 1;
		
		//Second Row
		cstr.anchor = GridBagConstraints.LINE_START;
		cstr.gridy = 1;
		
		//set up x max
		cstr.gridx = 0;
		panel.add(x_min_label, cstr);
		
		cstr.gridx = 1;
		panel.add(x_min_input, cstr);
		
		cstr.gridx = 2;
		panel.add(x_max_label, cstr);
		
		cstr.gridx = 3;
		panel.add(x_max_input, cstr);
		
		//Third Row
		cstr.gridy = 2;
		
		cstr.gridx = 0;
		panel.add(y_min_label, cstr);
		
		cstr.gridx = 1;
		panel.add(y_min_input, cstr);
		
		cstr.gridx = 2;
		panel.add(y_max_label, cstr);
		
		cstr.gridx = 3;
		panel.add(y_max_input, cstr);
		
		//Fourth Row
		cstr.gridy = 3;
		
		cstr.gridx = 0;
		panel.add(num_x_label, cstr);
		
		cstr.gridx = 1;
		panel.add(num_x_input, cstr);
		
		cstr.gridx = 2;
		panel.add(num_y_label, cstr);
		
		cstr.gridx = 3;
		panel.add(num_y_input, cstr);
		
		//Fifth Row. For the buttons. Each button has grid width = 2
		cstr.gridy = 4;
		cstr.gridwidth = 2;
		cstr.anchor = GridBagConstraints.CENTER;
		
		cstr.gridx = 0;
		panel.add(jbtSave, cstr);
		
		cstr.gridx = 2;
		panel.add(jbtCancel, cstr);
		
		jbtSave.addActionListener(new SaveListener());
		jbtCancel.addActionListener(new CancelListener());
		//Box.createHorizontalStrut(10);
		
		// initialize the textfield map
		this.textfieldMap.put(num_y_input, "num_y");
		this.textfieldMap.put(num_x_input, "num_x");
		this.textfieldMap.put(y_max_input, "y_max");
		this.textfieldMap.put(y_min_input, "y_min");
		this.textfieldMap.put(x_max_input, "x_max");
		this.textfieldMap.put(x_min_input, "x_min");
	}
	
	/*
	 * Regarding the settings cache
	 */
	
	private void updateCache() {
		for (JTextField key : textfieldMap.keySet()) {
			settingsCache.put(textfieldMap.get(key), key.getText());
		}
		System.out.println("New settings input saved to cache.");
	}
	
	private void clearTextfields() {
		for (JTextField key : textfieldMap.keySet()) {
			key.setText(null);
		}
	}
	
	//This is so other classes can access the cache
	
	protected HashMap <String, String> getCache() {
		return this.settingsCache;
	}
	
	/*
	 * Closing messages
	 */
	
	protected void printOpeningMessage() {
		System.out.println("Opened Vector Field Settings.");
	}
	
	protected void printClosingMessage() {
		System.out.println("Closed Vector Field Settings");
	}
	
	/*
	 * Action Listeners
	 */
	
	/* do we need this?
	class TextListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//check if source is textfield; though this should always be the case as this listener should only be added to textfields
			if (textfieldMap.get(e.getSource()) != null) {
				settingsCache.put(textfieldMap.get(e.getSource()), ((JTextField)e.getSource()).getText());
				System.out.println("Successfully put \"" + ((JTextField)e.getSource()).getText() + "\" under \"" + textfieldMap.get(e.getSource()) + "\".");
			}
		}
	}
	*/
	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//saves new settings to cache
			updateCache();
			close();
			printClosingMessage();
		}
	}
	
	class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearTextfields();
			close();
			printClosingMessage();
		}
	}
}
