package de.vorb.depict

sealed trait CurveType

object CurveType {
  object Line extends CurveType
  object Bezier extends CurveType
}
