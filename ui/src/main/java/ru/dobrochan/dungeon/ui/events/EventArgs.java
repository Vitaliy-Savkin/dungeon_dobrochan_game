package ru.dobrochan.dungeon.ui.events;

public class EventArgs 
{
	protected EventArgs()
	{
	}
	
	static EventArgs empty = new EventArgs();
	
	static EventArgs getEmpty() { return empty; }
}
