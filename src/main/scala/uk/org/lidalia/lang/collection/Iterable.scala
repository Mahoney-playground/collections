package uk.org.lidalia.lang.collection

object ToZero
object ToOne
object ToOption
object ToNonEmptyIterable
object ToIterable

trait Iterable[+A] {

  type MayBeEmptySelf[+B] <: Iterable[B]
//  type NonEmptySelf[+B] <: NonEmptyIterable[B]
  type Self[+B] <: Iterable[B]

  def head: ?[A]

  def tail: MayBeEmptySelf[A]

  def map[B](f: A => B): Self[B]

  def flatMap(f: A => Zero.type)(implicit d: ToZero.type) = Zero

  def flatMap[B](f: A => Iterable[B])(implicit d: ToIterable.type): Iterable[B]

  def ++[B >: A](other: Iterable[B]): Iterable[B]

  def toList: List[A]
}

trait NonEmptyIterable[+A]
    extends Iterable[A] {
  override type Self[+B] <: NonEmptyIterable[B]
  override def head: One[A]

  def toList: NonEmptyList[A]

  def flatMap[B](f: A => NonEmptyIterable[B])(implicit d: ToNonEmptyIterable.type): NonEmptyIterable[B]
}

trait Set[+A] extends Iterable[A] {
  override type MayBeEmptySelf[+B] <: Set[B]
//  override type NonEmptySelf[+B] <: NonEmptySet[B]
  override type Self[+B] <: Set[B]
}

trait NonEmptySet[+A] extends Set[A] with NonEmptyIterable[A] {
  override type Self[+B] <: NonEmptySet[B]
}

sealed trait ?[+A]
    extends List[A]
    with Set[A] {
  override type MayBeEmptySelf[+B] = ?[B]
//  override type NonEmptySelf[+B] = One[B]
  override type Self[+B] <: ?[B]

  def or[B >: A](default: => B): B

  def flatMap[B](f: A => ?[B])(implicit d: ToOption.type): ?[B]
}

case object Zero extends ?[Nothing] {

  override def prepend[B >: Nothing](value: B): One[B] = One(value)


  override type Self[+B] = Zero.type

  override def head = this
  override def tail = this
  override def map[B](f: Nothing => B) = this
  override def or[B >: Nothing](default: => B) = default

  override def toString = "None"

  override def flatMap[B](f: Nothing => Iterable[B])(implicit d: ToIterable.type) = this

  override def flatMap[B](f: (Nothing) => ?[B])(implicit d: ToOption.type) = this

  override def ++[B >: Nothing](other: Iterable[B]) = other
}

object One {
  def apply[A](a: A) = new One(a)
}

final class One[+A] private (val get: A) extends ?[A] with NonEmptyList[A] with NonEmptySet[A] {

  override type Self[+B] = One[B]

  override def map[B](f: A => B): One[B] = One(f(get))
  override def or[B >: A](default: => B) = get

  override def toString = get.toString

  def prepend[B >: A](value: B): NonEmptyList[B] = new NonEmptyListImpl(value, this)

  def tail = Zero

  override def head = this

  override def flatMap[B](f: A => Iterable[B])(implicit d: ToIterable.type): Iterable[B] = f(get)

  override def flatMap[B](f: (A) => NonEmptyIterable[B])(implicit d: ToNonEmptyIterable.type): NonEmptyIterable[B] = f(get)

  override def flatMap[B](f: (A) => ?[B])(implicit d: ToOption.type): ?[B] = f(get)

  def flatMap[B](f: (A) => One[B]): One[B] = f(get)

  override def ++[B >: A](other: Iterable[B]): Iterable[B] = new NonEmptyListImpl(get, other.toList)

  override def equals(other: Any) = {
    other match {
      case otherOne: One[Any] => otherOne.get == get
      case _ => false
    }
  }


}

object List {
  def apply() = Zero
  def apply[A](a: A): NonEmptyList[A] = NonEmptyList(a)
  def apply[A](a: A, b: A): NonEmptyList[A] = NonEmptyList(a, b)
}

trait List[+A] extends Iterable[A] {

  override type MayBeEmptySelf[+B] <: List[B]
//  override type NonEmptySelf[+B] <: NonEmptyList[B]
  override type Self[+B] <: List[B]

  def prepend[B >: A](value: B): NonEmptyList[B]

  override def toList: List[A] = this

}

object NonEmptyList {
  def apply[A](a: A): NonEmptyList[A] = Zero.prepend(a)
  def apply[A](a: A, b: A) = Zero.prepend(b).prepend(a)
}

trait NonEmptyList[+A] extends List[A] with NonEmptyIterable[A] {

  override type Self[+B] <: NonEmptyList[B]

  override def toList: NonEmptyList[A] = this

}

class NonEmptyListImpl[+A](ahead: A, mytail: List[A]) extends NonEmptyList[A] {

//  override type Self = this.type

  override def flatMap[B](f: (A) => Iterable[B])(implicit d: ToIterable.type ) = {
    (f(ahead) ++ mytail.flatMap(f)).asInstanceOf[NonEmptyList[B]]
  }

//  override type MayBeEmptySelf = this.type

  override def flatMap[B](f: (A) => NonEmptyIterable[B])(implicit d: ToNonEmptyIterable.type ): NonEmptyIterable[B] = ???

  def prepend[B >: A](value: B) = new NonEmptyListImpl(value, this)

  def map[B](f: (A) => B): Self[B] = new NonEmptyListImpl(f(ahead), tail.map(f)).asInstanceOf[Self[B]]

  def ++[B >: A](other: Iterable[B]) = {
    val newTail: Iterable[B] = tail ++ other
    new NonEmptyListImpl[B](ahead, newTail.toList)
  }
  override val head = One(ahead)

  override val tail = mytail.asInstanceOf[MayBeEmptySelf[A]]

  override def equals(other: Any) = {
    other match {
      case otherOne: List[A] =>
        otherOne.head == head && otherOne.tail == tail
      case _ => false
    }
  }
}

//object Test {
//  def main(args: Array[String]) {
//    val a: One[String] = One("1")
//    val mappedOne: One[Int] = a.map(_.toInt)
//    assert(mappedOne == One(1))
//    val headOne: One[String] = a.head
//    assert(headOne == a)
//    val tailOne: None.type = a.tail
//    assert(tailOne == None)
//    val emptyFlatMappedOne = a.flatMap(_ => None)
//    assert(emptyFlatMappedOne == None)
//    val nonEmptyFlatMappedOne = a.flatMap(x => One(x.toInt))
//    assert(nonEmptyFlatMappedOne == One(1))
//
//
//    val asOption: ?[String] = a
//    val mappedOption: ?[Int] = asOption.map(_.toInt)
//    assert(mappedOption == One(1))
//    val headOption: ?[String] = asOption.head
//    assert(headOption == asOption)
//    val tailOption: ?[String] = asOption.tail
//    assert(tailOption == None)
//    val emptyFlatMappedOption: ?[Int] = asOption.flatMap(_ => None)
//    assert(emptyFlatMappedOption == None)
//    val nonEmptyFlatMappedOption: ?[Int] = asOption.flatMap(x => One(x.toInt))
//    assert(nonEmptyFlatMappedOption == One(1))
//
//    val asIterable: Iterable[String] = a
//    val mappedIterable: Iterable[Int] = asIterable.map(_.toInt)
//    assert(mappedIterable == One(1))
//    val headIterable: ?[String] = asIterable.head
//    assert(headIterable == asIterable)
//    val tailIterable: Iterable[String] = asIterable.tail
//    assert(tailIterable == None)
//    val emptyFlatMappedIterable: Iterable[Int] = asIterable.flatMap(_ => None)
//    assert(emptyFlatMappedIterable == None)
//    val oneFlatMappedIterable: Iterable[Int] = asIterable.flatMap(x => One(x.toInt))
//    assert(oneFlatMappedIterable == One(1))
//    val multiFlatMappedIterable: Iterable[Int] = asIterable.flatMap(x => List(1, 2))
//    assert(oneFlatMappedIterable == List(1, 2))
//
//
//    val asNonEmptyIterable: NonEmptyIterable[String] = a
//    val nonEmptyHead: One[String] = asNonEmptyIterable.head
//    val mayBeEmptyHead: ?[String] = asIterable.head
//
//
//
//    val emptyList = List()
//    assert(emptyList == None)
//    assert(emptyList.head == None)
//    assert(emptyList.tail == None)
//    val singleElementList = List("World")
//    assert(singleElementList == One("World"))
//    assert(singleElementList.head == One("World"))
//    assert(singleElementList.tail == None)
//    val twoElementList = List("Hello", "World")
//    assert(twoElementList.head == One("Hello"))
//    assert(twoElementList.tail == singleElementList)
//  }
//}
