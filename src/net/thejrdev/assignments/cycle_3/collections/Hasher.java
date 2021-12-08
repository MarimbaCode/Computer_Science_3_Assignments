package net.thejrdev.assignments.cycle_3.collections;

import java.util.Arrays;

public class Hasher {

    public static int hash(String toHash){
        return Arrays.stream(toHash.split("")).mapToInt(c -> (int)(c.charAt(0)%256)).sum();
    }


}
