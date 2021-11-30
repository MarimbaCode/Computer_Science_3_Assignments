package net.thejrdev.assignments_test.cycle_3;

import net.thejrdev.assignments.cycle_3.stacks_queues.JosephusCandy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class JosephusCandyTest {

    @Test public final void test0(){

        Integer[] expected = new Integer[]{2, 4, 6, 8, 10, 12, 1, 5, 9, 13, 7, 3, 11};

        assertArrayEquals(expected, JosephusCandy.calcluate(13, 2));

    }
    @Test public final void test1(){

        Integer[] expected = new Integer[]{5, 10, 15, 20, 25, 30, 4, 11, 17, 23, 29, 6, 13, 21, 28, 7, 16, 26, 3, 18, 31, 12, 27, 14, 2, 24, 22, 1, 9, 19, 8};

        assertArrayEquals(expected, JosephusCandy.calcluate(13, 2));

    }
}
