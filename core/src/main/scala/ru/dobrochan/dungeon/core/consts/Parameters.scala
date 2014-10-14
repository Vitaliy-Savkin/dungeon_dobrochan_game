package ru.dobrochan.dungeon.core.consts

object Surface {
	val EMPTY = 0
	val PIT = 1
	val WATER = 2
	val ROCK = 3
	val WALL = 4
}

object UnitParameter {
	val ID = "id"
	val X = "posX"
	val Y = "posY"
	val WIDTH = "width"
	val HEIGHT = "height"
	val SIZE = "size"
	val MOVEMENT = "movement"
}

object Obstacles {
	val NONE = 1
	/** Небольшое препядствие. Непроходимо для пеших, проходимо для остальных. */
	val SMALL = 2
	/** Большое препядствие. Непроходимо для пеших и летающих, проходимо для остальных. */
	val BIG = 3
}

object Movement {	
	/** Сущность не может перемещаться. */
	val IMMOBILE = 1
	/** Сущность может перемещаться по земле. */
	val GROUND = 2
	/** Сущность может перемещаться по воздуху. */
	val FLY = 3
	/** Сущность может телепортироваться. */
	val TELEPORT = 4
}