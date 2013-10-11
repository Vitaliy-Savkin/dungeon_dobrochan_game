package ru.dobrochan.dungeon.ui.controls;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import ru.dobrochan.dungeon.ui.primitives.Size;

public class Picture extends AbstractControl {

	Image normalImage, disabledImage, hoveredImage, pressedImage;	
	
	public Picture(GUIContext container, Image image) {
		super(container);
		normalImage = disabledImage = hoveredImage = pressedImage = image;
	}

	//Null values alowed if not used
	public void setBehaviourImages(Image disabled, Image hovered, Image pressed)
	{
		if (disabled != null)
			disabledImage = disabled;
		if (hovered != null)
			hoveredImage = hovered;
		if (pressed != null)
			pressedImage = pressed;
	}
	
	Image getCurrentImage()
	{
		if (disabled)
			return disabledImage;
		if (pressed)
			return pressedImage;
		if (hovered)
			return hoveredImage;
		return normalImage;
	}

	@Override
	public void render(GUIContext container, Graphics g) throws SlickException
	{
		if (visible)
			g.drawImage(getCurrentImage(), getX(), getY());		
	}

	@Override
	public int getHeight() {
		return getCurrentImage().getHeight();
	}

	@Override
	public int getWidth() {
		return getCurrentImage().getWidth();
	}

	@Override
	public Size getSize() {
		return new Size(getWidth(), getHeight());
	}

}
