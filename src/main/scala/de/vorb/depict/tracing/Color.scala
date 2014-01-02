package de.vorb.depict.tracing

class Color(val argb: Int) extends AnyVal

object Color {
  def apply(argb: Int) = new Color(argb)
  val white = Color(0xFFFFFFFF)
  val black = Color(0xFF000000)
}
