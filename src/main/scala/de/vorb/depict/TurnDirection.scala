package de.vorb.depict

sealed trait TurnDirection

object TurnDirection {
  case object Left extends TurnDirection
  case object Right extends TurnDirection
}
