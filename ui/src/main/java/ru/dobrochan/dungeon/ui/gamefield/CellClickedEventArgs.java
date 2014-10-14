package ru.dobrochan.dungeon.ui.gamefield;

import ru.dobrochan.dungeon.ui.events.*;


public class CellClickedEventArgs extends EventArgs
{
	int cellX, cellY, mouseButton;
	
	public CellClickedEventArgs(int cellX, int cellY, int mouseButton){
		this.cellX = cellX;
		this.cellY = cellY;
		this.mouseButton = mouseButton;
	}
	
	public int getCellX(){ return cellX;}
	public int getCellY(){ return cellY;}
	public int getMouseButton(){ return mouseButton;}
}
