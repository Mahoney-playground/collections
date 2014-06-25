package uk.org.lidalia.lang.collection

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlatMapZeroToZeroTests extends PropSpec with TableDrivenPropertyChecks {

  property("Flat Mapping a Zero to a Zero") {
    val zero: Zero.type = Zero.flatMap { _: Nothing => Zero }
    assert(zero === Zero)
  }

  property("Flat Mapping a Zero as a ? to a Zero") {
    val initialZero: ?[Int] = Zero
    val zero: Zero.type = initialZero.flatMap { i: Int => Zero }
    assert(zero === Zero)
  }

  property("Flat Mapping a Zero as an Iterable to a Zero") {
    val initialZero: Iterable[Int] = Zero
    val zero: Zero.type = initialZero.flatMap { i: Int => Zero }
    assert(zero === Zero)
  }

  property("Flat Mapping a Zero as a List to a Zero") {
    val initialZero: List[Int] = Zero
    val zero: Zero.type = initialZero.flatMap { i: Int => Zero }
    assert(zero === Zero)
  }

  property("Flat Mapping a Zero as a Set to a Zero") {
    val initialZero: Set[Int] = Zero
    val zero: Zero.type = initialZero.flatMap { i: Int => Zero }
    assert(zero === Zero)
  }
}
