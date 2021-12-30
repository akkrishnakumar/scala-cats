package chapter3

import utils.BaseSpec
import cats.syntax.functor._

class Chapter3ExerciseSpec extends BaseSpec {

  test("Implement Functor for Tree") {
    Tree.leaf(100).map(_ * 2) shouldEqual Leaf(200)
    Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2) shouldEqual Branch(Leaf(20), Leaf(40))
  }

}
