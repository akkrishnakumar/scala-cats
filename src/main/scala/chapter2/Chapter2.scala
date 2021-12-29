package chapter2

import javax.swing.text.StyledEditorKit.BoldAction
import org.scalactic.Bool

object Chapter2 {

  def booleanAndMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      override def empty(value: Boolean): Boolean           = true
      override def combine(a: Boolean, b: Boolean): Boolean = a && b
    }

  def booleanOrMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      override def empty(value: Boolean): Boolean           = false
      override def combine(a: Boolean, b: Boolean): Boolean = a || b
    }

  /**
    * Eg: "This morning I can go to school or I can stay home" is exclusive.
    * Either option may be true but not both.
    */
  def booleanXorMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      override def empty(value: Boolean): Boolean           = false
      override def combine(a: Boolean, b: Boolean): Boolean = (a && !b) || (!a && b)
    }

  def booleanXnorMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      override def empty(value: Boolean): Boolean           = true
      override def combine(a: Boolean, b: Boolean): Boolean = (!a || b) && (a || !b)
    }

}
