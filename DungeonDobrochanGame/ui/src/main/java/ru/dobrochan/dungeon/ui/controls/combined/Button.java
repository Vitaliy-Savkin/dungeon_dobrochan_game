package ru.dobrochan.dungeon.ui.controls.combined;

import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

import ru.dobrochan.dungeon.ui.controls.ControlContainer;
import ru.dobrochan.dungeon.ui.controls.Picture;
import ru.dobrochan.dungeon.ui.controls.TextOutput;
import ru.dobrochan.dungeon.ui.primitives.Size;

public class Button extends ControlContainer {
	
	Picture background;
	TextOutput textOutput;
	
	public Button(GUIContext container, Image normalImage, String text, Font font) {
		super(container);
				
		background = new Picture(container, normalImage);
		textOutput = new TextOutput(container, font, text);	
		
		// Do not change order.
		this.addChild(background);
		Size textOffset = new Size(Math.max(0, (background.getWidth() - textOutput.getWidth()) / 2), 
								   Math.max(0, (background.getHeight() - textOutput.getHeight()) / 2));
		
		this.addChild(textOutput, textOffset);
	}
	
	public void setBehaviourImages(Image disabled, Image hovered, Image pressed){
		background.setBehaviourImages(disabled, hovered, pressed);
	}
}
