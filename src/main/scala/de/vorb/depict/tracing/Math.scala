package de.vorb.depict.tracing

import scala.Numeric._

object Geometry {
  /**
   * Calculate p0 x p1.
   */
  def xprod[T](p0: Point[T], p1: Point[T])(implicit num: Numeric[T]) =
    {
      num.minus(num.times(p0.x, p1.y), num.times(p0.y, p1.x))
    }

  /**
   * Calculate (p1 - p0) x (p3 - p2).
   */
  def cprod(p0: Point[Double], p1: Point[Double], p2: Point[Double],
    p3: Point[Double]): Double =
    {
      xprod(p1 - p0, p3 - p2)
    }

  /**
   * Calculate (p1 - p0) * (p2 - p0).
   */
  def iprod(p0: Point[Double], p1: Point[Double], p2: Point[Double]): Double =
    {
      val res0 = p1 - p0
      val res1 = p2 - p0

      res0.x * res1.x + res0.y * res1.y
    }

  /**
   * Calculate (p1 - p0) * (p3 - p2).
   */
  def iprod1(p0: Point[Double], p1: Point[Double], p2: Point[Double],
    p3: Point[Double]): Double =
    {
      val res0 = p1 - p0
      val res1 = p3 - p2

      res0.x * res1.x + res0.y * res1.y
    }

  /**
   * Calculate (p1 - p0) x (p2 - p0), the area of the parallelogram.
   */
  def areaOfParalleloram(p0: Point[Double], p1: Point[Double],
    p2: Point[Double]): Double =
    {
      val res0 = p1 - p0
      val res1 = p2 - p0

      res0.x * res1.y - res1.x * res0.y
    }

  /**
   * Return `true` if a <= b < c < a in a cyclic sense (mod n).
   */
  def cyclic(a: Int, b: Int, c: Int): Boolean =
    if (a <= c)
      a <= b && b < c
    else
      a <= b || b < c

  /**
   * Calculate a point `t` in [0..1] on a cubic bezier curve.
   */
  def bezier(t: Double, p0: Point[Double], p1: Point[Double], p2: Point[Double],
    p3: Point[Double]): Point[Double] =
    {
      require(t >= 0d, "t is < 0")
      require(t <= 1d, "t is > 1")

      val s = 1d - t
      val sQuad = s * s
      val tQuad = t * t

      // cubic bezier curve
      val x = sQuad * s * p0.x + 3 * (sQuad * t) * p1.x + 3 * (tQuad * s) * p2.x +
        tQuad * t * p3.x
      val y = sQuad * s * p0.y + 3 * (sQuad * t) * p1.y + 3 * (tQuad * s) * p2.y +
        tQuad * t * p3.y

      Point(x, y)
    }

  /**
   * Calculate the point `t` in [0..1] on the (convex) bezier curve
   * (p0, p1, p2, p3) which is tangent to q1 - q0. Return -1.0 if there is no
   * solution in [0..1].
   */
  def tangent(p0: Point[Double], p1: Point[Double], p2: Point[Double],
    p3: Point[Double], q0: Point[Double], q1: Point[Double]): Double =
    {
      val A = cprod(p0, p1, q0, q1)
      val B = cprod(p1, p2, q0, q1)
      val C = cprod(p2, p3, q0, q1)

      val a = A - 2d * B + C
      val b = -2d * A + 2d * B
      val c = A
      val d = b * b - 4d * a * c

      if (a == 0d || d < 0d)
        return -1d

      val s = math.sqrt(d)

      val r0 = (-b + s) / (2d * a)

      if (r0 >= 0d && r0 <= 1d)
        return r0

      val r1 = (-b - s) / (2d * a)

      if (r1 >= 0d && r1 <= 1d)
        r1
      else
        -1d
    }
}
