package de.vorb.depict.tracing

import java.awt.image.BufferedImage

case class Pixel(var x: Int, var y: Int)(implicit img: BufferedImage) {
  def n = Pixel(x, y + 1)
  def s = Pixel(x, y - 1)
  def e = Pixel(x + 1, y)
  def w = Pixel(x - 1, y)

  def nw = Corner(x - 1, y + 1)
  def ne = Corner(x + 1, y + 1)
  def sw = Corner(x - 1, y - 1)
  def se = Corner(x + 1, y - 1)

  def color: Color =
    if (isInsideImage)
      if (img.getRGB(x, y) == 0x000000)
        Color.Black
      else
        Color.White
    else
      Color.White

  def isInsideImage: Boolean =
    (x >= 0 && y >= 0 && x < img.getWidth && y < img.getHeight)
}