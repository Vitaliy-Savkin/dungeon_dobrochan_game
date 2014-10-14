package ru.dobrochan.dungeon.ui.events;

import ru.dobrochan.dungeon.ui.primitives.Point;

public class MouseMovedEventArgs extends EventArgs 
{
	Point oldLocation, newLocation;
	
	public MouseMovedEventArgs(Point oldLocation, Point newLocation) {
		this.oldLocation = oldLocation;
		this.newLocation = newLocation;
	}

	public MouseMovedEventArgs(int oldx, int oldy, int newx, int newy) {
		this( new Point(oldx, oldy), new Point(newx, newy));
	}

	public Point getOldLocation() {
		return oldLocation;
	}

	public Point getNewLocation() {
		return newLocation;
	}
}
