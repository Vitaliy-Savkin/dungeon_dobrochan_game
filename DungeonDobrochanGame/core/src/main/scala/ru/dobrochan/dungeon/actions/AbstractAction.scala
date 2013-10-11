package ru.dobrochan.dungeon.core.actions
import ru.dobrochan.dungeon.core.GameContext

abstract class AbstractAction(val contextId : Integer) {
	def execute(context : GameContext) {
	  if(context.id != contextId)
	    throw new ContextMismatchException
	  if(serverSource)
	    context.model.update(this);
	  context.view.update(this);
	}
	protected def serverSource : Boolean 
}

class ContextMismatchException extends Exception

abstract class AbstractClientAction(contId : Integer) extends AbstractAction(contId){
  override def serverSource = false
}

abstract class AbstractServerAction(contId : Integer) extends AbstractAction(contId){
  override def serverSource = true
}