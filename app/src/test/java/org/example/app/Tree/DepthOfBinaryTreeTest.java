package org.example.app.Tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> stack = new ArrayDeque();
        stack.offer(root);
        int level = 0;
        while (!stack.isEmpty()) {
            level += 1;
            // big mistake to forget saving the current size of the stack/queue in BFS
            int numInCurrLevel = stack.size();
            for (int i = 0; i < numInCurrLevel; i++) {
                TreeNode node = stack.poll();
                if (node.left != null) {
                    stack.offer(node.left);
                }
                if (node.right != null) {
                    stack.offer(node.right);
                }
            }
        }
        return level;
    }
}

public class DepthOfBinaryTreeTest {
    @Test
    public void test() {
        TreeNode root = TreeNode.fromArray(new Integer[] {1, 2, 3, 4, null, null, 5});
        assertEquals(3, Solution.maxDepth(root));
    }
}
