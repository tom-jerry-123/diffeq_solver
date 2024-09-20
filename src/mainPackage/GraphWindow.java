package mainPackage;
/*
 * Graph Window Code
 */

import javax.swing.*;
import javax.swing.border.*;

import diffEqPlotter.Graph;

import java.awt.event.*;
import java.awt.*;

import java.util.HashMap;

/*
 * Code for Graph Window
 */

public class GraphWindow extends MainFrameWindow {
	
	//constants
	private static final int PADX = 10, PADY = 10; //For padding
	
	//graph panel
	private Graph graph;
	
	GraphWindow() {
		super();
		graph = new Graph();
	}
	
	GraphWindow(int x, int y) {
		super(x, y);
		graph = new Graph();
	}
	
	/*
	 * Initialize function implemented in parent class MainFrameWindow,
	 * So no need to implement again
	 */
	
	protected void printOpeningMessage() {
		System.out.println("Opened Graph Window");
	}
	protected void printClosingMessage() {
		System.out.println("Graph Window Closed");
	}
	
	/*
	 * Set up subpanels below
	 */
	
	protected void setUpNavBar() {
		navBar.setLayout(new GridBagLayout());
		
		//Declare components
		JLabel title = new JLabel("Graphing Tool");
		JButton jbtVect = new JButton("<- Vector Field");
		JButton jbtMenu = new JButton("Main Menu ->");
		
		//Create constraints
		GridBagConstraints title_cstr = new GridBagConstraints();
		GridBagConstraints jbtVect_cstr = new GridBagConstraints();
		GridBagConstraints jbtMenu_cstr = new GridBagConstraints();
		
		title_cstr.gridx = 1;
		title_cstr.gridy = 0;
		title_cstr.weightx = 0.33;
		title_cstr.ipadx = PADX;
		title_cstr.ipady = PADY;
		
		jbtVect_cstr.gridx = 0;
		jbtVect_cstr.gridy = 0;
		jbtVect_cstr.anchor = GridBagConstraints.LINE_START;
		jbtVect_cstr.weightx = 0.33;
		jbtVect_cstr.ipadx = PADX;
		jbtVect_cstr.ipady = PADY;
		
		jbtMenu_cstr.gridx = 2;
		jbtMenu_cstr.gridy = 0;
		jbtMenu_cstr.weightx = 0.33;
		jbtMenu_cstr.anchor = GridBagConstraints.LINE_END;
		jbtMenu_cstr.ipadx = PADX;
		jbtMenu_cstr.ipady = PADY;
		
		//Add components
		navBar.add(title, title_cstr);
		navBar.add(jbtVect, jbtVect_cstr);
		navBar.add(jbtMenu, jbtMenu_cstr);
		
		/*
		 * register listeners
		 */
		jbtVect.addActionListener(new VectListener());
		jbtMenu.addActionListener(new MainMenuListener());
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
	
	/*
	 * Complete this
	 */
	
	protected void setUpPlotBar() {
		plotBar.setLayout(new BorderLayout());
		this.graph.repaint();
		plotBar.add(this.graph, BorderLayout.CENTER);
	}
	
	/*
	 * Listener implementation section
	 */
	
	class VectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Vector Field Window Button Clicked");
			close();
			Main.getVectFieldPage().run();
			System.out.println("Vector Field Window Opened.");
		}
	}
	
	class MainMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Main Menu button clicked");
			close();
			Main.getMainPage().run();
		}
	}
	
	class AnimateListener implements ActionListener {
		//Fill out this section once animate method is ready
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class SettingsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Graph Settings Button Clicked.");
			Main.getGraphSettingsPage().run();
		}
	}
	
	class UpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			graph.updateInput(getCache());
			graph.updateSettings(Main.getGraphSettingsPage().getCache());
			graph.repaint();
		}
	}
}
