package chapter4

import cats.Eval
import cats.data.Writer
import cats.Id
import cats.syntax.writer._
import cats.instances.vector._
import cats.syntax.applicative._
import cats.data.Reader
import cats.data.State
import cats.Monad
import chapter3.Tree._

object Chapter4 {

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    foldRightEval(as, Eval.now(acc))((a, b) => b.map(fn(a, _))).value

  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
      case Nil =>
        acc
    }

  def slowly[A](body: => A) =
    try body
    finally Thread.sleep(0)

  type Logged[A] = Writer[Vector[String], A]

  def factorial(n: Int): Logged[Int] =
    for {
      ans <- if (n == 0) 1.pure[Logged] else slowly(factorial(n - 1).map(_ * n))
      _   <- Vector(s"fact $n $ans").tell
    } yield ans

  final case class DB(
    userNames: Map[Int, String],
    passwords: Map[String, String]
  )

  type DBReader[A] = Reader[DB, A]

  def findUsername(userId: Int): DBReader[Option[String]] =
    Reader(db => db.userNames.get(userId))

  def checkPassword(username: String, password: String): DBReader[Boolean] =
    Reader(db => db.passwords.getOrElse(username, "") == password)

  def checkLogin(userId: Int, pass: String): DBReader[Boolean] =
    for {
      userOpt <- findUsername(userId)
      result <-
        userOpt
          .map(uName => checkPassword(uName, pass))
          .getOrElse(false.pure[DBReader])
    } yield result

  type CalcState[A] = State[List[Int], A]

  def operator(f: (Int, Int) => Int): CalcState[Int] =
    State {
      case a :: b :: tail =>
        val ans = f(a, b)
        (ans :: tail, ans)
      case _ => throw new RuntimeException("Exception")
    }

  def operand(num: Int): CalcState[Int] =
    State(stack => (num :: stack, num))

  def evalOne(sym: String): CalcState[Int] =
    sym match {
      case "+" => operator(_ + _)
      case "*" => operator(_ * _)
      case num => operand(num.toInt)
    }

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) { (a, b) => a.flatMap(_ => evalOne(b)) }

  implicit def treeMonad =
    new Monad[Tree] {

      override def pure[A](x: A): Tree[A] =
        Tree.leaf(x)

      override def flatMap[A, B](fa: Tree[A])(f: A => Tree[B]): Tree[B] =
        fa match {
          case Leaf(value)         => f(value)
          case Branch(left, right) => Tree.branch(flatMap(left)(f), flatMap(right)(f))
        }

      override def tailRecM[A, B](a: A)(f: A => Tree[Either[A, B]]): Tree[B] =
        flatMap(f(a)) {
          case Left(value)  => tailRecM(value)(f)
          case Right(value) => Leaf(value)
        }

    }

}
