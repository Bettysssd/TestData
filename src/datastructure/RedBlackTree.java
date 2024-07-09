package datastructure;

import static datastructure.RedBlackTree.Color.BLACK;
import static datastructure.RedBlackTree.Color.RED;

public class RedBlackTree {

    enum Color {
        RED, BLACK;
    }

    private Node root;

    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent; //父节点
        Color color = RED; //颜色

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        // 是否为左孩子
        boolean isLeafChild() {
            return parent != null && parent.left == this;
        }
        // 叔叔
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if(parent.isLeafChild()) { // 爸爸是不是爷爷的左孩子
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }
        // 兄弟
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeafChild()) { //自己是不是爸爸的左孩子
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    // 判断红
    boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    // 判断黑
    boolean isBlack(Node node) {
    //return !isRed(node);
        return node == null || node.color == BLACK;
    }

    // 右旋， 1.parent的处理 2.旋转后新根的父子关系
    private void rightRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.left;
        Node green = yellow.right;
        if (green != null) {
            green.parent = pink; //绿色属于黑色的节点，可能为null，需要判空处理
        }
        yellow.right = pink;
        yellow.parent = parent;
        pink.left = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        }
        else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }
    // 左旋
    private void leftRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;
        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = parent;
        pink.right = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.right == pink) {
            parent.right = yellow;
        } else {
            parent.left = yellow;
        }
    }

    /**
     * 新增或更新
     *   正常增、遇到红黑树不平衡进行调整
     * @param key-键
     * @param value-值
     */
    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value; // 更新
                return;
            }
        }
        Node inserted = new Node(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);
    }

    void fixRedRed(Node x) {
        // case1: 插入节点是根节点，变黑即可
        if (x == root) {
            x.color = BLACK;
        }
        // case2: 插入节点父亲是黑色，无需调整
        if (isBlack(x.parent)) {
            return;
        }
        // case3 当红红相邻，叔叔为红的时候
        // 要将父亲， 叔叔变黑，祖父变红，然后对祖父进行递归
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandParent = parent.parent;
        if (isRed(uncle)) {
            parent.color = BLACK;
            uncle.color = BLACK;
            grandParent.color = RED;
            fixRedRed(grandParent);
            return;
        }
        // case4 当红红相邻，叔叔为黑的时候
        if (parent.isLeafChild() && x.isLeafChild()) {  //LL
            parent.color = BLACK;
            grandParent.color = RED;
            rightRotate(grandParent);
        } else if (parent.isLeafChild() && !x.isLeafChild())  { // LR
            leftRotate(parent);
            parent.color = BLACK;
            grandParent.color = RED;
            rightRotate(grandParent);
        } else if (!parent.isLeafChild() && x.isLeafChild()) {  //RL
            rightRotate(parent);
            parent.color = BLACK;
            grandParent.color = RED;
            leftRotate(grandParent);
        } else {  //RR
            parent.color = BLACK;
            grandParent.color = RED;
            leftRotate(grandParent);

        }
    }

    /**
     * 删除
     * 正常删，会用到李代桃僵，遇到黑黑不平衡进行调整
     * @param key-键
     */
    public void remover(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    // 处理双黑 （case3， case4， case5）
    private void fixDoubleBlack(Node x) {
        if (x == root) {
            return;
        }
        Node parent = x.parent;
        Node sibling = x.sibling();
        // case3 兄弟节点是红色
        if (isRed(sibling)) {
            if (x.isLeafChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = RED;
            sibling.color = BLACK;
            fixDoubleBlack(x);
            return;
        }
        if (sibling != null) {
            // case 4 兄弟是黑色， 两个侄子也是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RED;
                if (isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
                // case5 兄弟是黑色，孩子有红色
            } else {
               // LL
                if (sibling.isLeafChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                // LR
                else if (sibling.isLeafChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                // RL
                else if(!sibling.isLeafChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                // RR
                else {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }
                parent.color = BLACK;
            }
        } else {
            fixDoubleBlack(parent);
        }
    }

    private void doRemove(Node deleted) {
        Node replaced = fineReplaced(deleted);
        Node parent = deleted.parent;
        if (replaced == null) { // 没有孩子
            // case1
            if (deleted == root) {
                root = null;
            } else {
                //先调整，再删除
                if (isBlack(deleted)) {
                    // 复杂情况
                    fixDoubleBlack(deleted);
                } else {
                    // 红色叶子，无需任何处理
                }
                // 判断待删除节点为其父亲的什么节点
                if (parent.isLeafChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                // 红黑树底层是双向引用 help gc
                deleted.parent = null;
            }

            return;
        }
        if (replaced.left == null || replaced.right == null) { // 有一个孩子
            //case1
            //先删除，再调整
            if (deleted == root) {
                root.key = replaced.key;;
                root.value = replaced.value;
                root.left = root.right = null;
            } else {
                if (deleted.isLeafChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;
                if (isBlack(deleted) && isBlack(replaced)) {
                    // 复杂处理
                    fixDoubleBlack(replaced);
                } else {
                    // case2 孩子节点红变黑
                    replaced.color = BLACK;
                }
            }
            return;
        }

        // 有两个孩子 => 有一个孩子 或者没有孩子 case0
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;

    }

    // 查找删除节点
    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    // 查找剩余节点
    private Node fineReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        } else if(deleted.left == null) {
            return deleted.right;
        } else if (deleted.right == null) {
            return deleted.left;
        } else {
            Node p = deleted.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

    }
}
