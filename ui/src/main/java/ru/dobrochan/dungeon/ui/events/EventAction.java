package ru.dobrochan.dungeon.ui.events;

import ru.dobrochan.dungeon.ui.controls.AbstractControl;
import ru.dobrochan.dungeon.ui.events.EventArgs;

public abstract class EventAction<T extends EventArgs>
{
	public abstract void execute(AbstractControl sender, T e);
}