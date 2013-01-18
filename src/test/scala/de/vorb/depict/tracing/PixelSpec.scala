package de.vorb.depict.tracing

import org.scalatest.FunSpec
import java.awt.image.BufferedImage

class PixelSpec extends FunSpec {
  implicit val img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
  val px = Pixel(0, 0)
  
  describe("A Pixel") {
    it ("should have a pixel to its right that has it to the left") {
      assert(px.e.w == px)
    }
    it ("should have a pixel to its left that has it to the right") {
      assert(px.w.e == px)
    }
    it ("should have a pixel under it that has it over it") {
      assert(px.s.n == px)
    }
    it ("should have a pixel over it that has it under it") {
      assert(px.n.s == px)
    }
  }
}