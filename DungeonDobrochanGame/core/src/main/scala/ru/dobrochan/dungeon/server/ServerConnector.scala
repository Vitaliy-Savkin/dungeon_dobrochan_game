package ru.dobrochan.dungeon.core.server

import ru.dobrochan.dungeon.core.actions._;
import ru.dobrochan.dungeon.core.actions.queries._;
import java.util.List;


abstract class ServerConnector
{
  def SendQuery(query: AbstractQuery) : List[AbstractAction]
}