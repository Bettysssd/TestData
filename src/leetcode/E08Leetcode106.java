package leetcode;

import com.sun.source.tree.Tree;
import queue.TreeNode;

import java.util.Arrays;

public class E08Leetcode106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0){
            return null;
        }
        int rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue);
        for (int i = 0; i < inorder.length; i++) {
            if (rootValue == inorder[i]) {
                int[] inLeft = Arrays.copyOfRange(inorder, 0, i);
                int[] inRight = Arrays.copyOfRange(inorder, i+1, inorder.length);

                int[] postLeft = Arrays.copyOfRange(postorder,0,i);
                int[] postRight = Arrays.copyOfRange(postorder,i, postorder.length - 1);

                root.left = buildTree(inLeft, postLeft);
                root.right = buildTree(inRight, postRight);
                break;
            }
        }
        return root;
    }
}
