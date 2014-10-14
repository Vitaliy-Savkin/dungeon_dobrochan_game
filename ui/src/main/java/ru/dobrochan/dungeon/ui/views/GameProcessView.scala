package ru.dobrochan.dungeon.ui.views

import ru.dobrochan.dungeon.core.actions._
import ru.dobrochan.dungeon.core.views._
import ru.dobrochan.dungeon.core.Entity
import ru.dobrochan.dungeon.ui.gamefield.GameFieldControl
import ru.dobrochan.dungeon.ui.gamefield.renderobjects.MovingEntityRenderObject
import ru.dobrochan.dungeon.ui.primitives.Point

class GameProcessView(val gameFieldControl: GameFieldControl) extends View{
  def update(action : AbstractAction) = {
    action match {
      case MoveAction(contId, unitId, fromX, fromY, toX, toY) => {
        gameFieldControl.moveUnitEntity(
          new Entity() { val uid = unitId },
          new Point(fromX, fromY),
          new Point(toX, toY))
      }
    }
  }
}