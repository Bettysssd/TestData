package leetcode;

import queue.TreeNode;

import java.util.Arrays;

public class E09Leetcode105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length == 0) {
            return null;
        }
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        for (int i = 0; i < inorder.length; i++) {
            if (rootValue == inorder[i]) {
                int[] inLeft = Arrays.copyOfRange(inorder, 0, i);
                int[] inRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);

                int[] preLeft = Arrays.copyOfRange(preorder,1,i+1);
                int[] preRight = Arrays.copyOfRange(preorder, i + 1, preorder.length);

                root.left = buildTree(preLeft,inLeft);
                root.right = buildTree(preRight,inRight);
                break;
            }
        }
        return root;
    }
}
