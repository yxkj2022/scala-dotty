package scala
import reflect.ClassTag

/** An immutable array. An `IArray[T]` has the same representation as an `Array[T]`,
 *  but it cannot be updated. Unlike regular arrays, immutable arrays are covariant.
 */
object opaques
  opaque type IArray[+T] = Array[_ <: T]

  /** Defines extension methods for immutable arrays */
  given arrayOps: Object with

    /** The selection operation on an immutable array.
      *
      *  @param arr the immutable array
      *  @param n   the index of the element to select
      *  @return    the element of the array at the given index
      */
    def (arr: IArray[Byte]) apply (n: Int): Byte = arr.asInstanceOf[Array[Byte]].apply(n)
    def (arr: IArray[Short]) apply (n: Int): Short = arr.asInstanceOf[Array[Short]].apply(n)
    def (arr: IArray[Char]) apply (n: Int): Char = arr.asInstanceOf[Array[Char]].apply(n)
    def (arr: IArray[Int]) apply (n: Int): Int = arr.asInstanceOf[Array[Int]].apply(n)
    def (arr: IArray[Long]) apply (n: Int): Long = arr.asInstanceOf[Array[Long]].apply(n)
    def (arr: IArray[Float]) apply (n: Int): Float = arr.asInstanceOf[Array[Float]].apply(n)
    def (arr: IArray[Double]) apply (n: Int): Double = arr.asInstanceOf[Array[Double]].apply(n)
    def [T <: Object](arr: IArray[T]) apply (n: Int): T = arr.asInstanceOf[Array[T]].apply(n)
    def [T](arr: IArray[T]) apply (n: Int): T = arr.asInstanceOf[Array[T]].apply(n)

    /** The number of elements in an immutable array
      *  @param arr  the immutable array
      */
    def (arr: IArray[Byte]) length: Int = arr.asInstanceOf[Array[Byte]].length
    def (arr: IArray[Short]) length: Int = arr.asInstanceOf[Array[Short]].length
    def (arr: IArray[Char]) length: Int = arr.asInstanceOf[Array[Char]].length
    def (arr: IArray[Int]) length: Int = arr.asInstanceOf[Array[Int]].length
    def (arr: IArray[Long]) length: Int = arr.asInstanceOf[Array[Long]].length
    def (arr: IArray[Float]) length: Int = arr.asInstanceOf[Array[Float]].length
    def (arr: IArray[Double]) length: Int = arr.asInstanceOf[Array[Double]].length
    def (arr: IArray[Object]) length: Int = arr.asInstanceOf[Array[Object]].length
    def [T](arr: IArray[T]) length: Int = arr.asInstanceOf[Array[T]].length

    /** All the methods on Array[T] that don't mutate in-place can be used with IArray[T].
     */
    def [T, U >: T: ClassTag](arr: IArray[T]) ++(that: IArray[U]): IArray[U] =
      (genericArrayOps(arr) ++ that.asInstanceOf[Array[U]]).asInstanceOf[IArray[U]]

    def [T](arr: IArray[T]) contains(elem: T): Boolean =
      genericArrayOps(arr.asInstanceOf[Array[T]]).contains(elem)

    def [T](arr: IArray[T]) count(p: T => Boolean): Int =
      genericArrayOps(arr).count(p)

    def [T](arr: IArray[T]) drop(n: Int): IArray[T] =
      genericArrayOps(arr).drop(n).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) dropRight(n: Int): IArray[T] =
      genericArrayOps(arr).dropRight(n).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) dropWhile(p: T => Boolean): IArray[T] =
      genericArrayOps(arr).dropWhile(p).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) exists(p: T => Boolean): IArray[T] =
      genericArrayOps(arr).exists(p).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) filter(p: T => Boolean): IArray[T] =
      genericArrayOps(arr).filter(p).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) filterNot(p: T => Boolean): IArray[T] =
      genericArrayOps(arr).filterNot(p).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) find(p: T => Boolean): Option[T] =
      genericArrayOps(arr).find(p)

    def [T, U: ClassTag](arr: IArray[T]) flatMap(f: T => IterableOnce[U]): IArray[U] =
      genericArrayOps(arr).flatMap(f).asInstanceOf[IArray[U]]

    def [T, U: ClassTag](arr: IArray[T]) flatten(given T => Iterable[U]): IArray[U] =
      genericArrayOps(arr).flatten.asInstanceOf[IArray[U]]

    def [T, U >: T: ClassTag](arr: IArray[T]) fold(z: U)(op: (U, U) => U): U =
      genericArrayOps(arr).fold(z)(op)

    def [T, U: ClassTag](arr: IArray[T]) foldLeft(z: U)(op: (U, T) => U): U =
      genericArrayOps(arr).foldLeft(z)(op)

    def [T, U: ClassTag](arr: IArray[T]) foldRight(z: U)(op: (T, U) => U): U =
      genericArrayOps(arr).foldRight(z)(op)

    def [T](arr: IArray[T]) forall(p: T => Boolean): Boolean =
      genericArrayOps(arr).forall(p)

    def [T, U](arr: IArray[T]) foreach(f: T => U): Unit =
      genericArrayOps(arr).foreach(f)

    def [T](arr: IArray[T]) head: T =
      genericArrayOps(arr).head

    def [T](arr: IArray[T]) headOption: Option[T] =
      genericArrayOps(arr).headOption

    def [T](arr: IArray[T]) indexOf(elem: T, from: Int = 0): Int =
      genericArrayOps(arr.asInstanceOf[Array[T]]).indexOf(elem, from)

    def [T](arr: IArray[T]) indexWhere(p: T => Boolean, from: Int = 0): Int =
      genericArrayOps(arr).indexWhere(p, from)

    def [T](arr: IArray[T]) indices: Range =
      genericArrayOps(arr).indices

    def [T](arr: IArray[T]) init: IArray[T] =
      genericArrayOps(arr).init.asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) isEmpty: Boolean =
      genericArrayOps(arr).isEmpty

    def [T](arr: IArray[T]) iterator: Iterator[T] =
      genericArrayOps(arr).iterator

    def [T](arr: IArray[T]) last: T =
      genericArrayOps(arr).last

    def [T](arr: IArray[T]) lastOption: Option[T] =
      genericArrayOps(arr).lastOption

    def [T](arr: IArray[T]) lastIndexOf(elem: T, end: Int = arr.length - 1): Int =
      genericArrayOps(arr.asInstanceOf[Array[T]]).lastIndexOf(elem, end)

    def [T](arr: IArray[T]) lastIndexWhere(p: T => Boolean, end: Int = arr.length - 1): Int =
      genericArrayOps(arr).lastIndexWhere(p, end)

    def [T, U: ClassTag](arr: IArray[T]) map(f: T => U): IArray[U] =
      genericArrayOps(arr).map(f).asInstanceOf[IArray[U]]

    def [T](arr: IArray[T]) nonEmpty: Boolean =
      genericArrayOps(arr).nonEmpty

    def [T](arr: IArray[T]) partition(p: T => Boolean): (IArray[T], IArray[T]) =
      genericArrayOps(arr).partition(p) match {
        case (x, y) => (x.asInstanceOf[IArray[T]], y.asInstanceOf[IArray[T]])
      }

    def [T](arr: IArray[T]) reverse: IArray[T] =
      genericArrayOps(arr).reverse.asInstanceOf[IArray[T]]

    def [T, U >: T: ClassTag](arr: IArray[T]) scan(z: U)(op: (U, U) => U): IArray[U] =
      genericArrayOps(arr).scan(z)(op).asInstanceOf[IArray[U]]

    def [T, U: ClassTag](arr: IArray[T]) scanLeft(z: U)(op: (U, T) => U): IArray[U] =
      genericArrayOps(arr).scanLeft(z)(op).asInstanceOf[IArray[U]]

    def [T, U: ClassTag](arr: IArray[T]) scanRight(z: U)(op: (T, U) => U): IArray[U] =
      genericArrayOps(arr).scanRight(z)(op).asInstanceOf[IArray[U]]

    def [T](arr: IArray[T]) size: Int =
      arr.length

    def [T](arr: IArray[T]) slice(from: Int, until: Int): IArray[T] =
      genericArrayOps(arr).slice(from, until).asInstanceOf[IArray[T]]

    def [T, U: ClassTag](arr: IArray[T]) sortBy(f: T => U)(given math.Ordering[U]): IArray[T] =
      genericArrayOps(arr).sortBy(f).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) sortWith(f: (T, T) => Boolean): IArray[T] =
      genericArrayOps(arr).sortWith(f).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) sorted(given math.Ordering[T]): IArray[T] =
      genericArrayOps(arr).sorted.asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) span(p: T => Boolean): (IArray[T], IArray[T]) =
      genericArrayOps(arr).span(p) match {
        case (x, y) => (x.asInstanceOf[IArray[T]], y.asInstanceOf[IArray[T]])
      }

    def [T](arr: IArray[T]) splitAt(n: Int): (IArray[T], IArray[T]) =
      genericArrayOps(arr).splitAt(n) match {
        case (x, y) => (x.asInstanceOf[IArray[T]], y.asInstanceOf[IArray[T]])
      }

    def [T, U >: T: ClassTag](arr: IArray[T]) startsWith(that: IArray[U], offset: Int = 0): Boolean =
      genericArrayOps(arr).startsWith(that.asInstanceOf[Array[U]])

    def [T](arr: IArray[T]) tail: IArray[T] =
      genericArrayOps(arr).tail.asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) take(n: Int): IArray[T] =
      genericArrayOps(arr).take(n).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) takeRight(n: Int): IArray[T] =
      genericArrayOps(arr).takeRight(n).asInstanceOf[IArray[T]]

    def [T](arr: IArray[T]) takeWhile(p: T => Boolean): IArray[T] =
      genericArrayOps(arr).takeWhile(p).asInstanceOf[IArray[T]]

    def [U: ClassTag, V: ClassTag](arr: IArray[(U, V)]) unzip: (IArray[U], IArray[V]) =
      genericArrayOps(arr).unzip match {
        case (x, y) => (x.asInstanceOf[IArray[U]], y.asInstanceOf[IArray[V]])
      }

    def [T, U: ClassTag](arr: IArray[T]) zip(that: IArray[U]): IArray[(T, U)] =
      genericArrayOps(arr).zip(that).asInstanceOf[IArray[(T, U)]]
end opaques

type IArray[+T] = opaques.IArray[T]

object IArray {

  /** An immutable array of length 0.
   */
  def empty[T: ClassTag]: IArray[T] = new Array[T](0).asInstanceOf

  def emptyBooleanIArray = Array.emptyBooleanArray.asInstanceOf[IArray[Boolean]]
  def emptyByteIArray    = Array.emptyByteArray.asInstanceOf[IArray[Byte]]
  def emptyCharIArray    = Array.emptyCharArray.asInstanceOf[IArray[Char]]
  def emptyDoubleIArray  = Array.emptyDoubleArray.asInstanceOf[IArray[Double]]
  def emptyFloatIArray   = Array.emptyFloatArray.asInstanceOf[IArray[Float]]
  def emptyIntIArray     = Array.emptyIntArray.asInstanceOf[IArray[Int]]
  def emptyLongIArray    = Array.emptyLongArray.asInstanceOf[IArray[Long]]
  def emptyShortIArray   = Array.emptyShortArray.asInstanceOf[IArray[Short]]
  def emptyObjectIArray  = Array.emptyObjectArray.asInstanceOf[IArray[Object]]

  /** An immutable array with given elements.
   */
  inline def apply[T](xs: =>T*)(given ct: => ClassTag[T]): IArray[T] = Array(xs: _*).asInstanceOf
  inline def apply(x: Boolean, xs: =>Boolean*): IArray[Boolean] = Array(x, xs: _*).asInstanceOf
  inline def apply(x: Byte, xs: =>Byte*): IArray[Byte] = Array(x, xs: _*).asInstanceOf
  inline def apply(x: Short, xs: =>Short*): IArray[Short] = Array(x, xs: _*).asInstanceOf
  inline def apply(x: Char, xs: =>Char*): IArray[Char] = Array(x, xs: _*).asInstanceOf
  inline def apply(x: Int, xs: =>Int*): IArray[Int] = Array(x, xs: _*).asInstanceOf
  inline def apply(x: Long, xs: =>Long*): IArray[Long] = Array(x, xs: _*).asInstanceOf
  inline def apply(x: Float, xs: =>Float*): IArray[Float] = Array(x, xs: _*).asInstanceOf
  inline def apply(x: Double, xs: =>Double*): IArray[Double] = Array(x, xs: _*).asInstanceOf
  inline def apply(x: Unit, xs: =>Unit*): IArray[Unit] = Array(x, xs: _*).asInstanceOf

  /** Concatenates all arrays into a single immutable array.
   *
   *  @param xss the given immutable arrays
   *  @return   the array created from concatenating `xss`
   */
  def concat[T: ClassTag](xss: IArray[T]*): IArray[T] =
    Array.concat[T](xss.asInstanceOf[Seq[Array[T]]]: _*).asInstanceOf

  /** Returns an immutable array that contains the results of some element computation a number
   *  of times. Each element is determined by a separate computation.
   *
   *  @param   n  the number of elements in the array
   *  @param   elem the element computation
   */
  def fill[T: ClassTag](n: Int)(elem: => T): IArray[T] =
    Array.fill(n)(elem).asInstanceOf

  /** Returns a two-dimensional immutable array that contains the results of some element computation a number
   *  of times. Each element is determined by a separate computation.
   *
   *  @param   n1  the number of elements in the 1st dimension
   *  @param   n2  the number of elements in the 2nd dimension
   *  @param   elem the element computation
   */
  def fill[T: ClassTag](n1: Int, n2: Int)(elem: => T): IArray[IArray[T]] =
    Array.fill(n1, n2)(elem).asInstanceOf

  /** Returns a three-dimensional immutable array that contains the results of some element computation a number
   *  of times. Each element is determined by a separate computation.
   *
   *  @param   n1  the number of elements in the 1st dimension
   *  @param   n2  the number of elements in the 2nd dimension
   *  @param   n3  the number of elements in the 3nd dimension
   *  @param   elem the element computation
   */
  def fill[T: ClassTag](n1: Int, n2: Int, n3: Int)(elem: => T): IArray[IArray[IArray[T]]] =
    Array.fill(n1, n2, n3)(elem).asInstanceOf

  /** Returns a four-dimensional immutable array that contains the results of some element computation a number
   *  of times. Each element is determined by a separate computation.
   *
   *  @param   n1  the number of elements in the 1st dimension
   *  @param   n2  the number of elements in the 2nd dimension
   *  @param   n3  the number of elements in the 3nd dimension
   *  @param   n4  the number of elements in the 4th dimension
   *  @param   elem the element computation
   */
  def fill[T: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int)(elem: => T): IArray[IArray[IArray[IArray[T]]]] =
    Array.fill(n1, n2, n3, n4)(elem).asInstanceOf

  /** Returns a five-dimensional immutable array that contains the results of some element computation a number
   *  of times. Each element is determined by a separate computation.
   *
   *  @param   n1  the number of elements in the 1st dimension
   *  @param   n2  the number of elements in the 2nd dimension
   *  @param   n3  the number of elements in the 3nd dimension
   *  @param   n4  the number of elements in the 4th dimension
   *  @param   n5  the number of elements in the 5th dimension
   *  @param   elem the element computation
   */
  def fill[T: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int, n5: Int)(elem: => T): IArray[IArray[IArray[IArray[IArray[T]]]]] =
    Array.fill(n1, n2, n3, n4, n5)(elem).asInstanceOf

  /** Returns an immutable array containing values of a given function over a range of integer
   *  values starting from 0.
   *
   *  @param  n   The number of elements in the array
   *  @param  f   The function computing element values
   */
  def tabulate[T: ClassTag](n: Int)(f: Int => T): IArray[T] =
    Array.tabulate(n)(f).asInstanceOf

  /** Returns a two-dimensional immutable array containing values of a given function
   *  over ranges of integer values starting from `0`.
   *
   *  @param   n1  the number of elements in the 1st dimension
   *  @param   n2  the number of elements in the 2nd dimension
   *  @param   f   The function computing element values
   */
  def tabulate[T: ClassTag](n1: Int, n2: Int)(f: (Int, Int) => T): IArray[IArray[T]] =
    Array.tabulate(n1, n2)(f).asInstanceOf

  /** Returns a three-dimensional immutable array containing values of a given function
   *  over ranges of integer values starting from `0`.
   *
   *  @param   n1  the number of elements in the 1st dimension
   *  @param   n2  the number of elements in the 2nd dimension
   *  @param   n3  the number of elements in the 3rd dimension
   *  @param   f   The function computing element values
   */
  def tabulate[T: ClassTag](n1: Int, n2: Int, n3: Int)(f: (Int, Int, Int) => T): IArray[IArray[IArray[T]]] =
    Array.tabulate(n1, n2, n3)(f).asInstanceOf

  /** Returns a four-dimensional immutable array containing values of a given function
   *  over ranges of integer values starting from `0`.
   *
   *  @param   n1  the number of elements in the 1st dimension
   *  @param   n2  the number of elements in the 2nd dimension
   *  @param   n3  the number of elements in the 3rd dimension
   *  @param   n4  the number of elements in the 4th dimension
   *  @param   f   The function computing element values
   */
  def tabulate[T: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int)(f: (Int, Int, Int, Int) => T): IArray[IArray[IArray[IArray[T]]]] =
    Array.tabulate(n1, n2, n3, n4)(f).asInstanceOf

  /** Returns a five-dimensional immutable array containing values of a given function
   *  over ranges of integer values starting from `0`.
   *
   *  @param   n1  the number of elements in the 1st dimension
   *  @param   n2  the number of elements in the 2nd dimension
   *  @param   n3  the number of elements in the 3rd dimension
   *  @param   n4  the number of elements in the 4th dimension
   *  @param   n5  the number of elements in the 5th dimension
   *  @param   f   The function computing element values
   */
  def tabulate[T: ClassTag](n1: Int, n2: Int, n3: Int, n4: Int, n5: Int)(f: (Int, Int, Int, Int, Int) => T): IArray[IArray[IArray[IArray[IArray[T]]]]] =
    Array.tabulate(n1, n2, n3, n4, n5)(f).asInstanceOf

  /** Returns an immutable array containing a sequence of increasing integers in a range.
   *
   *  @param start  the start value of the array
   *  @param end    the end value of the array, exclusive (in other words, this is the first value '''not''' returned)
   *  @return  the immutable array with values in range `start, start + 1, ..., end - 1`
   *  up to, but excluding, `end`.
   */
  def range(start: Int, end: Int): IArray[Int] = Array.range(start, end).asInstanceOf

  /** Returns an immutable array containing equally spaced values in some integer interval.
   *
   *  @param start the start value of the array
   *  @param end   the end value of the array, exclusive (in other words, this is the first value '''not''' returned)
   *  @param step  the increment value of the array (may not be zero)
   *  @return      the immutable array with values in `start, start + step, ...` up to, but excluding `end`
   */
  def range(start: Int, end: Int, step: Int): IArray[Int] = Array.range(start, end, step).asInstanceOf

  /** Returns an immutable array containing repeated applications of a function to a start value.
   *
   *  @param start the start value of the array
   *  @param len   the number of elements returned by the array
   *  @param f     the function that is repeatedly applied
   *  @return      the immutable array returning `len` values in the sequence `start, f(start), f(f(start)), ...`
   */
  def iterate[T: ClassTag](start: T, len: Int)(f: T => T): IArray[T] = Array.iterate(start, len)(f).asInstanceOf

  /** Returns a decomposition of the array into a sequence. This supports
   *  a pattern match like `{ case IArray(x,y,z) => println('3 elements')}`.
   *
   *  @param x the selector value
   *  @return  sequence wrapped in a [[scala.Some]], if `x` is a Seq, otherwise `None`
   */
   def unapplySeq[T](x: IArray[T]) = Array.unapplySeq[T](x.asInstanceOf[Array[T]])
}