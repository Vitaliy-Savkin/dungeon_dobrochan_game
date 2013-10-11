
package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ru.dobrochan.dungeon.content.ResourceManager;

/**
 *
 * @author SkinnyMan
 */
public class SubstrateRenderObject extends EntityRenderObject
{
	private static final Image image = ResourceManager.getInstance().getImage("UNIT_BACKLIGHT");

	private Color color;

	public SubstrateRenderObject(Color color)
	{
		this.color = color;
	}

	@Override
	public void stateChanged()
	{
		super.stateChanged();
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(image, getOwnerX(), getOwnerY(), color);
	}

	@Override
	public void update(int delta)
	{

	}

}
