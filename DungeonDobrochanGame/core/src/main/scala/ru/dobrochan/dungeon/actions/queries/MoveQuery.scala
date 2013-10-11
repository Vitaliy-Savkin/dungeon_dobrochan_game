package ru.dobrochan.dungeon.core.actions.queries

import scala.collection.Seq

case class MoveQuery(contId : Integer, val unitId : Integer, 
			    val toX : Integer, val toY : Integer) extends AbstractQuery(contId) {

  def id: Integer = 1

  def Serealize(): Seq[Byte] = { null }

  def Desearialize(bytes: Seq[Byte]): AbstractQuery = { null }

}