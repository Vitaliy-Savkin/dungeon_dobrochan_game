package ru.dobrochan.dungeon.core

import ru.dobrochan.dungeon.core.consts.Surface

class GameField(val widthInCells: Int, val heightInCells: Int)
{
	private val surfaces = Array.ofDim[Integer](widthInCells, heightInCells)
	
	def getSurfaceAt(x : Integer, y : Integer) : Integer = surfaces(x)(y);
	def setSurfaceAt(value : Integer, x : Integer, y : Integer) { surfaces(x)(y) = value } ;
}