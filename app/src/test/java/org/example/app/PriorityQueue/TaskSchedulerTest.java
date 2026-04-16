package org.example.app.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class TaskSchedulerSolution {
    public static int leastInterval(char[] tasks, int n) {
        // since its a priority queue question
        // think that when seeing a task
        // can append a timing when it is available to process next
        // or record which cycle is this task last processed
        // because when calculating a task's next available time,
        // its hard to know if this task is currently in cooldown
        // basically the logic becomes

        // I want to get next available task, using the last process time
        // process it if finishes cooldown
        // wait for it when its ready

        // ok, looks like I don't need to parse tasks for the first pass
        // I should be able to put all the task with default cycle of 0 to the pq first

        // ok, I still need both pq and map
        // using map to know when is a certain task processed last time
        // using a pq to know which task is closest the available

        // I still need the initial parse, otherwise I don't know a task's cooldown, if giving
        // default to all task

        // also, I did not think of a case, where A and B have equal cooldowns, but there are more A
        // than B
        // in such a case, A should be prioritized, because the following As needs more cooldown
        // I wasn focusing on how to get the latest available time,

        // say now I am solving this problem around handling the most frequent task first
        // I would store paris in a priority queue to get the most frequent task
        // once process it, how to deal with cooldown?
        // so when processing most frequent task, how to know if it is in the cooldown?
        // if we are putting the most frequent task in a cooldown queue,
        // shouldn't we look at the cooldown queue's task is ready, before processing the next task?
        // => yes

        // ok, store tasks using, and sort by their frequencies
        // when process one task, decrement its frequency, and put to a cooldown queue
        // everytime finishes process a task, look at the cooldown queue to see if there are any
        // tasks is ready
        // if there are ready task finished cooldown, put it back to the priority queue
        // so that the priority queue may process the most frequent task that has no cooldown
        // the cooldown queue can be FIFO, because the first task having cooldown, will also be the
        // first one to complete the cooldown

        record Pair<T, U>(T left, U right) {}

        Map<Character, Integer> count = new HashMap<>();
        for (char task : tasks) {
            count.put(task, count.getOrDefault(task, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.addAll(count.values());

        int curr = 0;
        Queue<Pair<Integer, Integer>> cooldown = new LinkedList<>();
        while (!pq.isEmpty() || !cooldown.isEmpty()) {
            if (!pq.isEmpty()) {
                Integer frequency = pq.poll();

                // needs to go to cd queue when the same task has remainings
                if (frequency > 1) {
                    cooldown.offer(new Pair<>(frequency - 1, curr));
                }
            }

            if (!cooldown.isEmpty()) {
                Pair<Integer, Integer> cdTask = cooldown.peek();
                // needs to include '=' is because by next cycle it will finish cooldown
                if (curr >= cdTask.right + n) {
                    pq.offer(cooldown.poll().left());
                }
            }

            curr += 1;
        }

        return curr;
    }
}

public class TaskSchedulerTest {
    @Test
    void test() {
        assertEquals(
                9, TaskSchedulerSolution.leastInterval(new char[] {'A', 'A', 'A', 'B', 'B'}, 3));
    }
}
