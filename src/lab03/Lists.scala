package lab03

import u03.Lists.List.{Cons, Nil, append}
import u03.Lists.List

import scala.annotation.tailrec

object Lists {
    @tailrec
    def drop[A](l: List[A], n: Int): List[A] = l match {
        case Cons(_, t) if n > 0 => drop(t, n - 1)
        case Cons(h, t) => Cons(h, t)
        case Nil() => Nil()
    }

    def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = l match {
        case Cons(h, t) => append(f(h), flatMap(t)(f))
        case _ => Nil()
    }
}
