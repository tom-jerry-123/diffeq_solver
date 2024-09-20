package mainPackage;
/*
 * File for creating vector field window
 */

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.util.HashMap;
import java.awt.*;

import diffEqPlotter.VectorField;

public class VectorFieldWindow extends MainFrameWindow {
	
	//constants
	private static final int PADX = 10, PADY = 10; //For padding
	//vector field
	private VectorField field;
	
	VectorFieldWindow() {
		super();
		field = new VectorField();
	}
	
	VectorFieldWindow(int x, int y) {
		super(x, y);
		field = new VectorField();
	}
	
	/*
	 * Sect 1: Relevant Abstract class implementations
	 */
	
	//Initialize function implemented in MainFrameWindow class
	
	protected void printOpeningMessage() {
		System.out.println("Opened Vector Field Window");
	}
	
	protected void printClosingMessage() {
		System.out.println("Vector Field Window Closed");
	}
	
	/*
	 * Sect 2: Set up subpanels. Only use for initialize function
	 */

	// set-up navBar panel
	protected void setUpNavBar() {
		navBar.setLayout(new GridBagLayout());
		
		//Declare components
		JLabel title = new JLabel("Vector Field Plotter");
		JButton jbtMenu = new JButton("<- Main Menu");
		JButton jbtGraph = new JButton("Graph ->");
		
		//Create constraints
		GridBagConstraints title_cstr = new GridBagConstraints();
		GridBagConstraints jbtMenu_cstr = new GridBagConstraints();
		GridBagConstraints jbtGraph_cstr = new GridBagConstraints();
		
		title_cstr.gridx = 1;
		title_cstr.gridy = 0;
		title_cstr.weightx = 0.33;
		title_cstr.ipadx = PADX;
		title_cstr.ipady = PADY;
		
		jbtMenu_cstr.gridx = 0;
		jbtMenu_cstr.gridy = 0;
		jbtMenu_cstr.weightx = 0.33;
		jbtMenu_cstr.anchor = GridBagConstraints.LINE_START;
		jbtMenu_cstr.ipadx = PADX;
		jbtMenu_cstr.ipady = PADY;
		
		jbtGraph_cstr.gridx = 2;
		jbtGraph_cstr.gridy = 0;
		jbtGraph_cstr.anchor = GridBagConstraints.LINE_END;
		jbtGraph_cstr.weightx = 0.33;
		jbtGraph_cstr.ipadx = PADX;
		jbtGraph_cstr.ipady = PADY;
		
		//Add components
		navBar.add(title, title_cstr);
		navBar.add(jbtMenu, jbtMenu_cstr);
		navBar.add(jbtGraph, jbtGraph_cstr);
		
		//register navBar listeners
		jbtMenu.addActionListener(new MainMenuListener());
		jbtGraph.addActionListener(new GraphListener());
	}
	
	//The code in VectorFieldWindow class for this function is identical, except the action listeners are different.
	protected void setUpInputBar() {
		HashMap <String, JButton> buttonMap;
		buttonMap = super.addInputBarComponents(); //adds all relevant input bar components, and gets JButtons
		
		try {
			buttonMap.get("Animate").addActionListener(new AnimateListener());
			buttonMap.get("Settings").addActionListener(new SettingsListener());
			buttonMap.get("Update").addActionListener(new UpdateListener());
		}
		catch (Exception e) {
			System.out.println("Something else went wrong!");
		}
	}
	
	protected void setUpPlotBar() {
		/*
		 * Test code, change this later; drawing works
		 */
		plotBar.setLayout(new BorderLayout());
		this.field.repaint();
		plotBar.add(this.field, BorderLayout.CENTER);
	}
	
	/*
	 * Sect 3: Listener implementation section
	 */
	
	class MainMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Main Menu button clicked");
			close();
			Main.getMainPage().run();
		}
	}
	
	class GraphListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Graph Window Button Clicked");
			close();
			Main.getGraphPage().run();
		}
	}
	
	class AnimateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class SettingsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Vect Settings Button Clicked.");
			Main.getVectSettingsPage().run();
		}
	}
	
	class UpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Updating vector field.");
			field.updateInput(getCache());
			field.updateSettings(Main.getVectSettingsPage().getCache());
			field.repaint();
		}
	}
}
