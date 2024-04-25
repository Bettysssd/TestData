package leetcode;

import queue.TreeNode;

public class E06Leetcode111_1 {


    public int minDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int d1 = minDepth(node.left);
        int d2 = minDepth(node.right);
        if (d1 == 0) {
            return d2 + 1;
        }
        if (d2 == 0) {
            return d1 + 1;
        }
        return Integer.min(d1, d2) + 1;
    }
}

