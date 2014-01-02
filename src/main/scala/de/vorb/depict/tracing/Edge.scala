package de.vorb.depict.tracing

object Edge {

  /**
   * Determines if two points a and b form an edge.
   */
  def isEdge(a: Point[Int], b: Point[Int])(img: Bitmap): Boolean = {
    if ((a distanceTo b) != 1)
      false
    else if (a.x < b.x)
      img(a.x, a.y - 1).isBlack && img(a.x, a.y).isWhite
    else if (a.x > b.x)
      img(b.x, b.y).isBlack && img(b.x, b.y - 1).isWhite
    else if (a.y < b.y)
      img(b.x - 1, b.y).isBlack && img(b.x, b.y).isWhite
    else
      img(a.x, a.y).isBlack && img(a.x - 1, a.y).isWhite
  }
}
