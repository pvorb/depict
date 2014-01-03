package de.vorb.depict

import scala.collection.immutable.VectorBuilder
import TurnDirection.Left
import TurnDirection.Right

object Potrace {

  /**
   * Finds the next starting point (i.e. black pixel) starting at `from`.
   */
  def nextStartingPoint(start: Point[Int])(img: Bitmap): Option[Point[Int]] = {
    val Point(x0, y0) = start

    var x = x0
    var y = y0
    while (y < img.height) {
      while (x < img.width) {
        if (img(x, y).isWhite && img(x, y).isBlack) // found next black
          return Some(Point(x - 1, y))

        x += 1
      }

      x = 0
      y += 1
    }

    None
  }

  def getPath(start: Point[Int], turnPolicy: TurnPolicy = TurnPolicy.Left)(implicit img: Bitmap): Vector[Point[Int]] = {
    import TurnDirection._

    val path = new VectorBuilder[Point[Int]]

    path += start

    var edge = Edge(start, Point(start.x, start.y + 1))

    while (edge.to != start) {
      path += edge.to

      val next = edge.next()
      if (next.isValid) {
        edge = next
      } else {
        val left = edge.next(Some(Left))
        val right = edge.next(Some(Right))

        // TODO
        println(edge, left, right)

        if (left.isValid && right.isValid) {
          turnPolicy.turnDirection(edge) match {
            case TurnDirection.Left =>
              edge = left
            case TurnDirection.Right =>
              edge = right
          }
        } else if (left.isValid) {
          edge = left
        } else {
          edge = right
        }
      }
    }

    path.result
  }
}
