package leetcode;

import queue.TreeNode;

import java.util.LinkedList;

public class E03Leetcode145_1 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6)));

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root; // 当前节点
        TreeNode pop = null; // 最近一次弹栈道元素
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr); // 压入栈记录回来的路
                // 待处理左子树
                System.out.println("回 " + curr.val);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null) {
                    System.out.println("中 " + peek.val);
                    pop = stack.pop();
                    System.out.println("去 " + pop.val);
                    // 右子树处理完成
                } else if(peek.right == pop) {
                    pop = stack.pop();
                    System.out.println("去 " + pop.val);
                }
                else {
                    // 待处理右子树
                    System.out.println("中 " + peek.val);
                    curr = peek.right;
                }

            }
        }
    }
}
