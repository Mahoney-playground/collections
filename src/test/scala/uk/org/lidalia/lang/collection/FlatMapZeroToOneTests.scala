package uk.org.lidalia.lang.collection

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlatMapZeroToOneTests extends PropSpec with TableDrivenPropertyChecks {

  property("Flat Mapping a Zero to a One") {
    val mapped: Zero.type = Zero.flatMap { _: Nothing => One(1) }

    assert(mapped === Zero)
  }

//  property("Flat Mapping a Zero as a ? to a One") {
//    val initialZero: ?[Int] = Zero
//    val mapped: ?[Int] = initialZero.flatMap { i: Int => One(i*2) }
//    assert(mapped === Zero)
//  }

  property("Flat Mapping a Zero as an Iterable to a One") {
    val initialZero: Iterable[Int] = Zero
    val mapped: Iterable[Int] = initialZero.flatMap { i: Int => One(i*2) }
    assert(mapped === Zero)
  }

//  property("Flat Mapping a Zero as a List to a One") {
//    val initialZero: List[Int] = Zero
//    val mapped: List[Int] = initialZero.flatMap { i: Int => One(i*2) }
//    assert(mapped === Zero)
//  }

//  property("Flat Mapping a Zero as a Set to a One") {
//    val initialZero: Set[Int] = Zero
//    val mapped: Set[Int] = initialZero.flatMap { i: Int => One(i*2) }
//    assert(mapped === Zero)
//  }
}
