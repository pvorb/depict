package de.vorb.depict.tracing

sealed trait CurveType

object CurveType {
  object Line extends CurveType
  object Bezier extends CurveType
}
