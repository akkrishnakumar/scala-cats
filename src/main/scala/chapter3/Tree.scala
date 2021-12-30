package chapter3

import cats.Functor

sealed trait Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)

  def leaf[A](value: A): Tree[A] = Leaf(value)

  implicit def treeFunctor: Functor[Tree] =
    new Functor[Tree] {

      override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] =
        fa match {
          case Leaf(value)  => leaf(f(value))
          case Branch(l, r) => branch(map(l)(f), map(r)(f))
        }

    }

}
