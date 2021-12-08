package net.thejrdev.assignments.cycle_3.collections;

import java.util.Set;
import java.util.TreeSet;

public class OddsAndEvens {

    public static void find(int[] nums){

        Set<Integer> odds = new TreeSet<>(), evens = new TreeSet<>();

        for (int i: nums) {
            (i % 2 == 0 ? evens : odds).add(i);
        }

        System.out.println(odds);
        System.out.println(evens);

    }

}
