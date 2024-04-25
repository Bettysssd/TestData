package leetcode;

import queue.TreeNode;

public class E06Leetcode226 {

    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    private void fn(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;
        fn(node.left);
        fn(node.right);
    }
}
