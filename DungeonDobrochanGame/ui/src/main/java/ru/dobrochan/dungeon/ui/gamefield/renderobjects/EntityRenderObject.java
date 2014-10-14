package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import ru.dobrochan.dungeon.core.Entity;
import ru.dobrochan.dungeon.ui.primitives.Point;

/**
 *
 * @author SkinnyMan
 */
public abstract class EntityRenderObject extends RenderObject
{
	private Entity owner;
	private Point ownerPos;

	protected Point getOwnerPos() { return ownerPos; }

	public void setOwner(Entity owner, Point ownerPos)
	{
		this.owner = owner;
        this.ownerPos = ownerPos;
	}

	public Entity getOwner()
	{
		return owner;
	}

	@Override
	public void stateChanged()
	{
//		GameFieldControl gameField = getGameFieldView();
//		int ownerCellX = (Integer)getOwner().getParam(UnitParameter.X());
//		int ownerCellY = (Integer)getOwner().getParam(UnitParameter.Y());
//		ownerX = gameField.getX() + gameField.getCellWidth() * ownerCellX;
//		ownerY = gameField.getY() + gameField.getCellHeight() * ownerCellY;
	}
}

