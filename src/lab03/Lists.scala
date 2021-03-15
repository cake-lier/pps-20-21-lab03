package lab03

import u03.Lists.List.{Cons, Nil}
import u03.Lists.List

import scala.annotation.tailrec

object Lists {
    @tailrec
    def drop[A](l: List[A], n: Int): List[A] = l match {
        case Cons(_, t) if n > 0 => drop(t, n - 1)
        case Cons(h, t) => Cons(h, t)
        case Nil() => Nil()
    }
}
