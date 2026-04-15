package org.example.app.Tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class Solution1 {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // preorder provides the root
        // inorder provides the left
        // when preorder[0] meet inorder[0], this is the left mode node, so that the next in inorder
        if (preorder.length == 0) {
            return null;
        }

        // meet the leftmost,
        TreeNode root = new TreeNode(preorder[0]);

        int inorderRootIndex = 0;
        for (int i=0; i<inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                inorderRootIndex = i;
                break;
            }
        }

        int inorderLeftSize = inorderRootIndex;
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, inorderLeftSize);

        int inorderRightSize = inorder.length - inorderLeftSize - 1;
        int[] rightInorder = Arrays.copyOfRange(inorder, inorderRootIndex+1, inorder.length);

        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + inorderLeftSize);
        int[] rightPreorder = Arrays.copyOfRange(preorder, preorder.length-inorderRightSize, inorder.length);

        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightInorder);


        // what I wasn't sure about is, once complete building the left, how do I jump the preorder and inorder array to their rlight node?
        // also, how to deal with the root node in inorder list?

        return root;
    }

}

public class ConstructBinaryTreeFromPreorderAndInorderTest {
    @Test
    void test() {
        Solution1.buildTree(new int[]{3,1,2,5,4,6}, new int[]{1,2,3,4,5,6});
    }
}