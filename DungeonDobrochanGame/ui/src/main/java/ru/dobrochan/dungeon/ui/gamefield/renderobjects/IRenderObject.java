
package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import org.newdawn.slick.Graphics;

/**
 *
 * @author SkinnyMan
 */
public interface IRenderObject
{
	int getLayer();
	void stateChanged();
	void update(int delta);
	void render(Graphics g);
}
