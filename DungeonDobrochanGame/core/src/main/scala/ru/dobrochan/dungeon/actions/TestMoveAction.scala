package ru.dobrochan.dungeon.core.actions

case class TestMoveAction(contId : Integer, val unitId : Integer, 
						  val fromX : Integer, val fromY : Integer, 
						  val toX : Integer, val toY : Integer) extends AbstractServerAction(contId) {}