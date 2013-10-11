package ru.dobrochan.dungeon.ui.controls;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import ru.dobrochan.dungeon.ui.primitives.Point;
import ru.dobrochan.dungeon.ui.primitives.Size;

public class ControlContainer extends AbstractControl implements IControlContainer {

	ArrayList<AbstractControl> children = new ArrayList<AbstractControl>();	
	boolean acceptingInput = true;
	
	public ControlContainer(GUIContext container) {
		super(container);
	}
	
	public void addChild(AbstractControl child){
		addChild(child, new Size(0, 0));
	}
	
	public void addChild(AbstractControl child, Size offset){
		if(child.parent != null)
			child.removeFromParent();
		
		children.add(child);
		child.parent = this;
		setChildOffset(child, offset);
	}
	
	public void removeChild(AbstractControl child){
		children.remove(child);
		child.parent = null;
	}	
	
	public Size getChildOffset(AbstractControl child){
		if (child.parent != this)
			throw new IllegalArgumentException("Control is not a child of the container!");
		return Point.Substract(child.getLocation(), this.getLocation());
	}
	
	public void setChildOffset(AbstractControl child, Size offset){
		if (child.parent != this)
			throw new IllegalArgumentException("Control is not a child of the container!");
		child.setLocation(Point.Add(getLocation(), offset));
	}
	
	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		if (visible)
		{
			for(AbstractControl control : children)
				control.render(container, g);
		}
	}

	@Override
	public int getHeight() {
		int maxBottom = 0;
		for (AbstractControl control : children){			
			int bottom = control.getY() + control.getHeight();
			if (bottom > maxBottom)
					maxBottom = bottom;
		}
		return maxBottom - getY();
	}

	@Override
	public int getWidth() {
		int maxRight = 0;
		for (AbstractControl control : children){
			int right = control.getX() + control.getWidth();
			if (right > maxRight)
				maxRight = right;	
		}
		return maxRight - getX();
	}
	
	public void setLocation(Point pt){
		if (children != null)
		{
			HashMap<AbstractControl, Size> offsets = new HashMap<AbstractControl, Size>();
			for(AbstractControl control : children){
				offsets.put(control, getChildOffset(control));
			}
			super.setLocation(pt);
			for(AbstractControl control : children){
				setChildOffset(control, offsets.get(control));
			}
		}
		else super.setLocation(pt);
	}
	
	public void setAcceptingInput(boolean value){
		acceptingInput = value;
	}
	
	public boolean isAcceptingInput(){
		return acceptingInput && super.isAcceptingInput();
	}
}
