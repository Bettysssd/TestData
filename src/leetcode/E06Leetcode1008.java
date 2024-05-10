package leetcode;

import queue.TreeNode;

public class E06Leetcode1008 {
//    public TreeNode bstFromPreorder(int[] preorder) {
//        TreeNode root = new TreeNode(preorder[0]);
//        for (int i = 1; i < preorder.length; i++) {
//            int val = preorder[i];
//            insert(root, val);
//        }
//        return root;
//   }
//
//    private TreeNode insert(TreeNode node, int val) {
//        if (node == null) {
//            return new TreeNode(val);
//        }
//
//        if (val < node.val) {
//            node.left = insert(node.left, val);
//        } else if (val > node.val) {
//            node.right = insert(node.right, val);
//        }
//        return node;
//    }

    /*
    public TreeNode bstFromPreorder(int[] preorder){
        return insert(preorder, Integer.MAX_VALUE);
    }

    int i = 0;
    private TreeNode insert(int[] preorder, int max) {
        if (i == preorder.length) return null;
        int val = preorder[i];
        if (val > max) return null;
        TreeNode node = new TreeNode(val);
        i++;
        node.left = insert(preorder, val);
        node.right = insert(preorder, max);
        return node;
    }
*/
    // 分治思想
    public TreeNode bstFromPreorder(int[] preorder) {
        return partition(preorder, 0, preorder.length - 1);
    }

    private TreeNode partition(int[] preorder, int start, int end) {
       if (start > end) {
           return null;
       }
        TreeNode root = new TreeNode(preorder[start]);
        int index = start + 1;
        while (index <= end) {
            if (preorder[index] > preorder[start]) {
                break;
            }
            index++;
        }
        //找到右子树的起点了
        root.left = partition (preorder, start + 1, index - 1);
        root.right = partition (preorder, index, end);

        return root;
    }
}
