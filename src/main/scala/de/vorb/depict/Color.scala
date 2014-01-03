package de.vorb.depict

class Color(val argb: Int) extends AnyVal {
  def isBlack = argb == 0xFF000000
  def isWhite = argb == 0xFFFFFFFF
}

object Color {
  def apply(argb: Int) = new Color(argb)
  val white = Color(0xFFFFFFFF)
  val black = Color(0xFF000000)
}
