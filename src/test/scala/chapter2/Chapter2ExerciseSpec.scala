package chapter2

import utils.BaseSpec
import Chapter2._

class Chapter2ExerciseSpec extends BaseSpec {

  test("Implement Semigroup, Monoid for Boolean And") {
    booleanAndMonoid.combine(true, true) shouldEqual true
    booleanAndMonoid.combine(false, true) shouldEqual false
    booleanAndMonoid.combine(false, false) shouldEqual false
  }

  test("Implement Semigroup, Monoid for Boolean Or") {
    booleanOrMonoid.combine(true, false) shouldEqual true
    booleanOrMonoid.combine(true, true) shouldEqual true
    booleanOrMonoid.combine(false, false) shouldEqual false
  }

  test("Implement Semigroup, Monoid for Boolean Xor") {
    booleanXorMonoid.combine(true, false) shouldEqual true
    booleanXorMonoid.combine(true, true) shouldEqual false
    booleanXorMonoid.combine(false, false) shouldEqual false
    booleanXorMonoid.combine(false, true) shouldEqual true
  }

  test("Implement Semigroup, Monoid for Boolean Xnor") {
    booleanXnorMonoid.combine(true, true) shouldEqual true
    booleanXnorMonoid.combine(true, false) shouldEqual false
    booleanXnorMonoid.combine(false, true) shouldEqual false
    booleanXnorMonoid.combine(false, false) shouldEqual true
  }

  ignore("Implement all set for Monoid") {
    // Skipped
  }

}
