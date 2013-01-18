package de.vorb.depict.tracing

sealed abstract class BinaryTree[T](value: T) {
  def toList: List[T] = value :: Nil
  override def toString: String = value.toString
}

case class Branch[T](left: BinaryTree[T], value: T, right: BinaryTree[T])
    extends BinaryTree[T](value) {
  override def toList: List[T] = left.toList ++ (value :: right.toList)
  override def toString: String =
    "(" + left + ", (" + value + "), " + right + ")"
}

case class Leaf[T](value: T) extends BinaryTree[T](value)

object Main extends App {
  val t = Branch(Branch(Leaf(5), 8, Leaf(1)), 7, Leaf(1))
  println(t.toList)
  println(t)
}