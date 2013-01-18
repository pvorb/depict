package de.vorb.depict.tracing

import java.io.File

import scala.swing.{Component, MainFrame, SimpleSwingApplication}

import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JLabel}

object FlipTest extends SimpleSwingApplication {
  val source = ImageIO.read(new File("src/examples/resources/source.png"))

  def top = new MainFrame {
    title = "Flip test"
    contents = Component.wrap(new JLabel(new ImageIcon(new Tracer(source).img)))
  }
}
