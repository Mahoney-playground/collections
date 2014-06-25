package uk.org.lidalia.lang.collection

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlatMapNonEmptyListToOneTests extends PropSpec with TableDrivenPropertyChecks {

  property("Flat Mapping a NonEmptyList to a One") {
    val one: NonEmptyList[Int] = NonEmptyList(1, 2)
    val mapped: NonEmptyList[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === List(10, 20))
  }

  property("Flat Mapping a NonEmptyList as a NonEmptyIterable to a One") {
    val one: NonEmptyIterable[Int] = NonEmptyList(1, 2)
    val mapped: NonEmptyIterable[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === List(10, 20))
  }

  property("Flat Mapping a NonEmptyList as an Iterable to a One") {
    val one: Iterable[Int] = NonEmptyList(1, 2)
    val mapped: Iterable[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === List(10, 20))
  }

  property("Flat Mapping a NonEmptyList as a List to a One") {
    val one: List[Int] = NonEmptyList(1, 2)
    val mapped: List[Int] = one.flatMap { i: Int => One(i*10) }
    assert(mapped === List(10, 20))
  }
}
