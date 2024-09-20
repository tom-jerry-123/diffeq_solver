package mainPackage;
/*
 * Abstract class used for Side Frame Window
 * Windows under this class use their own frame
 * This class is left abstract so it does not need to implement initialize and print message methods
 * which are specific to child classes
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class SideFrameWindow extends Window {
	protected JFrame frame;
	
	//default constructor
	SideFrameWindow() {
		super();
	}
	
	//Side Frame Window constructor with panel sizes as input
	SideFrameWindow(int x, int y) {
		super(x, y);
		frame = new JFrame();
		frame.add(this.panel);
		frame.setSize(x, y);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	//Side Frame Window constructor with panel size and frame name
	SideFrameWindow(int x, int y, String name) {
		super(x, y);
		frame = new JFrame(name);
		frame.add(this.panel);
		frame.setSize(x, y);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	//abstract methods from Window superclass not implemented. Must implement in child class
	
	@Override
	public void run() {
		frame.setVisible(true);
		printOpeningMessage();
	}
	
	@Override
	public void close() {
		frame.setVisible(false);
		printClosingMessage();
	}
}
