package net.thejrdev.assignments_test.cycle_3;

import net.thejrdev.assignments.cycle_3.collections.LinkedL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLinkedL {



    @Test
    public final void test0(){

        LinkedL<Integer> list = new LinkedL<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals("[1, 2, 3]", list.toString());
    }
    @Test
    public final void test1(){

        LinkedL<Integer> list = new LinkedL<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.reverse();

        assertEquals("[3, 2, 1]", list.toString());
    }

    @Test
    public final void test2(){

        LinkedL<Integer> list = new LinkedL<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);

        Integer num = list.get(2);

        assertEquals(3, num);
    }

    @Test
    public final void test3(){

        LinkedL<String> list = new LinkedL<String>();

        list.add("A");
        list.add("B");
        list.add("C");

        String character = list.remove(1);

        assertEquals("B", character);
    }
    @Test
    public final void test4(){

        LinkedL<Integer> list = new LinkedL<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9);


        Integer num = list.get(2);

        assertEquals(3, num);
    }

    @Test
    public final void test5(){

        LinkedL<Integer> list = new LinkedL<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9);

        StringBuilder sb = new StringBuilder();

        for(Integer i: list){
            sb.append(i);
        }

        assertEquals("123456789", sb.toString());


    }
}
