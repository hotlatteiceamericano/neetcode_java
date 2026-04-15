package org.example.app.Tree;


import org.junit.jupiter.api.Test;

class MaxPathSumSolution {
    int res = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        this.res = root.val;
        dfs(root);
        return this.res;
    }

    public int dfs(TreeNode root) {
        // each root can get the path sum from its left and right
        // each root can compare the max with the sum of its left, right and its own value
        // however it can only return either its left or right + itself
        if (root == null) return 0;

        int leftSum = maxPathSum(root.left);
        int rightSum = maxPathSum(root.right);

        this.res = Math.max(
                res,
                Math.max(
                        root.val,
                        Math.max(
                                Math.max(root.val + leftSum, root.val + rightSum),
                                leftSum + rightSum + root.val)));

        return Math.max(root.val, Math.max(leftSum, rightSum) + root.val);
    }
}

public class BinaryTreeMaxPathSumTest {
    @Test
    void test() {
        MaxPathSumSolution.maxPathSum(TreeNode.fromArray(new Integer[]{1,-2,-3,1,3,-2,null,-1}));
    }
}
