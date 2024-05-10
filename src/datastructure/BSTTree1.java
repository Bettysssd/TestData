package datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BSTTree1 {

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    BSTNode root;

/*
    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            return null; // 没找到
        }
        if (key < node.key) {
            return doGet(node.left, key); // 向左找
        } else if (key > node.key) {
            return doGet(node.right, key); // 向右找
        } else {
            return node.value; // 找到了
        }
    }

    public Object get(int key) {
        return doGet(root, key);
    }

 */

    public Object get(int key) {
        BSTNode node = root;
            while (node != null) {
                if (key < node.key) {
                    node = node.left;
                } else if (key > node.key) {
                    node = node.right;
                } else {
                    return node.value;
                }
            }
        return null;
    }


/*
    public Object min() {
        return doMin(root);
    }

    private Object doMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) { // 最小节点时
            return node.value;
        }
        return doMin(node.left);
    }

    public Object max() {
        return doMax(root);
    }

    private Object doMax(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) { // 最大节点时
            return node.value;
        }
        return doMax(node.right);
    }

 */

    public Object min (){
        return min(root);
    }

    private Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    public Object max() {
       return max(root);
    }

    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }


    /**
     * 存储关键字和对应值
     * @param key
     * @param value
     */
    public void put(int key, Object value) {
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                // 相等，则更新
                node.value = value;
                return;
            }
        }
        // 没有找到的，则直接新建一个

        if (parent == null) {
            root = new BSTNode(key, value);
            return;
        }

        if (parent.key > key) {
            parent.left =  new BSTNode(key, value);
        } else {
            parent.right =  new BSTNode(key, value);
        }
    }

    /**
     * 查找关键字的后驱值
     * @param key
     * @return
     */
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key < p.key) {
                ancestorFromRight = p;
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) {
            return null;
        }
        // 情况1： 节点有右子树， 此时前任就是右子树的最大值
        if (p.right != null) {
            return min(p.right);
        }
        // 情况2： 节点没有右子树，若离它最近的，自右而来的祖先就是后任
        return ancestorFromRight != null ? ancestorFromRight.value : null;
    }

    /**
     * 查找关键字的前驱值
     * @param key
     * @return
     */
    public Object predecessor(int key) {

        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                ancestorFromLeft = p;
                p = p.right;
            } else {
               break;
            }
        }
        if (p == null) {
            return null;
        }
        // 情况1： 节点有左子树， 此时前任就是左子树的最大值
        if (p.left != null) {
            return max(p.left);
        }
        // 情况2： 节点没有左子树，若离它最近的，自左而来的祖先就是前任
        return ancestorFromLeft != null ? ancestorFromLeft.value : null;
    }

    /**
     * 根据关键字删除吗，但是不会关联到这个节点的下一个
     * @param key
     * @return
     */
//    public Object delete(int key) {
//        BSTNode p = root;
//        BSTNode parent = null;
//        while (p != null) {
//            if (key < p.key) {
//                parent = p;
//                p = p.left;
//            } else if (key > p.key) {
//                parent = p;
//                p = p.right;
//            } else {
//                break;
//            }
//        }
//        if (p == null) {
//            return null;
//        }
//        if (p.left == null ) {
//            // 1.被删除节点没有左孩子
//            shift(parent, p, p.right);
//        } else if (p.right == null) {
//            // 2.被删除节点没有右孩子
//            shift(parent, p, p.left);
//        } else {
//            // 1.被删除节点找后继
//           // 我们要从当前被删除的节点寻找他的后继 (自左向右)
//            BSTNode s = p.right;
//            BSTNode sParent = p; // 记录每次更新节点的parent
//            while (s.left != null) {
//                sParent = s;
//                s = s.left;
//            }
//            // 说明被删除节点和他的后继不相临
//            // 2.处理后继的后事
//            if (p != sParent) {
//                shift (sParent, s, s.right); //不可能有左孩子
//                s.right = p.right;
//
//            }
//            // 3.后继取代被删除的节点
//                shift(parent, p, s);
//                s.left = p.left;
//        }
//        return p.value;
//    }

    public Object delete(int key) {
        ArrayList<Object> result = new ArrayList<Object>();
        root = doDelete(root, key, result);
        return result.isEmpty() ? null : result.get(0);
    }
    /** 托孤方法
     *
     * @param parent 被删除节点的父亲
     * @param deleted 被删除的节点
     * @param child  被顶上去的节点
     */

    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }


    //递归的方式进行实现
    private BSTNode doDelete(BSTNode node, int key,ArrayList<Object> result) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = doDelete(node.left, key, result );
            return node;
        }

        if (key > node.key) {
            node.right = doDelete(node.right, key, result);
            return node;
        }
        result.add(node.value);
        // 1.只有左孩子的时候
        if (node.right == null) {
            return node.left;
        }
        // 2.只有右孩子的时候
        if (node.left == null) {
            return node.right;
        }
        // 3. 有左右两个孩子的时候
        BSTNode s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = doDelete(node.right, s.key, new ArrayList<>());
        s.left = node.left;
        return s;
    }

// 找 < key 的所有value
public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();
        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode p = root;
        while (p != null || !stack.isEmpty()) {
           if (p != null) {
               stack.push(p);
               p = p.left;
           } else {
               BSTNode pop = stack.pop();
               // 处理值
               if (pop.key < key) {
                   result.add(pop.value);
               } else {
                   break;
               }
               p = pop.right;
           }
        }
        return result;
}
// 找 > key 的所有value
    public List<Object> greater(int key) {
  /*
        ArrayList<Object> result = new ArrayList<>();
        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key > key) {
                    result.add(pop.value);
                }
                p = pop.right;
            }
        }
        return result;
    */
        ArrayList<Object> result = new ArrayList<>();
        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.left;
            }
        }
        return result;
    }

//找 >= key1 || <= key2 的所有value
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }


}
