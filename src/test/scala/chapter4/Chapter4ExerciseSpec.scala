package chapter4

import utils.BaseSpec
import chapter4.Tree._
import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.Monad
import Chapter4._

class Chapter4ExerciseSpec extends BaseSpec {

  test("Define map method in Monad") {
    // Implemented trait - Monad
  }

  test("Define map, flatMap and pure for Id Monad") {
    // Implemented trait - Id
  }

  test("Safer Folding using Eval") {
    // Shouldn't cause stack overflow
    foldRight((1 to 100000).toList, 0L)(_ + _)
  }

  test("Use Writer to log factorial intermediate steps") {
    val expected = Vector(
      "fact 0 1",
      "fact 1 1",
      "fact 2 2",
      "fact 3 6",
      "fact 4 24",
      "fact 5 120"
    )
    val (logs, fact) = factorial(5).run

    fact shouldEqual 120
    logs shouldEqual expected

    // Capture logs in right sequence when process run in parallel.
    // Await.result(Future.sequence(Vector(
    //   Future(factorial(5)),
    //   Future(factorial(5))
    // )).map(_.map(_.written)), 5.seconds)
  }

  test("Check logins of user using steps sequenced by Reader Monad") {
    val users     = Map(1 -> "dade", 2 -> "kate", 3 -> "margo")
    val passwords = Map("dade" -> "zerocool", "kate" -> "acidburn", "margo" -> "secret")
    val db        = DB(users, passwords)

    checkLogin(1, "zerocool").run(db) shouldEqual true
    checkLogin(4, "davinci").run(db) shouldEqual false
  }

  test("Post-Order Calculator (State Monad)") {
    val smallProgram = evalAll(List("1", "2", "+", "3", "*"))
    val biggerProgram = for {
      _   <- evalAll(List("1", "2", "+"))
      _   <- evalAll(List("3", "4", "+"))
      ans <- evalOne("*")
    } yield ans

    smallProgram.runA(Nil).value shouldEqual 9
    biggerProgram.runA(Nil).value shouldEqual 21
  }

  test("Branching out further for Monad") {
    val result = branch(leaf(100), leaf(200)).flatMap(x => branch(leaf(x - 1), leaf(x + 1)))
    result shouldEqual branch(branch(leaf(99), leaf(101)), branch(leaf(199), leaf(201)))
  }

}
