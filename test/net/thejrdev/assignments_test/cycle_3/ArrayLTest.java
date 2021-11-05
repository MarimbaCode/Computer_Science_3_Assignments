package net.thejrdev.assignments_test.cycle_3;

import net.thejrdev.assignments.cycle_3.collections.ArrayL;
import net.thejrdev.assignments.cycle_3.collections.LinkedL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayLTest {



    @Test
    public final void test0(){

        ArrayL<Integer> list = new ArrayL<>();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals("[1, 2, 3]", list.toString());
    }
    @Test
    public final void test1(){

        ArrayL<Integer> list = new ArrayL<>();

        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(3));
    }

    @Test
    public final void test2(){

        ArrayL<Integer> list = new ArrayL<>();

        list.add(1);
        list.add(2);
        list.add(3);

        assertFalse(list.contains(4));
    }

    @Test
    public final void test3(){

        ArrayL<Integer> list = new ArrayL<>();

    }
    @Test
    public final void test4(){

        ArrayL<Integer> list = new ArrayL<>();

        list.add(0, 1);
        list.add(0, 2);
        list.add(0, 3);


        assertEquals(3, list.get(0));
    }


}
