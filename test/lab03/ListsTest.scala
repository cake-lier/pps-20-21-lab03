package lab03

import org.junit.jupiter.api.{Assertions, Test}
import u03.Lists.List.{Cons, Nil}
import lab03.Lists.drop

class ListsTest {
    @Test
    def dropTest(): Unit = {
        val list = Cons(10, Cons(20, Cons(30, Nil())))
        Assertions.assertEquals(Cons(20, Cons(30, Nil())), drop(list, 1))
        Assertions.assertEquals(Cons(30, Nil()), drop(list, 2))
        Assertions.assertEquals(Nil(), drop(list, 5))
        Assertions.assertEquals(list, drop(list, 0))
        Assertions.assertEquals(list, drop(list, -2))
    }
}
