package uk.org.lidalia.lang.collection

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlatMapZeroToZeroAsOptionTests extends PropSpec with TableDrivenPropertyChecks {

  val zeroAsOption: ?[Int] = Zero.asInstanceOf[?[Int]]
  
  property("Flat Mapping a Zero to a Zero as ?") {
    val mapped: Zero.type = Zero.flatMap { _: Nothing => zeroAsOption }
    assert(mapped === Zero)
  }

//  property("Flat Mapping a Zero as a ? to a Zero as ?") {
//    val initialZero: ?[Int] = Zero
//    val mapped: ?[Int] = initialZero.flatMap { i: Int => zeroAsOption }
//    assert(mapped === Zero)
//  }

  property("Flat Mapping a Zero as an Iterable to a Zero as ?") {
    val initialZero: Iterable[Int] = Zero
    val mapped: Iterable[Int] = initialZero.flatMap { i: Int => zeroAsOption }
    assert(mapped === Zero)
  }

//  property("Flat Mapping a Zero as a List to a Zero as ?") {
//    val initialZero: List[Int] = Zero
//    val mapped: List[Int] = initialZero.flatMap { i: Int => zeroAsOption }
//    assert(mapped === Zero)
//  }

//  property("Flat Mapping a Zero as a Set to a Zero as ?") {
//    val initialZero: Set[Int] = Zero
//    val mapped: Set[Int] = initialZero.flatMap { i: Int => zeroAsOption }
//    assert(mapped === Zero)
//  }
}
