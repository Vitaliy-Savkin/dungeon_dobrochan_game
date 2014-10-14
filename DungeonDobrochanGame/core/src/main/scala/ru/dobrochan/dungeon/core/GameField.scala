package ru.dobrochan.dungeon.core

import ru.dobrochan.dungeon.core.consts.Surface

class GameField(val widthInCells: Int, val heightInCells: Int)
{
	private val surfaces = Array.ofDim[Int](widthInCells, heightInCells)
	
	def getSurfaceAt(x : Int, y : Int) : Int = surfaces(x)(y)
	def setSurfaceAt(value : Int, x : Int, y : Int) { surfaces(x)(y) = value }
}