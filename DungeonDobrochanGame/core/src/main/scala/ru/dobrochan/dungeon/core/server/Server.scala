package ru.dobrochan.dungeon.core.server

import ru.dobrochan.dungeon.core.actions._
import ru.dobrochan.dungeon.core.models._
import ru.dobrochan.dungeon.core.actions.queries._

class Server(model: Model)
{
   val processor: ModelProcessor[Model] = model match {
     case gpm: GameProcessModel => new GameProcessModelProcessor(gpm)
     case _ => null
   }

	def processQuery(query: AbstractQuery): Seq[AbstractAction] = {
	   processor.processQuery(query)
	}	
	
	//	abstract class ClientConnector
	//	{
	//	  def RetrieveNextQuery() : AbstractQuery
	//	  def SendAnswer(actions : Seq[AbstractAction], client : Integer)
	//	}
	//	
	//	val clientConnectors : Seq[ClientConnector]
}

abstract class ModelProcessor[+T <: Model]{
  def processQuery(query: AbstractQuery): Seq[AbstractAction]
}

class GameProcessModelProcessor(model: GameProcessModel) extends ModelProcessor[GameProcessModel]{
  def processQuery(query : AbstractQuery): Seq[AbstractAction] = {
    query match {
	    case MoveQuery(contId, unitId, toX, toY) =>
        model.getUnitPosById(unitId)
             .map({case (fromX, fromY) => new MoveAction(contId, unitId, fromX, fromY, toX, toY)})
             .toSeq
	    case _ => Seq.empty[AbstractAction]
	  }
  }
}
