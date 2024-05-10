package leetcode;

import queue.TreeNode;

public class E07Leetcode235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
     TreeNode a = root;
     while (a.val > p.val && a.val > q.val || a.val < p.val && a.val < q.val ) {
         if (a.val > p.val) a = a.left;
         else a = a.right;
     }
     return a;
    }
}
