package ru.dobrochan.dungeon.core.models

import ru.dobrochan.dungeon.core.actions._;
import ru.dobrochan.dungeon.core.units._;
import ru.dobrochan.dungeon.core.GameField;

trait Model { def update(action : AbstractAction) }

class GameProcessModel(val gameFiled : GameField, val unit : GameUnit) extends Model
{
  def update(action : AbstractAction) = { 
    action match {
	    case TestMoveAction(contId, unitId, fromX, fromY, toX, toY) => {
	      unit.posX = toX
	      unit.posY = toY
	    }
    }
  }
}