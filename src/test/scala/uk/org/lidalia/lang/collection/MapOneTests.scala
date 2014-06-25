package uk.org.lidalia.lang.collection

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MapOneTests extends PropSpec with TableDrivenPropertyChecks {

  property("Mapping a One") {
    val one: One[Int] = One(1)
    val ten: One[Int] = one.map(_*10)
    assert(ten === One(10))
  }

  property("Mapping a One as a ?") {
    val one: ?[Int] = One(1)
    val ten: ?[Int] = one.map(_*10)
    assert(ten === One(10))
  }

  property("Mapping a One as a NonEmptyIterable") {
    val one: NonEmptyIterable[Int] = One(1)
    val ten: NonEmptyIterable[Int] = one.map(_*10)
    assert(ten === One(10))
  }

  property("Mapping a One as an Iterable") {
    val one: Iterable[Int] = One(1)
    val ten: Iterable[Int] = one.map(_*10)
    assert(ten === One(10))
  }

  property("Mapping a One as a List") {
    val one: List[Int] = One(1)
    val ten: List[Int] = one.map(_*10)
    assert(ten === One(10))
  }

  property("Mapping a One as a Set") {
    val one: Set[Int] = One(1)
    val ten: Set[Int] = one.map(_*10)
    assert(ten === One(10))
  }

  property("Mapping a One as a NonEmptySet") {
    val one: NonEmptySet[Int] = One(1)
    val ten: NonEmptySet[Int] = one.map(_*10)
    assert(ten === One(10))
  }

  property("Mapping a One as a NonEmptyList") {
    val one: NonEmptyList[Int] = One(1)
    val ten: NonEmptyList[Int] = one.map(_*10)
    assert(ten === One(10))
  }
}
