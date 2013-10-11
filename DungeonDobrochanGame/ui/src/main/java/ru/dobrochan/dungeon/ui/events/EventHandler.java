package ru.dobrochan.dungeon.ui.events;

import java.util.ArrayList;

import ru.dobrochan.dungeon.ui.controls.AbstractControl;

public class EventHandler<TAction extends EventAction<TEventArgs>, TEventArgs extends EventArgs> 
{
	ArrayList<TAction> actions = new ArrayList<TAction>();
	
	public void addAction(TAction action)
	{
		actions.add(action);
	}	
	
	public void removeAction(TAction action)
	{
		actions.remove(action);
	}
	
	public void invoke(AbstractControl control, TEventArgs e)
	{
		for (TAction action : actions)
		{
			action.execute(control, e);
		}
	}	
}
