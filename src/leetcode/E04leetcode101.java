package leetcode;

import queue.TreeNode;

public class E04leetcode101 {
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        // 如果可以到达这里的话，则说明左子树和右子树存在其中一个
        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return check(left.left,right.right) && check(left.right, right.left);
    }
}
