package lab03

import org.junit.jupiter.api.{Assertions, Test}
import u03.Lists.List.{Cons, Nil}
import u03.Streams.Stream
import lab03.Streams.{constant, drop, fibonacci}

class StreamsTest {
    @Test
    def testDrop(): Unit = {
        val s = Stream.take(Stream.iterate(0)(_ + 1))(10)
        Assertions.assertEquals(Cons(6, Cons(7, Cons(8, Cons(9, Nil())))), Stream.toList(drop(s)(6)))
        Assertions.assertEquals(Nil(), Stream.toList(drop(s)(11)))
        Assertions.assertEquals(Stream.toList(s), Stream.toList(drop(s)(0)))
        Assertions.assertEquals(Stream.toList(s), Stream.toList(drop(s)(-2)))
    }

    @Test
    def testConstant(): Unit = {
        val strK = "x"
        Assertions.assertEquals(Cons(strK, Cons(strK, Cons(strK, Cons(strK, Cons(strK, Nil()))))),
                                Stream.toList(Stream.take(constant(strK))(5)))
        val nilK = Nil()
        Assertions.assertEquals(Cons(nilK, Cons(nilK, Cons(nilK, Cons(nilK, Cons(nilK, Nil()))))),
                                Stream.toList(Stream.take(constant(nilK))(5)))
    }

    @Test
    def testFibonacci(): Unit = {
        val fibs = fibonacci()
        Assertions.assertEquals(Cons(0, Cons(1, Cons(1, Cons(2, Cons(3, Cons(5, Cons(8, Cons(13, Nil())))))))),
                                Stream.toList(Stream.take(fibs)(8)))
    }
}
