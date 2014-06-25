package uk.org.lidalia.lang.collection

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlatMapOneToZeroTests extends PropSpec with TableDrivenPropertyChecks {

  property("Flat Mapping a One to a Zero") {
    val one: One[Int] = One(1)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a One as a ? to a Zero") {
    val one: ?[Int] = One(1)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a One as a NonEmptyIterable to a Zero") {
    val one: NonEmptyIterable[Int] = One(1)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a One as an Iterable to a Zero") {
    val one: Iterable[Int] = One(1)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a One as a List to a Zero") {
    val one: List[Int] = One(1)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a One as a Set to a Zero") {
    val one: Set[Int] = One(1)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a One as a NonEmptySet to a Zero") {
    val one: NonEmptySet[Int] = One(1)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }

  property("Flat Mapping a One as a NonEmptyList to a Zero") {
    val one: NonEmptyList[Int] = One(1)
    val mapped: Zero.type = one.flatMap { i: Int => Zero }
    assert(mapped === Zero)
  }
}
