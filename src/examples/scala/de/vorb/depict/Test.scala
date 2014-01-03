package de.vorb.depict

import javax.imageio.ImageIO
import java.io.File
import java.awt.image.BufferedImage

object ColorChangeTest extends App {
  val srcFile = ImageIO.read(new File("src/examples/resources/source.png"))
  implicit val source = new Bitmap(srcFile)
  val paths = Potrace.findAllPaths()
  paths.foreach(println)
}
