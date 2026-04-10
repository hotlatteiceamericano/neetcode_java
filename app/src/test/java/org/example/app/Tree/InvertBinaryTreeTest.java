package org.example.app.Tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InvertBinaryTree {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.right = left;

        root.left = right;

        return root;
    }
}

public class InvertBinaryTreeTest {
    @Test
    public void testBalancedTree() {
        TreeNode root = TreeNode.fromArray(new Integer[] {4, 2, 7, 1, 3, 6, 9});
        TreeNode expected = TreeNode.fromArray(new Integer[] {4, 7, 2, 9, 6, 3, 2});
        assertEquals(InvertBinaryTree.invertTree(root), expected);
    }
}
