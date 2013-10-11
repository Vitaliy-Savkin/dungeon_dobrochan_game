package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import ru.dobrochan.dungeon.core.IEntity;

/**
 *
 * @author SkinnyMan
 */
public abstract class EntityRenderObject extends RenderObject
{
	private IEntity owner;

	private int ownerX;
	private int ownerY;

	protected int getOwnerX() { return ownerX; }
	protected int getOwnerY() { return ownerY; }

	public void setOwner(IEntity owner)
	{
		this.owner = owner;
	}

	public IEntity getOwner()
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

