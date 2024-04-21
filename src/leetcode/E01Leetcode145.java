package leetcode;

import com.sun.source.tree.Tree;
import queue.TreeNode;

import java.util.LinkedList;

public class E01Leetcode145 {

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
//                System.out.println("去 " + curr.val); // 屏蔽下面的输出，这里输出的是前序遍历
                stack.push(curr); // 压入栈记录回来的路
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                    System.out.println("回 " + pop.val); // 屏蔽上面的输出，这里输出的是中序遍历
                } else {
                    curr = peek.right;
                }

            }
        }
    }
}
