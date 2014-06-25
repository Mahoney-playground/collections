package uk.org.lidalia.lang.collection

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlatMapNonEmptyListToZeroTests extends PropSpec with TableDrivenPropertyChecks {

  property("Flat Mapping a NonEmptyList to a Zero") {
    val one: NonEmptyList[Int] = NonEmptyList(1, 2)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a NonEmptyList as a NonEmptyIterable to a Zero") {
    val one: NonEmptyIterable[Int] = NonEmptyList(1, 2)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a NonEmptyList as an Iterable to a Zero") {
    val one: Iterable[Int] = NonEmptyList(1, 2)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a NonEmptyList as a List to a Zero") {
    val one: List[Int] = NonEmptyList(1, 2)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }
}
