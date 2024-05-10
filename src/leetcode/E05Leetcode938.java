package leetcode;

import queue.TreeNode;

import java.util.LinkedList;

public class E05Leetcode938 {
    // 中序遍历非递归实现
    /*
    public int rangeSumBST(TreeNode node, int low, int high) {
    TreeNode p = node;
    LinkedList<TreeNode> stack = new LinkedList<>();
    int sum = 0;
    while (p != null || !stack.isEmpty()) {
        if (p != null) {
            stack.push(p);
            p = p.left;
        } else {
            TreeNode pop = stack.pop();
            //处理值
            if (pop.val > high) {
                break;
            }
            if (pop.val >= low) {
                sum += pop.val;
            }
            p = pop.right;
        }
    }
    return sum;
    }
     */
    // 上下限递归
    public int rangeSumBST(TreeNode node, int low, int high) {

        if(node == null) return 0;
        if(node.val < low) {
            return rangeSumBST(node.right, low, high);
        } else if(node.val > high) {
            return rangeSumBST(node.left, low, high);
        }


        // 合理范围
        return node.val + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high) ;
    }
}
