package ru.dobrochan.dungeon.core.server

import ru.dobrochan.dungeon.core.actions._
import ru.dobrochan.dungeon.core.actions.queries._

trait ServerConnector
{
  def SendQuery(query: AbstractQuery): Seq[AbstractAction]
}

class SingleMachineServerConnector(val server: Server) extends ServerConnector
{
  override def SendQuery(query: AbstractQuery): Seq[AbstractAction] = server.processQuery(query)
}