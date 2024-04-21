package leetcode;

import queue.TreeNode;

import java.util.LinkedList;

public class E02Leetcode94 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6)));
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root; // 当前节点
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.println("去 " + curr.val); // 屏蔽下面的输出，这里输出的是前序遍历
                stack.push(curr); // 压入栈记录回来的路
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                curr = pop.right;
            }
        }
    }
}
