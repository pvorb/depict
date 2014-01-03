package de.vorb.depict

case class Path(val points: Vector[Point[Int]]) {
  lazy val lines = {
    val edges = (points.sliding(2) map { slide =>
      // map to seq of edges
      Edge(slide(0), slide(1))
    }).toSeq groupBy { edge =>
      // group by edge direction
      edge.direction
    }

    var lines = Map.empty[Int, (Int, Int)]

    edges(EdgeDirection.Up) foreach { edge =>
      lines += edge.to.y -> (-1, edge.to.x)
    }

    edges(EdgeDirection.Down) foreach { edge =>
      val y = edge.from.y
      lines = lines.updated(y, (edge.from.x, lines(y)._2))
    }

    lines
  }

  // sum
  def area: Int =
    (lines.values map {
      case (x0, x1) =>
        x1 - x0
    }).sum

  def invert(implicit img: Bitmap): Unit = {
    lines foreach {
      case (y, (x0, x1)) =>
        img.invertLine(y, x0, x1)
    }
  }

  override def toString = points.mkString("Path(", " -> ", ")")
}
