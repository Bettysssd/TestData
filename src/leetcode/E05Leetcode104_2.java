package leetcode;

import queue.TreeNode;

import java.util.LinkedList;

public class E05Leetcode104_2 {

    public int maxDepth(TreeNode node){
        TreeNode curr = node;
        TreeNode pop = null;
        int max = 0;
        LinkedList<TreeNode> stack = new LinkedList();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null){
                stack.push(curr);
                int size = stack.size();
                if (size > max) {
                    max = size;
                }
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }
        return max;

    }

}
