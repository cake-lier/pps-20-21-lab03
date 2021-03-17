package lab03

import u02.Modules.Person
import u02.Modules.Person.Teacher
import u02.Optionals.Option
import u02.Optionals.Option.{None, Some}
import u03.Lists.List
import u03.Lists.List.{Cons, Nil, append}

import scala.annotation.tailrec

object Lists {
    @tailrec
    def drop[A](l: List[A], n: Int): List[A] = l match {
        case Cons(_, t) if n > 0 => drop(t, n - 1)
        case _ => l
    }

    def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = l match {
        case Cons(h, t) => append(f(h), flatMap(t)(f))
        case _ => Nil()
    }

    def map[A, B](l: List[A])(f: A => B): List[B] = flatMap(l)(e => Cons(f(e), Nil()))

    def filter[A](l: List[A])(p: A => Boolean): List[A] = flatMap(l) {
        case e if p(e) => Cons(e, Nil())
        case _ => Nil()
    }

    def max(l: List[Int]): Option[Int] = l match {
        case Cons(h, t) => max(t) match {
            case Some(a) if h < a => Some(a)
            case _ => Some(h)
        }
        case _ => None()
    }

    def extractCourses(l: List[Person]): List[String] = flatMap(l) {
        case Teacher(_, c) => Cons(c, Nil())
        case _ => Nil()
    }

    @tailrec
    def foldLeft[A, B](l: List[A])(seed: B)(accumulator: (B, A) => B): B = l match {
        case Cons(h, t) => foldLeft(t)(accumulator(seed, h))(accumulator)
        case _ => seed
    }

    def foldRight[A, B](l: List[A])(seed: B)(accumulator: (A, B) => B): B = l match {
        case Cons(h, t) => accumulator(h, foldRight(t)(seed)(accumulator))
        case _ => seed
    }
}
