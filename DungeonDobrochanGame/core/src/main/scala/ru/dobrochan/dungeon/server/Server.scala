package ru.dobrochan.dungeon.core.server

import ru.dobrochan.dungeon.core.actions._;
import ru.dobrochan.dungeon.core.models._;
import ru.dobrochan.dungeon.core.actions.queries._;

class Server(model : Model)
{
   var processor : ModelProcessor[Model] = setModel(model);
   
    def setModel(model: Model) : ModelProcessor[Model] = {
	    processor = model match {
	      case gpm: GameProcessModel => new GameProcessModelProcessor(gpm)
	      case _ => null
	    }
	    processor
    }
    
	def ProcessQuery(query : AbstractQuery) : Seq[AbstractAction] = { 
	   processor.ProcessQuery(query)
	}	
	
	//	abstract class ClientConnector
	//	{
	//	  def RetrieveNextQuery() : AbstractQuery
	//	  def SendAnswer(actions : Seq[AbstractAction], client : Integer)
	//	}
	//	
	//	val clientConnectors : Seq[ClientConnector]
}

abstract class ModelProcessor[+T <: Model](val model : T){
  def ProcessQuery(query : AbstractQuery) : Seq[AbstractAction]
}

class GameProcessModelProcessor(m : GameProcessModel) 
				extends ModelProcessor[GameProcessModel](m){
  def ProcessQuery(query : AbstractQuery) : Seq[AbstractAction] = {
    query match {
	    case MoveQuery(contId, unitId, toX, toY) => 
	      Seq(new TestMoveAction(contId, unitId, model.unit.posX, model.unit.posY, toX, toY))
	    case _ => Seq.empty[AbstractAction]
	  }
  }
}
