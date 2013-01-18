package de.vorb.depict.tracing

import java.awt.image.BufferedImage
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp

class Tracer(source: BufferedImage) {
  implicit val img: BufferedImage = {
    // flip source image vertically to make lower left of the image the
    // origin of the coordinate system
    val at = AffineTransform.getScaleInstance(1, -1)
    at.translate(0, -source.getHeight)
    val op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)

    op.filter(source, null)
  }

  def findPaths: List[Path] = Nil

  val cursor = new Pixel(0, img.getHeight - 1)

  def findNextColorChange(): Option[Edge] = {
    var running = true
    var color = cursor.color

    while (running && cursor.y >= 0 && cursor.color == Color.White) {
      while (running && cursor.x < img.getWidth && cursor.color == Color.White) {
        cursor.x += 1
      }

      if (running) {
        cursor.x = 0
        cursor.y -= 1
        color = cursor.color
      }
    }

    None
  }
}