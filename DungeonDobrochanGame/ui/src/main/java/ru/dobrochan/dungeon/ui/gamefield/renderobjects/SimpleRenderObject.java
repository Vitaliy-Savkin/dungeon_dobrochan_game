
package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author SkinnyMan
 */
public class SimpleRenderObject extends EntityRenderObject
{
	public Image image;

	@Override
	public void stateChanged()
	{
		super.stateChanged();
	}

	@Override
	public void update(int delta)
	{

	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(image, getOwnerX(), getOwnerY());
	}

}
