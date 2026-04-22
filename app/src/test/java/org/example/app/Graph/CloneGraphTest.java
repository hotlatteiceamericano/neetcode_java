package org.example.app.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Solution {
    public Node cloneGraph(Node node) {
        // simply use a hashset to store visited nodes?
        // if I BFS, except for root, I must have cloned the neighbor before iterate on it
        // which makes the order becomes
        // get the cloned from map while polling from BFS queue
        // then create new node when iterating the neighbors
        // then update clone's neighbors
        // a map will be used for both storing visited and a mapping between original and clone

        // solution use the map as visited as well
        // which means all the neighbors will essentially be visited
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        Node clonedRoot = new Node(node.val);
        map.put(node, clonedRoot);
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            List<Node> cloneNeighbors = new ArrayList<>();
            for (Node n : curr.neighbors) {
                // see how solution handle if neighbor has been seen before
                if (!map.containsKey(n)) {
                    map.put(n, new Node(n.val));

                    // wasn't able to briefly describe how this works
                    queue.offer(n);
                }

                cloneNeighbors.add(map.get(n));

                // see if solution has both map and visited
                // if (!visited.contains(n)) {
                //     queue.offer(n);
                // }
            }
            Node cloned = map.get(curr);
            cloned.neighbors = cloneNeighbors;
            visited.add(curr);
        }

        return map.get(node);
    }
}
