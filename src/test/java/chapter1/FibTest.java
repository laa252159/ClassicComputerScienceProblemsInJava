package chapter1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibTest {
    @Test
    void myFibTest(){

        System.out.println(Fib4.fib4(60));
        System.out.println(Fib2.fib2(60));
    }
}