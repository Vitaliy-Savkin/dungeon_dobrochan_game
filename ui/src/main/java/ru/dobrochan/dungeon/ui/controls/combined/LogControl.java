package ru.dobrochan.dungeon.ui.controls.combined;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.ui.controls.AbstractControl;
import ru.dobrochan.dungeon.ui.controls.ControlContainer;
import ru.dobrochan.dungeon.ui.controls.Picture;
import ru.dobrochan.dungeon.ui.controls.TextOutput;
import ru.dobrochan.dungeon.ui.events.MouseClickedAction;
import ru.dobrochan.dungeon.ui.events.MouseClickedEventArgs;
import ru.dobrochan.dungeon.ui.primitives.Size;

public class LogControl extends ControlContainer {

	Picture minBackground;
	Picture maxBackground;
	Picture arrowUp;
	Picture arrowDown;
	Picture minimize;
	Picture maximize;
	Font font;
	TextOutput output;
	
	ArrayList<String> messages = new ArrayList<String>();
	int startDisplayIndex = 0;
	int maxLinesCountMinimized;
	int maxLinesCountMaximized;
	int maxLinesCount;
	
	int textAreaHeightMaximized = 220;	
	int textAreaHeightMinimized = 65;	
	
	public LogControl(GUIContext container, Font font) {
		super(container);
		
		this.font = font;
		output = new TextOutput(container, font, "");
		maxLinesCountMinimized = textAreaHeightMinimized / font.getLineHeight();
		maxLinesCountMaximized = textAreaHeightMaximized / font.getLineHeight();
		
		Image arrowImage = ResourceManager.getInstance().getImage("LOG_ARROW");		
		arrowUp = new Picture(container, arrowImage);		
		arrowDown = new Picture(container, arrowImage.getFlippedCopy(false, true));
		
		arrowUp.onMouseClickedAdd(new MouseClickedAction(){
			@Override
			public void execute(AbstractControl sender, MouseClickedEventArgs e) {				
				LogControl log = ((LogControl)sender.getParent());
				if (log.startDisplayIndex > 0)
				{
					log.startDisplayIndex--;
					refreshOutput();
				}
			}			
		});
		
		arrowDown.onMouseClickedAdd(new MouseClickedAction(){
			@Override
			public void execute(AbstractControl sender, MouseClickedEventArgs e) {					
				LogControl log = ((LogControl)sender.getParent());
				if (messages.size() > maxLinesCount &&
					log.startDisplayIndex < messages.size() - maxLinesCount)
				{
					log.startDisplayIndex++;
					refreshOutput();
				}
			}			
		});		
		
		minBackground = new Picture(container, 
				ResourceManager.getInstance().getImage("LOG_BACKGROUND_MIN"));
		maxBackground = new Picture(container, 
				ResourceManager.getInstance().getImage("LOG_BACKGROUND_MAX"));
		
		minimize = new Picture(container, 
				ResourceManager.getInstance().getImage("LOG_BUTTON_MINIMIZE"));
		maximize = new Picture(container, 
				ResourceManager.getInstance().getImage("LOG_BUTTON_MAXIMIZE"));
		
		minimize.onMouseClickedAdd(new MouseClickedAction(){
			@Override
			public void execute(AbstractControl sender, MouseClickedEventArgs e) {
				((LogControl)sender.getParent()).setState(false);				
			}			
		});
		
		maximize.onMouseClickedAdd(new MouseClickedAction(){
			@Override
			public void execute(AbstractControl sender, MouseClickedEventArgs e) {
				((LogControl)sender.getParent()).setState(true);				
			}			
		});
		
		this.addChild(minBackground);
		this.addChild(maxBackground);
		this.addChild(maximize, new Size(428, 64));
		this.addChild(minimize, new Size(428, 220));
		this.addChild(arrowUp, new Size(410, 15));
		this.addChild(arrowDown, new Size(410, 40));
		this.addChild(output, new Size(12, 12));
		
		setState(false);
	}
	
	void setState(boolean maximized){
		maxBackground.setVisible(maximized);
		maxBackground.setEnabled(maximized);
		minimize.setVisible(maximized);
		minimize.setEnabled(maximized);
		
		minBackground.setVisible(!maximized);
		minBackground.setEnabled(!maximized);
		maximize.setVisible(!maximized);
		maximize.setEnabled(!maximized);
		
		maxLinesCount = maximized ? maxLinesCountMaximized : maxLinesCountMinimized;
		startDisplayIndex = Math.max(0, messages.size() - maxLinesCount);
		refreshOutput();
	}
	
	private void refreshOutput(){
		int maxIndex = Math.min(messages.size(), startDisplayIndex + maxLinesCount);
		String result = "";
		for(int i = startDisplayIndex; i < maxIndex; i++)
			result += messages.get(i) + "\n";
		output.setText(result);
	}
	
	private Collection<String> SplitMessage(String message){
		// TODO: split too long lines
		return Arrays.asList(message.split("\n"));
	}
	
	public void addMessage(String message)
	{
		Collection<String> splitted = SplitMessage(message);
		if (messages.size() == maxLinesCount + startDisplayIndex)
			startDisplayIndex += splitted.size();
		messages.addAll(splitted);
		refreshOutput();
	}
}
