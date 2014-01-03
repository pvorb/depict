package de.vorb.depict

case class Curve(kind: CurveType, endA: Point[Double], controlA: Point[Double],
  controlB: Point[Double], endB: Point[Double])
