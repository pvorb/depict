package de.vorb.depict

import java.awt.image.BufferedImage

class Bitmap(val original: BufferedImage, defaultColor: Color = Color.white) {
  def apply(x: Int, y: Int): Color =
    if (x < original.getMinX || x >= original.getWidth ||
      y < original.getMinY || y >= original.getHeight)
      Color.white
    else
      Color(original.getRGB(x, y))

  def update(x: Int, y: Int, color: Color): Unit = {
    if (x >= original.getMinX || x < original.getWidth ||
      y >= original.getMinY || y < original.getHeight)
      original.setRGB(x, y, color.argb)
  }

  def invert(x: Int, y: Int): Unit =
    original.setRGB(x, y, original.getRGB(x, y) ^ 0x00FFFFFF)

  def invertLine(y: Int, x0: Int, x1: Int): Unit = {
    (x0 until x1) foreach { x =>
      invert(x, y)
    }
  }

  def withDefault(color: Color) =
    if (color == defaultColor)
      Bitmap.this
    else
      new Bitmap(original, color)

  def width = original.getWidth
  def height = original.getHeight

  def clear(color: Color = defaultColor) {
    val g = original.getGraphics
    g.setColor(new java.awt.Color(color.argb))
    g.fillRect(0, 0, width, height)
  }
}
