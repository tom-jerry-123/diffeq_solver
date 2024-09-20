/*
 * ToggleButton class
 * - represents buttons with on/off state
 * - used for buttons in graph settings -> buttons that represent which function / derivative to graph
 */
package mainPackage;

import java.awt.*;
import javax.swing.*;

public class ToggleButton extends JButton {
	private String identifier; // String to identify button. Not necessarily the same as the text on button
	
	private boolean state; //state = on / off
	private ButtonTexture onTexture;
	private ButtonTexture offTexture;
	
	ToggleButton() {
		super();
		this.state = false; // off state
		onTexture = new ButtonTexture(new Font("Serif", Font.BOLD, 14), Color.CYAN); //Default on-texture
		offTexture = null;
		identifier = "";
	}
	
	ToggleButton(String s) {
		super(s);
		this.state = false;
		onTexture = new ButtonTexture(new Font("Serif", Font.BOLD, 14), Color.CYAN, s);
		offTexture = null;
		identifier = s;
	}
	
	ToggleButton(String s, boolean state) {
		super(s);
		this.state = state;
		onTexture = new ButtonTexture(new Font("Serif", Font.BOLD, 14), Color.CYAN, s);
		offTexture = null;
		identifier = s;
	}
	
	//internal method to update button texture so that it is reflected in jbt's font, color, etc. instance vars
	private void updateTexture() {
		if (this.state) {
			if (onTexture == null) {
				this.setFont(ButtonTexture.DEFAULT_FONT);
				this.setBackground(ButtonTexture.DEFAULT_BKGRND);
			}
			else {
				this.setFont(onTexture.getFont());
				this.setBackground(onTexture.getBackgroundColor());
			}
		}
		else {
			if (offTexture == null) {
				this.setFont(ButtonTexture.DEFAULT_FONT);
				this.setBackground(ButtonTexture.DEFAULT_BKGRND);
				this.setText(onTexture.getText());
			}
			else {
				this.setFont(offTexture.getFont());
				this.setBackground(offTexture.getBackgroundColor());
				this.setText(offTexture.getText());
			}
		}
	}
	
	public void toggle() {
		this.state = !this.state;
		this.repaint();
		System.out.println("Toggled button " + this.getText() + " to " + this.state);
	}
	
	public void setState(boolean state) {
		this.state = state;
		updateTexture();
	}
	
	public boolean getState() {
		return state;
	}
	
	public void setOnTexture(Font font, Color color) {
		onTexture = new ButtonTexture(font, color);
		updateTexture();
	}
	
	//overloading, second setter method for onTexture
	public void setOnTexture(ButtonTexture onTexture) {
		this.onTexture = onTexture;
		updateTexture();
	}
	
	public ButtonTexture getOnTexture() {
		return this.onTexture;
	}
	
	public void setOffTexture(Font font, Color color) {
		offTexture = new ButtonTexture(font, color);
		updateTexture();
	}
	
	//overloading, second setter method for offTexture with a texture object as parameter
	public void setOffTexture(ButtonTexture offTexture) {
		this.offTexture = offTexture;
		updateTexture();
	}
	
	public ButtonTexture getOffTexture() {
		return this.offTexture;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		updateTexture();
		super.paintComponent(g);
	}
}
