package chapter1

import utils.BaseSpec
import Printable._
import PrintableInstances._
import PrintableSyntax._
import ShowSyntax._
import ShowInstances._
import cats.instances.option._
import cats.syntax.eq._
import cats.kernel.Eq

class Chapter1ExerciseSpec extends BaseSpec {

  val tom      = Cat("Tom", 10, "Blue")
  val garfield = Cat("Garfield", 11, "Golden Brown")

  implicit def catEqual: Eq[Cat] = ???

  test("Implement Printable") {
    format(10) shouldEqual "10"
    format("Akhil") shouldEqual "Akhil"
  }

  test("Implement instance of Printable for Cat") {
    format(tom) shouldEqual "Tom is a 10 year old Blue cat."
  }

  test("Implement PrintableSyntax(Extension Class)") {
    tom.format shouldEqual "Tom is a 10 year old Blue cat."
  }

  test("Convert Printable to Show") {
    tom.show shouldEqual "Tom is a 10 year old Blue cat."
  }

  ignore("Implement Eq for Cat") {
    // Didn't implement due to some discripency
  }

}
