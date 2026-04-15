package org.example.app.Tree;

import java.util.LinkedList;
import java.util.Queue;

// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TreeNode other)) return false;
        return this.val == other.val
            && java.util.Objects.equals(this.left, other.left)
            && java.util.Objects.equals(this.right, other.right);
    }

    @Override
    public String toString() {
        if (left == null && right == null) return String.valueOf(val);
        return val + "(" + left + ", " + right + ")";
    }

    public static TreeNode fromArray(Integer[] vals) {
        if (vals == null || vals.length == 0 || vals[0] == null) return null;

        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (i < vals.length) {
            TreeNode node = queue.poll();
            if (i < vals.length && vals[i] != null) {
                node.left = new TreeNode(vals[i]);
                queue.add(node.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                node.right = new TreeNode(vals[i]);
                queue.add(node.right);
            }
            i++;
        }

        return root;
    }
}
