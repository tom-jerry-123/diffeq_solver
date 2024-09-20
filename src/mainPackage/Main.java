package mainPackage;
/*
 * Code for Main program. Runs the application
 */

import java.awt.*;
import javax.swing.*;

public class Main {
	private static JFrame appFrame = new JFrame("Differential Equation Visualization Tool");
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Dimension screenSize = tk.getScreenSize();
	
	//All the pages
	private static Menu mainPage = new Menu(400, 300);
	private static Help helpPage = new Help(500, 375, "Help");
	private static VectorFieldWindow vectFieldPage = new VectorFieldWindow(800, 600);
	private static GraphWindow graphPage = new GraphWindow(800, 600);
	private static VectFieldSettings vectSettingsPage = new VectFieldSettings(500, 375, "Vector Field Window Settings");
	private static GraphSettings graphSettingsPage = new GraphSettings(500, 375, "Graph Window Settings");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createFrame();
		initializeWindows();
		mainPage.run();
	}
	
	//initialize the main appFrame
	private static void createFrame() {
		appFrame.setSize(100, 100);
		center(appFrame);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.setVisible(true);
	}
	
	//initialize panels for all windows
	private static void initializeWindows() {
		mainPage.initialize();
		helpPage.initialize();
		vectFieldPage.initialize();
		graphPage.initialize();
		vectSettingsPage.initialize();
		graphSettingsPage.initialize();
	}
	
	public static void center(JFrame frame) {
		frame.setLocation(screenSize.width/2 - frame.getWidth()/2, screenSize.height/2 - frame.getHeight()/2);
	}
	
	//use this to access main frame in other classes
	public static JFrame getFrame() {
		return appFrame;
	}
	
	//use this to access pages in other classes
	public static Menu getMainPage() {
		return mainPage;
	}
	
	public static Help getHelpPage() {
		return helpPage;
	}
	
	public static VectorFieldWindow getVectFieldPage() {
		return vectFieldPage;
	}
	
	public static GraphWindow getGraphPage() {
		return graphPage;
	}
	
	public static VectFieldSettings getVectSettingsPage() {
		return vectSettingsPage;
	}
	
	public static GraphSettings getGraphSettingsPage() {
		return graphSettingsPage;
	}
	//public static getScreenSize
}