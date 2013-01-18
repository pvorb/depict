package de.vorb.depict.tracing

/**
 *
 */
case class Edge(v: Corner, w: Corner) {
  require((v distance w) <= 1.0d, "distance between vertices > 1")
  require(v.isVertex, "v not a vertex")
  require(w.isVertex, "w not a vertex")

  val direction: Direction =
    if (v.x > w.x) Direction.Left
    else if (v.x < w.x) Direction.Right
    else if (v.y > w.y) Direction.Down
    else Direction.Up

  // ensure that the edge has a black pixel on its left and a white pixel on its
  // right.
  direction match {
    case Direction.Left =>
      require(v.sw.color == Color.Black, "left pixel white")
      require(v.nw.color == Color.White, "right pixel black")
    case Direction.Right =>
      require(v.ne.color == Color.Black, "left pixel white")
      require(v.se.color == Color.White, "right pixel black")
    case Direction.Down =>
      require(v.se.color == Color.Black, "left pixel white")
      require(v.sw.color == Color.White, "right pixel black")
    case Direction.Up =>
      require(v.nw.color == Color.Black, "left pixel white")
      require(v.ne.color == Color.White, "right pixel black")
  }
}