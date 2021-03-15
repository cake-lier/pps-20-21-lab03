package lab03

import lab03.Lists._
import org.junit.jupiter.api.{Assertions, Test}
import u02.Modules.Person.{Student, Teacher}
import u02.Optionals.Option.{None, Some}
import u03.Lists.List
import u03.Lists.List.{Cons, Nil}

class ListsTest {
    val list1: List[Int] = Cons(10, Cons(20, Cons(30, Nil())))
    val list2: List[Int] = Cons(3,Cons(7,Cons(1,Cons(5, Nil()))))
    val list3: List[String] = Cons("b", Cons("c", Cons("d", Nil())))

    @Test
    def testDrop(): Unit = {
        Assertions.assertEquals(Cons(20, Cons(30, Nil())), drop(list1, 1))
        Assertions.assertEquals(Cons(30, Nil()), drop(list1, 2))
        Assertions.assertEquals(Nil(), drop(list1, 5))
        Assertions.assertEquals(list1, drop(list1, 0))
        Assertions.assertEquals(list1, drop(list1, -2))
    }

    @Test
    def testFlatMap(): Unit = {
        Assertions.assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), flatMap(list1)(v => Cons(v + 1, Nil())))
        Assertions.assertEquals(Cons(11, Cons(12, Cons(21, Cons(22, Cons(31, Cons(32, Nil())))))),
                                flatMap(list1)(v => Cons(v + 1, Cons(v + 2, Nil()))))
        Assertions.assertEquals(Nil(), flatMap(Nil[Int]())(v => Cons(v + 1, Nil())))
        Assertions.assertEquals(Nil(), flatMap(Nil[Int]())(v => Cons(v + 1, list1)))
        Assertions.assertEquals(Nil(), flatMap(list1)(_ => Nil[Int]()))
    }

    @Test
    def testMap(): Unit = {
        Assertions.assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), map(list1)(_ + 1))
        Assertions.assertEquals(Nil(), map(Nil[Int]())(_ + 1))
        Assertions.assertEquals(Cons(Nil(), Cons(Nil(), Cons(Nil(), Nil()))), map(list1)(_ => Nil()))
    }

    @Test
    def testFilter(): Unit = {
        Assertions.assertEquals(Cons(30, Nil()), filter(list1)(_ > 20))
        Assertions.assertEquals(Nil(), filter(list1)(_ > 100))
        Assertions.assertEquals(list1, filter(list1)(_ > 0))
        Assertions.assertEquals(Nil(), filter(Nil[Int]())(_ > 20))
    }

    @Test
    def testMax(): Unit = {
        Assertions.assertEquals(Some(25), max(Cons(10, Cons(25, Cons(20, Nil())))))
        Assertions.assertEquals(Some(10), max(Cons(10, Nil())))
        Assertions.assertEquals(None(), max(Nil()))
    }

    @Test
    def testExtractCourses(): Unit = {
        val student = Student("Bianchi", 1)
        Assertions.assertEquals(Cons("A", Cons("B", Nil())),
                                extractCourses(Cons(Teacher("Rossi", "A"), Cons(student, Cons(Teacher("Verdi", "B"), Nil())))))
        Assertions.assertEquals(Nil(), extractCourses(Nil()))
        Assertions.assertEquals(Nil(), extractCourses(Cons(student, Nil())))
    }

    @Test
    def testFoldLeft(): Unit = {
        Assertions.assertEquals(-16, foldLeft(list2)(0)(_ - _))
        Assertions.assertEquals(0, foldLeft(Nil[Int]())(0)(_ - _))
        Assertions.assertEquals(-1, foldLeft(Cons(1, Nil()))(0)(_ - _))
        Assertions.assertEquals("abcd", foldLeft(list3)("a")(_ + _))
    }

    @Test
    def testFoldRight(): Unit = {
        Assertions.assertEquals(-8, foldRight(list2)(0)(_ - _))
        Assertions.assertEquals(0, foldRight(Nil[Int]())(0)(_ - _))
        Assertions.assertEquals(1, foldRight(Cons(1, Nil()))(0)(_ - _))
        Assertions.assertEquals("bcda", foldRight(list3)("a")(_ + _))
    }
}