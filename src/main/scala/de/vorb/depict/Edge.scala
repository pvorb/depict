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

  /**
   * Determines if two points a and b form an edge.
   */
  def isValid(implicit img: Bitmap): Boolean = {
    if ((from distanceTo to) != 1)
      false
    else if (from.x < to.x)
      img(from.x, from.y - 1).isBlack && img(from.x, from.y).isWhite
    else if (from.x > to.x)
      img(to.x, to.y).isBlack && img(to.x, to.y - 1).isWhite
    else if (from.y < to.y)
      img(to.x - 1, to.y).isBlack && img(to.x, to.y).isWhite
    else
      img(from.x, from.y).isBlack && img(from.x - 1, from.y).isWhite
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
