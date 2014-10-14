package ru.dobrochan.dungeon.core.actions
import ru.dobrochan.dungeon.core.GameContext

sealed trait AbstractAction{
  def contextId: Int
	def execute(context : GameContext) {
	  if(context.id != contextId)
	    throw new ContextMismatchException
	  if(serverSource)
	    context.model.update(this)
	  context.view.update(this)
	}
	protected def serverSource: Boolean
}

class ContextMismatchException extends Exception

/*
 *  Client-side actions
 */
sealed trait AbstractClientAction extends AbstractAction { override def serverSource = false }

/*
 *  Server-side actions
 */
sealed trait AbstractServerAction extends AbstractAction { override def serverSource = true }

case class MoveAction(contextId : Int, unitId : Int,
                      fromX : Int, fromY : Int,
                      toX : Int, toY : Int) extends AbstractServerAction