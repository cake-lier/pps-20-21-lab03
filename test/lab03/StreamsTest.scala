package lab03

import org.junit.jupiter.api.{Assertions, Test}
import u03.Lists.List.{Cons, Nil}
import u03.Streams.Stream
import lab03.Streams.drop

class StreamsTest {
    @Test
    def testDrop(): Unit = {
        val s = Stream.take(Stream.iterate(0)(_ + 1))(10)
        Assertions.assertEquals(Cons(6, Cons(7, Cons(8, Cons(9, Nil())))), Stream.toList(drop(s)(6)))
        Assertions.assertEquals(Nil(), Stream.toList(drop(s)(11)))
        Assertions.assertEquals(Stream.toList(s), Stream.toList(drop(s)(0)))
    }
}
