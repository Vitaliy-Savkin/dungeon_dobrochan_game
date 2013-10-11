package ru.dobrochan.dungeon.ui.events;

import ru.dobrochan.dungeon.ui.primitives.Point;

public class MouseClickedEventArgs extends MouseButtonEventArgs {
	int clickCount;
	
	public MouseClickedEventArgs(int button, Point location, int clickCount) {
		super(button, location);
		this.clickCount = clickCount;
	}
	
	public MouseClickedEventArgs(int button, int x, int y, int clickCount) {
		this(button, new Point(x, y), clickCount);
	}
	
	int getClickCount(){ return clickCount; }
}
