package ru.dobrochan.dungeon.ui.events;

import ru.dobrochan.dungeon.ui.primitives.Point;

public class MouseButtonEventArgs extends EventArgs {
	int button;
	Point location;
	
	public MouseButtonEventArgs(int button, Point location) {
		this.location = location;
		this.button = button;
	}
	
	public MouseButtonEventArgs(int button, int x, int y) {
		this(button, new Point(x, y));
	}

	public int getButton() {
		return button;
	}

	public Point getLocation() {
		return location;
	}		
}
