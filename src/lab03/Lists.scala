package lab03

import u03.Lists.List.{Cons, Nil, append}
import u03.Lists.List
import u02.Optionals.Option
import u02.Optionals.Option.{None, Some}

import scala.annotation.tailrec

object Lists {
    @tailrec
    def drop[A](l: List[A], n: Int): List[A] = l match {
        case Cons(_, t) if n > 0 => drop(t, n - 1)
        case Cons(h, t) => Cons(h, t)
        case _ => Nil()
    }

    def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = l match {
        case Cons(h, t) => append(f(h), flatMap(t)(f))
        case _ => Nil()
    }

    def map[A, B](l: List[A])(f: A => B): List[B] = flatMap(l)(e => Cons(f(e), Nil()))

    def filter[A](l: List[A])(p: A => Boolean): List[A] = flatMap(l)(e => if (p(e)) { Cons(e, Nil()) } else { Nil() })

    def max(l: List[Int]): Option[Int] = l match {
        case Cons(h, t) => max(t) match {
            case Some(a) if h < a => Some(a)
            case _ => Some(h)
        }
        case _ => None()
    }
}
