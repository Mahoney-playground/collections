package uk.org.lidalia.lang.collection

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlatMapOneToOneTests extends PropSpec with TableDrivenPropertyChecks {

  property("Flat Mapping a One to a One") {
    val one: One[Int] = One(1)
    val mapped: One[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === One(10))
  }

  property("Flat Mapping a One as a ? to a One") {
    val one: ?[Int] = One(1)
    val mapped: ?[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === One(10))
  }

  property("Flat Mapping a One as a NonEmptyIterable to a One") {
    val one: NonEmptyIterable[Int] = One(1)
    val mapped: NonEmptyIterable[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === One(10))
  }

  property("Flat Mapping a One as an Iterable to a One") {
    val one: Iterable[Int] = One(1)
    val mapped: Iterable[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === One(10))
  }

  property("Flat Mapping a One as a List to a One") {
    val one: List[Int] = One(1)
    val mapped: List[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === One(10))
  }

  property("Flat Mapping a One as a Set to a One") {
    val one: Set[Int] = One(1)
    val mapped: Set[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === One(10))
  }

  property("Flat Mapping a One as a NonEmptySet to a One") {
    val one: NonEmptySet[Int] = One(1)
    val mapped: NonEmptySet[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === One(10))
  }

  property("Flat Mapping a One as a NonEmptyList to a One") {
    val one: NonEmptyList[Int] = One(1)
    val mapped: NonEmptyList[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === One(10))
  }
}
