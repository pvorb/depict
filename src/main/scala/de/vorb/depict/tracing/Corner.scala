package de.vorb.depict.tracing

import java.awt.image.BufferedImage

case class Corner(x: Int, y: Int)(implicit img: BufferedImage) {
  def n = Corner(x, y + 1)
  def s = Corner(x, y - 1)
  def e = Corner(x + 1, y)
  def w = Corner(x - 1, y)

  def nw = Pixel(x - 1, y)
  def ne = Pixel(x, y)
  def sw = Pixel(x - 1, y - 1)
  def se = Pixel(x, y - 1)

  def isVertex: Boolean = {
    !(nw.color == ne.color && nw.color == sw.color &&
      nw.color == se.color && ne.color == sw.color &&
      ne.color == se.color && sw.color == se.color)
  }

  def distance(other: Corner): Double = {
    val dx = this.x - other.x
    val dy = this.y - other.y
    math.sqrt(dx * dx + dy * dy)
  }
}