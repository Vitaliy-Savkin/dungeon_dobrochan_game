import ru.dobrochan.dungeon.core.actions.queries._

trait QuerySerializer[SerializedType]{
  def serialize(query: AbstractQuery): SerializedType
  def deserialize(query: SerializedType): AbstractQuery
}

object BinaryQuerySerializer extends QuerySerializer[Seq[Byte]]{
  def serialize(query: AbstractQuery): Seq[Byte] = query match {
    case MoveQuery(_, _, _, _) => Seq.empty[Byte]
  }

  def deserialize(data: Seq[Byte]): AbstractQuery = data match {
    case Seq(0, _*) => null
  }
}
