package ru.dobrochan.dungeon.ui.events;

public class MouseWheelEventArgs extends EventArgs {
	int delta;

	public MouseWheelEventArgs(int delta) {
		this.delta = delta;
	}

	public int getDelta() {
		return delta;
	}		
}
