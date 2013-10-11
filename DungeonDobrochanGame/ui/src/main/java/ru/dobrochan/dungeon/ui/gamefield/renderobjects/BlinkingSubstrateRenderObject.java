
package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ru.dobrochan.dungeon.content.ResourceManager;

/**
 *
 * @author SkinnyMan
 */
public class BlinkingSubstrateRenderObject extends EntityRenderObject
{
	private static final Image image = ResourceManager.getInstance().getImage("UNIT_BACKLIGHT");

	private Color color1;
	private Color color2;

	private double time;

	public BlinkingSubstrateRenderObject(Color color1, Color color2)
	{
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	public void update(int delta)
	{
		time += (double)delta / 1000.0;
	}

	@Override
	public void render(Graphics g)
	{
		double mix = Math.sin(4*time);
		g.drawImage(image, getOwnerX(), getOwnerY(), mixColor(color1, color2, mix));
	}

	private static Color mixColor(Color color1, Color color2, double mix)
	{
		float a = color1.a + (color2.a - color1.a) * (float)mix;
		float r = color1.r + (color2.r - color1.r) * (float)mix;
		float g = color1.g + (color2.g - color1.g) * (float)mix;
		float b = color1.b + (color2.b - color1.b) * (float)mix;
		return new Color(r, g, b, a);
	}

}
