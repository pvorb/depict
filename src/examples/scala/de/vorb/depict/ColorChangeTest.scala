package de.vorb.depict

import javax.imageio.ImageIO
import java.io.File

object ColorChangeTest extends App {
  val src = ImageIO.read(new File("src/examples/resources/source.png"))
  implicit val img = new Bitmap(src)

  val start = Potrace.nextStartingPoint(Point(0, 0))(img)
  println(start)

  start foreach { start =>
    println(Potrace.getPath(start))
  }
}
