package chapter1

trait Show[A] {
  def show(a: A): String
}

object ShowInstances {

  implicit def showCat: Show[Cat] =
    new Show[Cat] {
      override def show(cat: Cat): String =
        s"${cat.name} is a ${cat.age} year old ${cat.color} cat."
    }

}

object Show {
  def justShow[A](a: A)(implicit s: Show[A]): String = ???
  def show[A](a: A)(implicit s: Show[A]): Unit       = println(justShow(a))
}

object ShowSyntax {

  implicit class ShowOps[A](self: A) {
    def show(implicit s: Show[A]): String = s.show(self)
  }
}
