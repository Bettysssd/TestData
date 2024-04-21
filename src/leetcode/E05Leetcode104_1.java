package leetcode;

import queue.TreeNode;

public class E05Leetcode104_1 {

    public int maxDepth(TreeNode node){
        if (node == null) {
            return 0;
        }

//        if (node.left == null && node.right == null) {
//            return 1;
//        }

        int d1 = maxDepth(node.left);
        int d2 = maxDepth(node.right);
        return Integer.max(d1, d2) + 1;

    }

}
