package mainPackage;
/*
 * Main Menu of Application
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class Menu extends Window {
	//declare panel
	private static final int BT_WIDTH = 80, BT_HEIGHT = 40;
	
	Menu() {
		super();
		//default constructor
	}
	
	Menu(int x, int y) {
		super(x, y);
	}
	
	//Abstract method initialize implementation
	public void initialize() {
		panel.setLayout(null);
		
		JButton jbtStart = new JButton("Start");
		JButton jbtHelp = new JButton("Help");
		
		//Set location and size of Jbuttons
		jbtStart.setBounds((int)(0.5 * panel.getWidth()) - BT_WIDTH/2, (int)(0.3 * panel.getHeight()), BT_WIDTH, BT_HEIGHT);
		jbtHelp.setBounds((int)(0.5 * panel.getWidth()) - BT_WIDTH/2, (int)(0.6 * panel.getHeight()), BT_WIDTH, BT_HEIGHT);
		
		//Create title
		JLabel title = new JLabel("Differential Equation Solver");
		title.setFont(new Font("Serif", Font.BOLD, 18));
		//Set location and size of Jlabels
		title.setBounds(panel.getWidth()/2 - (int)(0.6 * title.getPreferredSize().width), (int)(0.1 * panel.getHeight()), (int)(1.2*title.getPreferredSize().width), title.getPreferredSize().height);
		
		//add components
		panel.add(jbtStart);
		panel.add(jbtHelp);
		panel.add(title);
		
		//register listeners
		jbtStart.addActionListener(new StartListener());
		jbtHelp.addActionListener(new HelpListener());
	}
	
	//implementation of abstract method printOpeningMessage
	protected void printOpeningMessage() {
		System.out.println("Main Menu Opened.");
	}
	
	protected void printClosingMessage() {
		System.out.println("Main Menu Closed.");
	}
	
	//run and close implemented in Abstract parent class Window
	
	/*
	 * Inner Listener classes for start and help
	 */
	
	class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Start button clicked");
			close();
			Main.getVectFieldPage().run();
		}
	}
	
	class HelpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Help Button Clicked");
			Main.getHelpPage().run();
		}
	}
}
