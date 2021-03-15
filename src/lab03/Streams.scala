package lab03

import u03.Streams.Stream
import u03.Streams.Stream.{Cons, iterate}

import scala.annotation.tailrec

object Streams {
    @tailrec
    def drop[A](s: Stream[A])(n: Int): Stream[A] = s match {
        case Cons(_, t) if n > 0 => drop(t())(n - 1)
        case _ => s
    }

    def constant[A](k: => A): Stream[A] = iterate(k)(x => x)
}
