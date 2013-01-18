package de.vorb.depict

import java.awt.image.BufferedImage
import scala.collection.mutable

package object tracing {
  type Path = mutable.LinkedList[Edge]

  implicit class PathWrapper(path: Path) {
    def isClosed: Boolean = path(0) == path(path.length - 1)
  }

  def getColor(x: Int, y: Int)(implicit img: BufferedImage): Color =
    try {
      if (img.getRGB(x, y) == 0x000000)
        Color.Black
      else
        Color.White
    } catch {
      case _: ArrayIndexOutOfBoundsException => Color.White
    }
}