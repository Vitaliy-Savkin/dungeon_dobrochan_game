package ru.dobrochan.dungeon.core.actions.queries

abstract class AbstractQuery(val contextId : Integer) {
	def id : Integer
	def Serealize() : Seq[Byte]
	def Desearialize(bytes : Seq[Byte]) : AbstractQuery
}

