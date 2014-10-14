package ru.dobrochan.dungeon.ui.controls.combined;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.ui.controls.ControlContainer;
import ru.dobrochan.dungeon.ui.controls.Picture;
import ru.dobrochan.dungeon.ui.events.MouseClickedAction;
import ru.dobrochan.dungeon.ui.primitives.Size;

public class MainMenu extends ControlContainer {

	ArrayList<Button> buttons = new ArrayList<Button>();
	Image normal = ResourceManager.getInstance().getImage("MENU_BUTTON_NORMAL");
	Image hovered = ResourceManager.getInstance().getImage("MENU_BUTTON_FOCUSED");
	Image pressed = ResourceManager.getInstance().getImage("MENU_BUTTON_PRESSED");
	Image disabled = ResourceManager.getInstance().getImage("MENU_BUTTON_DISABLED");
	Font font;
	Picture logo;
	Picture panel;
	
	public MainMenu(GUIContext container, Font font) {
		super(container);
		this.font = font;
		logo = new Picture(container, ResourceManager.getInstance().getImage("WINDOW_LOGO"));
		panel = new Picture(container, ResourceManager.getInstance().getImage("WINDOW_MAIN_MENU_WINDOW"));
		addChild(panel, new Size(120, 150));
		addChild(logo);
	}
	
	public void addButton(String text, MouseClickedAction action){
		Button newButton = new Button(super.container, normal, text, font);
		newButton.setBehaviourImages(disabled, hovered, pressed);
		newButton.onMouseClickedAdd(action);
		addChild(newButton, new Size(140, 245 + buttons.size() * (normal.getHeight() + 10)));
	}
}
