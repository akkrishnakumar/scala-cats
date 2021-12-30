package chapter4

object Id {

  type Id[A] = A

  def pure[A](a: A): Id[A] = a

  def flatMap[A, B](a: Id[A])(f: A => Id[B]): Id[B] = f(a)

  def map[A, B](a: Id[A])(f: A => B): Id[B] = flatMap(a)(pure(f(_)))

}
