package ru.dobrochan.dungeon.core.models

import ru.dobrochan.dungeon.core.actions._
import ru.dobrochan.dungeon.core.units._
import ru.dobrochan.dungeon.core.GameField
import scala.collection.mutable

trait Model { def update(action : AbstractAction) }

class GameProcessModel(val gameField: GameField, units: scala.collection.Iterable[GameUnit]) extends Model
{
  private val pos = units.map(u => (u, u.initialCharacteristics))
                         .map({case (u, ch) => (u, ch.copy(posX = 1, posY = 1))})
                         .toSeq

  private val _units = mutable.Map(pos: _*)

  def getUnitPosById(uid: Int) = _units.find({case (u, ch) => u.uid == uid}).map({case (u, ch) => (ch.posX, ch.posY)})

  def update(action : AbstractAction) = action match {
	    case MoveAction(contId, unitId, _, _, toX, toY) =>
        _units.find({case (u, ch) => u.uid == unitId})
              .map({case (u, ch) => _units(u) = ch.copy(posX = toX, posY = toY)})
	    }
 }
