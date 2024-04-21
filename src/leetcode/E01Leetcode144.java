package leetcode;

import queue.TreeNode;

import java.util.LinkedList;

public class E01Leetcode144 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6)));
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root; // 当前节点
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr); // 压入栈记录回来的路
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.println("回 " + pop.val); // 屏蔽上面的输出，这里输出的是中序遍历
                curr = pop.right;
            }
        }
    }
}
