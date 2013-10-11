package ru.dobrochan.dungeon.core.server

import ru.dobrochan.dungeon.core.actions._;
import ru.dobrochan.dungeon.core.actions.queries._;
import java.util.List;
import scala.collection.JavaConversions._;


class SingleMachineServerConnector(val server : Server) extends ServerConnector
{
  override def SendQuery(query: AbstractQuery) : List[AbstractAction] =
  {
    seqAsJavaList(server.ProcessQuery(query));
  }
}