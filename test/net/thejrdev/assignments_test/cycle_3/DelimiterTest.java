package net.thejrdev.assignments_test.cycle_3;

import net.thejrdev.assignments.cycle_3.stacks_queues.DelimiterMatching;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DelimiterTest {

    @Test public final void test0(){
        assertEquals("good", DelimiterMatching.match("()"));
    }
    @Test public final void test1(){
        assertEquals("matching error", DelimiterMatching.match("([)]"));
    }
    @Test public final void test2(){
        assertEquals("missing right delimiter", DelimiterMatching.match("[()"));
    }
    @Test public final void test3(){
        assertEquals("missing left delimiter", DelimiterMatching.match("()]"));
    }
}
