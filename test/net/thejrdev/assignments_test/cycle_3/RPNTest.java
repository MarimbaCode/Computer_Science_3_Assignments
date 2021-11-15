package net.thejrdev.assignments_test.cycle_3;

import net.thejrdev.assignments.cycle_3.stacks_queues.RPN;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPNTest {

    @Test public final void test0(){
        String input = "2 7 + 1 2 + +";
        assertEquals(12.0, RPN.solve(input));
    }
    @Test public final void test1(){
        String input = "1 2 3 4 + + +";
        assertEquals(10.0, RPN.solve(input));
    }
    @Test public final void test2(){
        String input = "9 3 * 8 / 4 +";
        assertEquals(7.375, RPN.solve(input));
    }
    @Test public final void test3(){
        String input = "3 3 + 7 * 9 2 / +";
        assertEquals(46.5, RPN.solve(input));
    }
    @Test public final void test4(){
        String input = "9 3 / 2 * 7 9 * + 4 -";
        assertEquals(65.0, RPN.solve(input));
    }
    @Test public final void test5(){
        String input = "5 5 + 2 * 4 / 9 +";
        assertEquals(14.0, RPN.solve(input));
    }
}
