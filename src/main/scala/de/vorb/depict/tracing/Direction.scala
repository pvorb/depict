package de.vorb.depict.tracing

sealed trait Direction

object Direction {
  case object Up extends Direction
  case object Right extends Direction
  case object Down extends Direction
  case object Left extends Direction
}