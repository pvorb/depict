package de.vorb.depict

case class Edge(from: Point[Int], to: Point[Int]) {
  def direction: EdgeDirection =
    if (from.y > to.y) EdgeDirection.Up
    else if (from.x < to.x) EdgeDirection.Right
    else if (from.y < to.y) EdgeDirection.Down
    else if (from.x > to.x) EdgeDirection.Left
    else throw new IllegalStateException(s"invalid edge $this")

  override def toString = s"$from -> $to"

  def next(direction: Option[TurnDirection] = None): Edge =
    direction match {
      case None                      => Edge(to, to + (to - from))
      case Some(TurnDirection.Left)  => Edge(to, to + (to - from).toLeft)
      case Some(TurnDirection.Right) => Edge(to, to + (to - from).toRight)
    }

  def leftPoint: Point[Int] = direction match {
    case EdgeDirection.Up    => Point(from.x - 1, from.y - 1)
    case EdgeDirection.Right => Point(from.x, from.y - 1)
    case EdgeDirection.Down  => from
    case EdgeDirection.Left  => to
  }

  def rightPoint: Point[Int] = direction match {
    case EdgeDirection.Up    => to
    case EdgeDirection.Right => from
    case EdgeDirection.Down  => Point(from.x - 1, from.y)
    case EdgeDirection.Left  => Point(from.x - 1, from.y - 1)
  }

  /**
   * Determines if two points a and b form a valid edge.
   */
  def isValidEdge(implicit img: Bitmap): Boolean = {
    if ((from distanceTo to) != 1)
      false
    else
      rightPoint.isWhite && leftPoint.isBlack
  }
}

sealed trait EdgeDirection

object EdgeDirection {
  case object Up extends EdgeDirection
  case object Right extends EdgeDirection
  case object Down extends EdgeDirection
  case object Left extends EdgeDirection
}

sealed trait NextDirection
