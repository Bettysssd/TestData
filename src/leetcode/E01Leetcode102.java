package leetcode;

import queue.LinkedListQueue;
import queue.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E01Leetcode102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        int c1 = 1;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();
                level.add(n.val);
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            c1 = c2;
            result.add(level);
        }

        return result;

    }



}
