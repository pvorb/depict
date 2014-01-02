package de.vorb.depict.tracing

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
}

object Point {
  implicit class RichPoint[T](val point: Point[T]) {
    def isVertex()
  }
}
