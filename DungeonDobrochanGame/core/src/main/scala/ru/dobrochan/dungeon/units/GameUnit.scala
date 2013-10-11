package ru.dobrochan.dungeon.core.units

abstract class GameUnit(val uid : Integer, var posX : Integer, var posY : Integer) {
}

class Skeleton(_uid : Integer, _x : Integer, _y : Integer) extends GameUnit(_uid, _x, _y) {
}

