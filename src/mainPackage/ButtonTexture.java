/*
 * Button Texture class
 * - This is to deal with the font, background, border, etc. of buttons
 * - I'll have one texture for on state of toggle buttons, and another for off state
 */

package mainPackage;

import java.awt.*;

public class ButtonTexture {
	public static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 14);
	public static final Color DEFAULT_BKGRND = null;
	
	private Font btFont;
	private Color bkgrnd;
	private String btText;
	
	//use defaults
	ButtonTexture() {
		btFont = new Font("SansSerif", Font.PLAIN, 14);
		bkgrnd = null;
		btText = "";
	}
	
	ButtonTexture(Font font, Color color) {
		btFont = font;
		bkgrnd = color;
		btText = "";
	}
	
	ButtonTexture(Font font, Color color, String s) {
		btFont = font;
		bkgrnd = color;
		btText = s;
	}
	
	public void setFont(Font font) {
		this.btFont = font;
	}
	
	public Font getFont() {
		return this.btFont;
	}
	
	public void setColor(Color color) {
		this.bkgrnd = color;
	}
	
	public Color getBackgroundColor() {
		return this.bkgrnd;
	}
	
	public void setText(String s) {
		this.btText = s;
	}
	
	public String getText() {
		return this.btText;
	}
}
