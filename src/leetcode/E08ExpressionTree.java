package leetcode;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

//根据后缀表达式建树
public class E08ExpressionTree {

    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }

    public TreeNode constructExpressionTree(String[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String t : tokens) {
            switch (t) {
                // + , - , * , /
                case "+":
                case "-":
                case "*":
                case "/":
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode root = new TreeNode(t);
                    root.left = left;
                    root.right = right;
                    stack.push(root);
                    break;
                //数字
                default:
                    stack.push(new TreeNode(t));
                    break;
            }
        }
        return stack.peek();
    }

    public static void post (TreeNode node, ArrayList<String> result) {
        if (node == null) {
            return;
        }
        post(node.left,result);
        post(node.right,result);
        result.add(node.val);
    }

    public static void main(String[] args) {
        String[] tokens = {"2", "1", "-", "3", "*"};
        TreeNode root = new E08ExpressionTree().constructExpressionTree(tokens);
        ArrayList<String> result = new ArrayList<>();
        post(root, result);
        System.out.println(result);
    }
}
