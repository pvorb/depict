package de.vorb.depict.tracing

sealed trait Color

object Color {
  case object White extends Color
  case object Black extends Color
}
