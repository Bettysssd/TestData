package leetcode;

import queue.TreeNode;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

public class E04Leetcode98 {
//    public boolean isValidBST(TreeNode node) {
//        TreeNode p = node;
//        LinkedList<TreeNode> stack = new LinkedList<>();
//        long prev = Long.MIN_VALUE;
//        while (p != null || !stack.isEmpty()) {
//            if (p != null) {
//
//                stack.push(p);
//                p = p.left;
//            } else {
//               TreeNode pop = stack.pop();
//               //处理值
//                if (prev >= pop.val) {
//                    return false;
//                }
//                prev = pop.val;
//                p = pop.right;
//            }
//        }
//        return true;
//    }

//    long prev = Long.MIN_VALUE;
//public boolean isValidBST(TreeNode node) {
//
//    if(node == null) return true;
//    boolean a = isValidBST(node.left);
//    if (!a) return false;
//    // 值
//    if(prev >= node.val) {
//        return false;
//    }
//    prev = node.val;
//    return isValidBST(node.right);
//}

    /*
    public boolean isValidBST(TreeNode node) {
        return doVaild(node, new AtomicLong(Long.MIN_VALUE));
    }


    private boolean doVaild(TreeNode node, AtomicLong prev) {
        if(node == null) return true;
        boolean a = doVaild(node.left, prev);
        if (!a) return false;
        // 值
        if(prev.get() >= node.val) {
            return false;
        }
        prev.set(node.val);
        return doVaild(node.right, prev);
    }
     */
    public boolean isValidBST(TreeNode node) {
        return doVaild(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean doVaild(TreeNode node,long min,long max) {
        if (node == null) return true;
        if( node.val <= min || node.val >= max ) return false;

        return doVaild(node.left, min,node.val) && doVaild(node.right,node.val,max);
    }
}
