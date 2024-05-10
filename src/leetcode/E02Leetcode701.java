package leetcode;

import queue.TreeNode;

public class E02Leetcode701 {

    public TreeNode insertIntoBST(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insertIntoBST(node.left, val);
        } else if (val > node.val) {
            node.right = insertIntoBST(node.right, val);
        }
        return node;
    }
}
