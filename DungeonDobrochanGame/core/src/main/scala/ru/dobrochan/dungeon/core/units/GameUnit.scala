package ru.dobrochan.dungeon.core.units

import ru.dobrochan.dungeon.core.Entity

case class Characteristics(posX: Int, posY: Int, priority: Int, dead: Boolean) // to be continued

trait GameUnit extends Entity
{
  def initialCharacteristics: Characteristics
}

case class Skeleton(uid: Int) extends GameUnit{
  def initialCharacteristics = Characteristics(0, 0, 0, false)
}
