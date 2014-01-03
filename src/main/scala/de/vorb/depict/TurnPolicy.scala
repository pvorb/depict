package de.vorb.depict

sealed trait TurnPolicy {
  def turnDirection(edge: Edge)(implicit img: Bitmap): TurnDirection
}

object TurnPolicy {
  case object Left extends TurnPolicy {
    def turnDirection(edge: Edge)(implicit img: Bitmap) = TurnDirection.Left
  }

  case object Right extends TurnPolicy {
    def turnDirection(edge: Edge)(implicit img: Bitmap) = TurnDirection.Right
  }

  //  case object White extends TurnPolicy
  //  case object Black extends TurnPolicy
  //  case object Minority extends TurnPolicy
  //  case object Majority extends TurnPolicy
  //  case object Random extends TurnPolicy
}
