package ru.dobrochan.dungeon.ui.controls.combined;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.ui.controls.AbstractControl;
import ru.dobrochan.dungeon.ui.controls.ControlContainer;
import ru.dobrochan.dungeon.ui.controls.Picture;
import ru.dobrochan.dungeon.ui.controls.TextOutput;
import ru.dobrochan.dungeon.ui.events.MouseClickedAction;
import ru.dobrochan.dungeon.ui.events.MouseClickedEventArgs;
import ru.dobrochan.dungeon.ui.primitives.Point;
import ru.dobrochan.dungeon.ui.primitives.Size;

public class CreaturesBar extends ControlContainer {

	ArrayList<Image> creaturesImages = new ArrayList<Image>();
	private int startDisplayIndex = 0;
	private int MAX_DISPLAY_COUNT = 8;	
	Picture background;
	Picture progress;
	TextOutput label;	
	
	public CreaturesBar(GUIContext container, Font labelFont) {
		super(container);
		background = new Picture(container, ResourceManager.getInstance().getImage("CREATURES_BAR_BACKGROUND"));
		background.onMouseClickedAdd(new MouseClickedAction(){
			@Override
			public void execute(AbstractControl sender, MouseClickedEventArgs e) {				
				int x = e.getLocation().getX() - sender.getLocation().getX();
				int y = e.getLocation().getY() - sender.getLocation().getY();
				Point clientPoint = new Point(x, y);
				
				CreaturesBar bar = ((CreaturesBar)sender.getParent());
				//left arrow
				if (Point.inBounds(new Point(70, 70), new Size(30, 30), clientPoint) && 
					bar.startDisplayIndex > 0)
				{
					bar.startDisplayIndex--;
				}
				//right arrow
				else if (Point.inBounds(new Point(655, 70), new Size(30, 30), clientPoint) && 
						bar.creaturesImages.size() > MAX_DISPLAY_COUNT &&
						bar.startDisplayIndex < bar.creaturesImages.size() - MAX_DISPLAY_COUNT)
				{
					bar.startDisplayIndex++;
				}
			}			
		});
		
		// TODO: implement progress
		progress = new Picture(container, ResourceManager.getInstance().getImage("CREATURES_BAR_PROGRESS"));	
		
		label = new TextOutput(container, labelFont, "");
		
		addChild(background);
		addChild(label, new Size(112, 23));
		addChild(progress, new Size(171, 33));
	}
	
	@Override 
	public void render(GUIContext container, Graphics g) throws SlickException {
		if (visible)
		{
			super.render(container, g);
			int imgagesGap = 7;
			int y = getY() + 50;
			int x = getX() + 105 + imgagesGap;
			int maxIndex = Math.min(creaturesImages.size(), startDisplayIndex + MAX_DISPLAY_COUNT);
			for(int i = startDisplayIndex; i < maxIndex; i++)
			{
				g.drawImage(creaturesImages.get(i), x, y);
				x += creaturesImages.get(i).getWidth() + imgagesGap;
			}
		}		
	}
	
	public ArrayList<Image> getCreaturesImages(){
		return creaturesImages;
	}
	
	public void setLabelText(String text){
		label.setText(text);
	}
}
