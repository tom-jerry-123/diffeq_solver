package mainPackage;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import diffEqPlotter.Graph;

/*
 * Graph Settings Window
 */

public class GraphSettings extends SideFrameWindow {
	
	//for the settings cache
	private HashMap<JTextField, String> textfieldMap;
	private HashMap<String, String> settingsCache;
	
	//Turns out I don't need a cache to store graph button input
	private HashMap<String, ToggleButton> graphBtMap;
	
	GraphSettings() {
		super();
		textfieldMap = new HashMap<JTextField, String> ();
		settingsCache = new HashMap<String, String> ();
		graphBtMap = new HashMap<String, ToggleButton>();
	}
	
	GraphSettings(int x, int  y) {
		super(x, y);
		textfieldMap = new HashMap<JTextField, String> ();
		settingsCache = new HashMap<String, String> ();
		graphBtMap = new HashMap<String, ToggleButton>();
	}
	
	GraphSettings(int x, int y, String name) {
		super(x, y, name);
		textfieldMap = new HashMap<JTextField, String> ();
		settingsCache = new HashMap<String, String> ();
		graphBtMap = new HashMap<String, ToggleButton>();
	}
	
	public void initialize() {
		panel.setLayout(new GridBagLayout());
		
		JLabel title = new JLabel("Graph Window Settings");
		JLabel x_min_label = new JLabel("X_MIN = "); //for the smallest x coordinate
		JTextField x_min_input = new JTextField(5);
		JLabel x_max_label = new JLabel("X_MAX = "); //for the largest x coordinate
		JTextField x_max_input = new JTextField(5);
		JLabel t_min_label = new JLabel("T_MIN = "); //for the smallest y coordinate
		JLabel t_min_value = new JLabel("0"); // t_min is fixed to be t = 0
		JLabel t_max_label = new JLabel("T_MAX = "); //for the largest y coordinate
		JTextField t_max_input = new JTextField(5);
		JLabel func_label = new JLabel("Graph:"); //for the number of columns of vector arrows
		ToggleButton jbtX = new ToggleButton("X", true);
		ToggleButton jbtX_prime = new ToggleButton("X\'"); //number of rows of vector arrows
		ToggleButton jbtX_dblPrime = new ToggleButton("X\"");
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
		panel.add(t_min_label, cstr);
		
		cstr.gridx = 1;
		panel.add(t_min_value, cstr);
		
		cstr.gridx = 2;
		panel.add(t_max_label, cstr);
		
		cstr.gridx = 3;
		panel.add(t_max_input, cstr);
		
		//Fourth Row
		cstr.gridy = 3;
		
		cstr.gridx = 0;
		panel.add(func_label, cstr);
		
		cstr.gridx = 1;
		panel.add(jbtX, cstr);
		
		cstr.gridx = 2;
		panel.add(jbtX_prime, cstr);
		
		cstr.gridx = 3;
		panel.add(jbtX_dblPrime, cstr);
		
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
		
		this.graphBtMap.put("X", jbtX);
		this.graphBtMap.put("X\'", jbtX_prime);
		this.graphBtMap.put("X\"", jbtX_dblPrime);
		
		Graph.setFuncToGraph(true, false, false);
		
		jbtX.addActionListener(new GraphButtonListener());
		jbtX_prime.addActionListener(new GraphButtonListener());
		jbtX_dblPrime.addActionListener(new GraphButtonListener());
		
		this.textfieldMap.put(t_max_input, "t_max");
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
	
	private void updateFuncToGraph() {
		boolean zero, first, second;
		try {
			zero = this.graphBtMap.get(Graph.FUNC).getState();
			first = this.graphBtMap.get(Graph.FIRST).getState();
			second = this.graphBtMap.get(Graph.SEC).getState();
			Graph.setFuncToGraph(zero, first, second);
		}
		catch (Exception e) {
			System.out.println("Something went wrong with updating input from graph buttons.");
		}
	}
	
	private void clearTextfields() {
		for (JTextField key : textfieldMap.keySet()) {
			key.setText(null);
		}
		System.out.println("Cleared textfields in Graph Settings window.");
	}
	
	//reset buttons so that their state corresponds to state of graph toggle variables in graph object
	private void clearButtons() {
		for (String key : graphBtMap.keySet()) {
			try {
				boolean state = Graph.getFuncToGraph(key);
				graphBtMap.get(key).setState(state);
			}
			catch (Exception e) {
				System.out.println("Something went wrong with clearing button " + key);
			}
		}
	}
	
	//This is so other classes can access the cache
	
	protected HashMap <String, String> getCache() {
		return this.settingsCache;
	}
	
	protected void printOpeningMessage() {
		System.out.println("Opened Graph Settings.");
	}
	
	protected void printClosingMessage() {
		System.out.println("Closed Graph Settings");
	}
	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//saves new settings to cache
			updateCache();
			updateFuncToGraph();
			close();
			printClosingMessage();
		}
	}
	
	class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearTextfields();
			clearButtons();
			close();
			printClosingMessage();
		}
	}
	
	class GraphButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Complete this
			try {
				((ToggleButton)(e.getSource())).toggle();
			}
			catch (Exception exception) {
				System.out.println("***\nSomething went wrong");
				System.out.println(exception);
				System.out.println("***");
			}
		}
	}
}
