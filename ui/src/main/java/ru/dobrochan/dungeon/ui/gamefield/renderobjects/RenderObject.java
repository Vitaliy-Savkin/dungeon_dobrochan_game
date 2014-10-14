
package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import ru.dobrochan.dungeon.ui.gamefield.GameFieldControl;
import ru.dobrochan.dungeon.ui.gamefield.renderobjects.IRenderObject;

/**
 *
 * @author SkinnyMan
 */
public abstract class RenderObject implements IRenderObject
{
	private GameFieldControl gameFieldView;

	@Override
	public int getLayer()
	{
		return 10;
	}

	public GameFieldControl getGameFieldView() { return gameFieldView; }
	public void setGameFieldView(GameFieldControl fieldView) { gameFieldView = fieldView; }
}
