package de.vorb.depict

import scala.Numeric._

case class Point[T](x: T, y: T)(implicit num: Numeric[T]) {
  def +(other: Point[T]): Point[T] =
    Point(num.plus(x, other.x), num.plus(y, other.y))

  def -(other: Point[T]): Point[T] =
    Point(num.minus(x, other.x), num.minus(y, other.y))

  def distanceTo(other: Point[T]): Double = {
    val xDiff = num.toDouble(x) - num.toDouble(other.x)
    val yDiff = num.toDouble(y) - num.toDouble(other.y)
    math.sqrt(xDiff * xDiff + yDiff * yDiff)
  }

  def maxDistanceTo(other: Point[T]): Double = {
    val xDiff = num.toDouble(x) - num.toDouble(other.x)
    val yDiff = num.toDouble(y) - num.toDouble(other.y)
    math.max(xDiff, yDiff)
  }

  override def toString = s"($x, $y)"
}

object Point {
  implicit class RichPoint(val p: Point[Int]) {
    def isVertex(implicit img: Bitmap) = {
      val nw = img(p.x - 1, p.y - 1)
      val ne = img(p.x, p.y - 1)
      val se = img(p.x, p.y)
      val sw = img(p.x - 1, p.y)

      (nw.argb ^ ne.argb ^ se.argb ^ sw.argb) != 0
    }

    def color(implicit img: Bitmap): Color = img(p.x, p.y)
    def isBlack(implicit img: Bitmap) = color.isBlack
    def isWhite(implicit img: Bitmap) = color.isWhite

    def toLeft: Point[Int] = this.p match {
      case Point(1, 0)  => Point(0, -1)
      case Point(0, -1) => Point(-1, 0)
      case Point(-1, 0) => Point(0, 1)
      case Point(0, 1)  => Point(1, 0)
    }

    def toRight: Point[Int] = this.p match {
      case Point(1, 0)  => Point(0, 1)
      case Point(0, 1)  => Point(-1, 0)
      case Point(-1, 0) => Point(0, -1)
      case Point(0, -1) => Point(1, 0)
    }
  }
}
