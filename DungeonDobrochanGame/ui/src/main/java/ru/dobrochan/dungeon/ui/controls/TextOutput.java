package ru.dobrochan.dungeon.ui.controls;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import ru.dobrochan.dungeon.ui.primitives.Size;

public class TextOutput extends AbstractControl {

	String text;	
	Font font;
	Size outputSize;

	public TextOutput(GUIContext container, Font font, String text) {
		super(container);	
		this.font = font;
		setText(text);
	}

	@Override
	public void render(GUIContext container, Graphics g) throws SlickException 
	{
		if (visible)
			g.drawString(text, getX(), getY());
	}

	public Font getFont() {
		return font;
	}	

	public void setFont(Font font) {
		this.font = font;
		outputSize = new Size(font.getWidth(text), font.getHeight(text));
	}	
	
	public String getText() {
		return text; 
	}
	
	public void setText(String text) {
		this.text = text;
		outputSize = new Size(font.getWidth(text), font.getHeight(text));
	}

	@Override
	public int getHeight() {
		return outputSize.getHeight();
	}

	@Override
	public int getWidth() {
		return outputSize.getWidth();
	}	
	
	public void setSize(Size sz){
		outputSize = sz;
	}

	@Override
	public Size getSize() {
		return outputSize;
	}
}
