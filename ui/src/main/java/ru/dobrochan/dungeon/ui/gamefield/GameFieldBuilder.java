package ru.dobrochan.dungeon.ui.gamefield;

import ru.dobrochan.dungeon.content.Settings;
import ru.dobrochan.dungeon.core.consts.Surface;
import ru.dobrochan.dungeon.core.GameField;


/**
 * Вспомогательный класс для создания декоративного рельефа.
 *
 * @author SkinnyMan
 */
public class GameFieldBuilder
{
	private GameField field;

	public GameFieldBuilder()
	{
		field = new GameField(Settings.FIELD_WIDTH, Settings.FIELD_HEIGHT);
		clearRect(0, 0, field.widthInCells(), field.heightInCells());
	}

	public GameField getBuiltField()
	{
		return field;
	}

	public void setCell(int x, int y, int surface)
	{
		field.setSurfaceAt(surface, x, y);
	}

	public void setRect(int x, int y, int w, int h, int surface)
	{
		for (int i = y; i < y + h; i++)
			for (int j = x; j < x + w; j++)
				field.setSurfaceAt(surface, j, i);
	}

	public void clearCell(int x, int y)
	{
		field.setSurfaceAt(Surface.EMPTY(), x, y);
	}
 
	public void clearRect(int x, int y, int w, int h)
	{
		for (int i = y; i < y + h; i++)
			for (int j = x; j < x + w; j++)
				field.setSurfaceAt(Surface.EMPTY(), j, i);
	}
}