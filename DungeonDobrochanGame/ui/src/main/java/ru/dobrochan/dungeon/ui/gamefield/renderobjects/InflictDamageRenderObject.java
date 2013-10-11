
package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import ru.dobrochan.dungeon.core.consts.UnitParameter;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 *
 * @author SkinnyMan
 */
public class InflictDamageRenderObject extends EntityRenderObject
{
	private int damage;
	private float yOffset;
	private float alpha;

	public InflictDamageRenderObject(int damage)
	{
		this.damage = damage;
		yOffset = 0;
		alpha = 1.5f;
	}

	@Override
	public void update(int delta)
	{
		yOffset -= (float)delta * 0.02;
		alpha -= (float)delta * 0.001;
		if (alpha <= 0)
			getGameFieldView().removeRenderObject(this);
	}

	@Override
	public void render(Graphics g)
	{
		Color pushColor = g.getColor();
		Color textColor = new Color(1.0f, 0, 0, alpha);
		g.setColor(textColor);
		float unitW = (Integer)(getOwner().getParam(UnitParameter.WIDTH())) * getGameFieldView().getCellWidth();
		String text = Integer.toString(damage);
		Font f = g.getFont();
		float stringW = f.getWidth(text);
		float xOffset = (unitW - stringW) / 2;
		g.drawString(text, getOwnerX() + xOffset, getOwnerY() + yOffset);
		g.setColor(pushColor);
	}

}
