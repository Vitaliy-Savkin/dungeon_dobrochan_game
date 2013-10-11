package ru.dobrochan.dungeon.core.views

import ru.dobrochan.dungeon.core.actions._
import ru.dobrochan.dungeon.core.{IEntity, DummyEntity}

trait View {
  def update(action : AbstractAction)
}