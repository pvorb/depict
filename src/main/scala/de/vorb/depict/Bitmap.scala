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

  def withDefault(color: Color) =
    if (color == defaultColor)
      Bitmap.this
    else
      new Bitmap(original, color)

  def width = original.getWidth
  def height = original.getHeight
}
