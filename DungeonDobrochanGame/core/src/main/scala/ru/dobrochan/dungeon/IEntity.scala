package ru.dobrochan.dungeon.core

import java.util.Map;
import java.util.Set;

trait IEntity //extends Cloneable 
{

/** Устанавливает параметр сущности.
	 *
	 * @param name имя параметра
	 * @param value значение параметра
	 */
	def setParam(name : String, value : Object) : Unit

	/**
	 * Возвращает значение параметра.
	 *
	 * @param name имя параметра
	 * @return значение параметра
	 */
	def getParam(name : String) : Object

	/**
	 *
	 * @return
	 */
	//def clone() : IEntity
}

class DummyEntity extends IEntity{
	def setParam(name : String, value : Object) : Unit = {}
	def getParam(name : String) : Object = null
}