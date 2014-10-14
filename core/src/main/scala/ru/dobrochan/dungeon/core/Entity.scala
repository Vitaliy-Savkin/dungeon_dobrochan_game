package ru.dobrochan.dungeon.core

trait Entity{
  def uid: Int
  override def hashCode(): Int = uid.hashCode()
  override def equals(obj: scala.Any): Boolean = obj match {
    case obj: Entity => uid.equals(obj.uid)
    case _ => false
  }
}