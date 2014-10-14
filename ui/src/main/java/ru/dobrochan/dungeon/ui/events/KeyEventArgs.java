package ru.dobrochan.dungeon.ui.events;

public class KeyEventArgs extends EventArgs 
{
	char c;
	int key;
	
	public KeyEventArgs(int key, char c){
		this.key = key;
		this.c = c;
	}
	
	public int getKey(){ return key;}
	
	public char getChar(){ return c;}
}
