package de.vorb.depict

import scala.collection.immutable.VectorBuilder
import TurnDirection.Left
import TurnDirection.Right
import scala.collection.immutable.VectorBuilder

object Potrace {
  /**
   * Finds the next starting point (i.e. black pixel) starting at `from`.
   */
  def findNext(start: Point[Int])(implicit img: Bitmap): Option[Point[Int]] = {
    var x = start.x
    var y = start.y

    while (y < img.height) {
      while (x < img.width) {
        if (img(x - 1, y).isWhite && img(x, y).isBlack) // found next black
          return Some(Point(x, y))

        x += 1
      }

      x = 0
      y += 1
    }

    None
  }

  /**
   * Returns a closed path beginning at the given starting point.
   */
  def findPath(start: Point[Int], turnPolicy: TurnPolicy = TurnPolicy.Left)(
    implicit img: Bitmap): Path = {

    import TurnDirection._

    val path = new VectorBuilder[Point[Int]]

    path += start

    var edge = Edge(start, Point(start.x, start.y + 1))

    while (edge.to != start) {
      path += edge.to

      val next = edge.next()
      if (next.isValidEdge) {
        edge = next
      } else {
        val left = edge.next(Some(Left))
        val right = edge.next(Some(Right))

        if (left.isValidEdge && right.isValidEdge) {
          turnPolicy.turnDirection(edge) match {
            case TurnDirection.Left =>
              edge = left
            case TurnDirection.Right =>
              edge = right
          }
        } else if (left.isValidEdge) {
          edge = left
        } else {
          edge = right
        }
      }
    }

    path += start // closed path

    Path(path.result)
  }

  def findAllPaths(turdSize: Int = 2)(implicit img: Bitmap): Vector[Path] = {
    var ps = new VectorBuilder[Path]

    var line = 0
    val height = img.height
    var startPoint = findNext(Point(0, 0))
    while (startPoint != None) {
      val path = findPath(startPoint.get)

      // omit paths that have less than `turdSize` pixels
      if (path.area >= turdSize)
        ps += path

      path.invert
      startPoint = findNext(startPoint.get)
    }

    ps.result
  }
}
