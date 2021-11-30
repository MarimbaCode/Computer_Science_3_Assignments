package net.thejrdev.assignments.cycle_3.stacks_queues;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class JosephusCandy {

    public static Integer[] calcluate(int students, int inc) {

        Queue<Integer> studentQueue = new LinkedList<>();

        IntStream.range(1,students + 1).forEachOrdered(studentQueue::offer);

        Queue<Integer> res = new LinkedList<>();
        while(!studentQueue.isEmpty()){
            for (int i = 0; i < inc - 1; i++) {
                studentQueue.offer(studentQueue.poll());
            }
            res.offer(studentQueue.poll());
        }
        return res.toArray(Integer[]::new);
    }


}
