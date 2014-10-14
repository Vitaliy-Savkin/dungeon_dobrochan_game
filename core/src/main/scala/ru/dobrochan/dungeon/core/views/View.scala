package ru.dobrochan.dungeon.core.views

import ru.dobrochan.dungeon.core.actions._

trait View {
  def update(action: AbstractAction)
}