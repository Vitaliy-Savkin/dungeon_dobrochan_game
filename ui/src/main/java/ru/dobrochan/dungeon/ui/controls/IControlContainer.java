package ru.dobrochan.dungeon.ui.controls;

import ru.dobrochan.dungeon.ui.primitives.Size;

public interface IControlContainer 
{
	public void addChild(AbstractControl child);
	public void removeChild(AbstractControl child);
	public Size getChildOffset(AbstractControl child);	
	public void setChildOffset(AbstractControl child, Size offset);
	public void setAcceptingInput(boolean value);
	public boolean isAcceptingInput();
}
