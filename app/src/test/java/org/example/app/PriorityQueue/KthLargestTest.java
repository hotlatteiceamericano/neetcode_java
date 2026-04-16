package org.example.app.PriorityQueue;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

class KthLargest {

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>();
        for (int num : nums) {
            this.pq.offer(num);
            if (this.pq.size() > k) {
                this.pq.poll();
            }
        }
    }

    public int add(int val) {
        this.pq.offer(val);
        this.pq.poll();
        return this.pq.isEmpty() ? null : this.pq.peek();
    }
}

public class KthLargestTest {
    @Test
    void test() {}
}
