package ru.dobrochan.dungeon.core.actions.queries

sealed trait AbstractQuery {
	def id : Int
  def contextId: Int
}

case class MoveQuery(contextId : Int, unitId : Int, toX : Int, toY : Int) extends AbstractQuery {
  def id: Int = 1
}
