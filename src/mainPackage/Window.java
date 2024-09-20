package mainPackage;
/*
 * Abstract Class Window
 * Two types of windows: 
 * - main frame windows: windows put on main app frame. Direct child class
 * - side frame windows: windows with their own frame. Settings and help menus get their own frame;
 * side frame windows are child classes of SideFrameWindow extends Window
 */

import javax.swing.JPanel;

public abstract class Window {
	protected JPanel panel;
	protected int width, height;
	
	public Window() {
		//empty (default) constructor
		this.panel = new JPanel();
	}
	
	public Window(int x, int y) {
		this.width = x;
		this.height = y;
		this.panel = new JPanel();
		this.panel.setSize(x, y);
		this.panel.setLocation(0, 0);
	}
	
	//abstract methods
	public abstract void initialize();
	protected abstract void printOpeningMessage();
	protected abstract void printClosingMessage();
	
	public void run() {
		Main.getFrame().add(this.panel);
		Main.getFrame().setSize(this.width, this.height);
		Main.center(Main.getFrame());
		Main.getFrame().repaint();
		printOpeningMessage();
	}
	
	public void close() {
		Main.getFrame().remove(this.panel);
		Main.getFrame().revalidate();
		Main.getFrame().repaint();
		Main.getFrame().setSize(0, 0);
		printClosingMessage();
	}
}
