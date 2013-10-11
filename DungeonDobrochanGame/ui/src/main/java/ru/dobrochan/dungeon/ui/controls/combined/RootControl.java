package ru.dobrochan.dungeon.ui.controls.combined;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

import ru.dobrochan.dungeon.ui.controls.ControlContainer;
import ru.dobrochan.dungeon.ui.controls.Picture;

public class RootControl extends ControlContainer {
	
	Picture background;
	Picture borders;
	
	public RootControl(GUIContext container, Image backgroundImage, Image bordersImage) {
		super(container);
		background = new Picture(container, backgroundImage);
		borders = new Picture(container, bordersImage);
		addChild(background);
		addChild(borders);
	}
}
