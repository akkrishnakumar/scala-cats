package chapter2

trait Semigroup[A] {
  def empty(value: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def combine(a: A, b: A): A
}

object Monoid {
  def apply[A](implicit m: Monoid[A]): Monoid[A] = m
}
