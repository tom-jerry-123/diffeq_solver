package mainPackage;
/*
 * Code for Help Menu
 */

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class Help extends SideFrameWindow {
	
	//dimensions of buttons for help
	static final int BT_WIDTH = 80, BT_HEIGHT = 40;
	
	Help() {
		super();
		//default constructor
	}
	
	Help(int x, int y) {
		super(x, y);
	}
	
	Help(int x, int y, String name) {
		super(x, y, name);
	}
	
	//implementation of abstract method initialize
	public void initialize() {
		panel.setLayout(null);
		
		//Create Title
		JLabel title = new JLabel("Help");
		title.setFont(new Font("Serif", Font.BOLD, 18));
		title.setBounds(50, 50, title.getPreferredSize().width, title.getPreferredSize().height);
		
		//Create Buttons
		JButton jbtOK = new JButton("OK");
		jbtOK.setBounds((int)(0.5 * panel.getWidth()) - BT_WIDTH/2, (int)(0.6 * panel.getHeight()), BT_WIDTH, BT_HEIGHT);
		
		panel.add(title);
		panel.add(jbtOK);
		
		//Register Listener
		jbtOK.addActionListener(new OKListener());
	}
	
	//implementation of abstract method
	protected void printOpeningMessage() {
		System.out.println("Opened Help Menu");
	}
	
	//implementation of abstract method
	protected void printClosingMessage() {
		System.out.println("Closed Help Menu");
	}
	
	class OKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			close();
		}
	}
}
