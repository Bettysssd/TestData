package datastructure;

import java.util.Arrays;

public class BTree {

    Node root;
    int t;
    final int MIN_KEY_NUMBER; // 最小关键字数目
    final int MAX_KEY_NUMBER; // 最大关键字数目


    static class Node {
        int[] keys; // 关键字
        Node[] children; // 孩子节点
        int keyNumber; // 有效关键字数目
        boolean leaf = true; //是否为叶子节点
        int t; // 最小度数，孩子数

        public Node(int t) { // t > 2
            this.t = t;
            this.children = new Node[2 * t];
            this.keys = new int[2 * t - 1];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        // 多路查找
        Node get(int key) {
            int i = 0;
            while (i < keyNumber) {

                // 找到了
                if (keys[i] == key) {
                    return this;
                }
                // 比所查询的大
                if (keys[i] > key) {
                    break;
                }
                // 执行到此时 keys[i] > key 或 i == keyNumber
                i++;
            }
            if (leaf) {
                return null;
            }
            // 非叶子情况
           return children[i].get(key);
        }
        // 向指定索引处插入 key
        // [1,2,3,5]
        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }
        // 向 children 指定索引处插入 child
        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        // 移除指定 index 处的 key
        int removeKey(int index) {
            int t = keys[index];
            System.arraycopy(keys, index + 1, keys, index,--keyNumber - index);
            return t;
        }

        // 移除最左边的 key
        int removeLeftmostKey() { return removeKey(0); }

        // 移除最右边的 key
        int removeRightmostKey() { return removeKey(keyNumber - 1); }

        // 移除指定 index 处的 child
        Node removeChild(int index) {
            Node child = children[index];
            System.arraycopy(children, index + 1, children, index, children.length - index - 1);
            return child;
        }

        // 移除最左边的 child
        Node removeLeftmostChild() {return removeChild(0); }

        // 移除最右边的 child
        Node removeRightmostChild() {return removeChild(keyNumber); }

        // index 孩子处左边的兄弟
        Node childLeftSibling(int index) { return index > 0  ? children[index + 1] : null; }

        // index 孩子处右边的兄弟
        Node childRightSibling(int index) { return index == keyNumber ? null : children[index + 1]; }

        // 复制当前节点的所有 key 和 child 到 target
        void moveToTarget(Node target) {
            int start = target.keyNumber;
            if (!leaf) {
                for (int i = 0; i <= keyNumber ; i++) {
                    target.children[start + 1] = children[i];
                }
            }

            for (int i = 0; i < keyNumber; i++) {
                target.keys[target.keyNumber++] = keys[i];
            }

        }
    }

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
        this.t = t;
        Node root = new Node(t);
    }

    // 1.是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }
    // 2.新增
    public void put(int key) {
        doPut(root,key, null, 0);
    }

    private void doPut(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i <= node.keyNumber) {
            if (node.keys[i] == key) {
                return; // 更新
            }
            if (node.keys[i] > key) {
                break; // 退出时，已经记录到当前需要被插入的位置了
            }
            i++;
        }
        // 判断是否为叶子节点
        if (node.leaf){
            node.insertKey(key, i);
        } else {
            doPut(node.children[i], key, node, i);
        }
        // 进行分裂行为【结合并且调用split方法】
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    private void split(Node left, Node parent, int index) {
        // 如果分裂的是root节点
        if (parent == null) {
            // 新建一个根节点
            Node newRoot = new Node(t);
            // 肯定不为叶子节点
            newRoot.leaf = false;
            // 先插入左边的孩子
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        // 1.创建right节点，并把t以后的节点从left拷贝到当中
        Node right = new Node(t);
        // 大家都在同一层上面
        right.leaf = left.leaf;
        System.arraycopy(left.keys, t, right.keys, 0, t-1);
        // 如果是非叶子节点
        if (!left.leaf) {
            // 对应的孩子节点也相应赋值过去, 孩子比keyNumbers多1
            System.arraycopy(left.children,t,right.children, 0, t);
        }
        // 更改一下有效的节点数目
        left.keyNumber = right.keyNumber = t - 1;
        // 2.t-1处的key插入到parent的index处。index指的是插入的索引位置
        int mid = left.keys[t-1];
        parent.insertKey(mid,index);
        // 3. right节点作为parent的孩子插入到index+1的位置
        parent.insertChild(right, index + 1);



    }

    // 3.删除

    public void remove (int key) {
        doRemove(null, root, 0, key);
    }

    private void doRemove(Node parent, Node node, int index, int key) {
        int i = 0;
        while (i <= node.keyNumber ) {

            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        // i 找到了， 代表待删除的key索引
        // i 没有找到，代表到待删除的第i个孩子继续查找
        if (node.leaf) {
            if (! found(node, key, i)) { // case1 当前是叶子节点，没找到
                return;
            } else { // case2 当前是叶子节点，找到了
                node.removeKey(i);
            }
        } else {
            if (! found(node, key, i)) { // case3 当前不是叶子节点，没有找到
                doRemove(node, node.children[i], i, key);
            } else {  // case4 当前是叶子节点，找到了
                // 1. 找到后继 key [孩子]
                Node s = node.children[i + 1];

                while (! s.leaf) {
                    s = s.children[0];
                }
                int sKey = s.keys[0];
                // 2. 替换待删除 key
                node.keys[i] = sKey;
                // 3. 删除后继 key
                doRemove(node, node.children[i + 1], i + 1, sKey);
            }

        }
        if (node.keyNumber < MIN_KEY_NUMBER) {
            // 调整平衡 case 5 case 6
            balance(parent, node, index);
        }
    }

    private void balance(Node parent, Node x, int index) {
        // case 6 根节点
        if (x == root) {
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }

        Node left = parent.childLeftSibling(index);
        Node right = parent.childRightSibling(index);

        // case 5-1 左边富裕，右旋
        if (left != null && left.keyNumber > MIN_KEY_NUMBER) {
            // a) 父节点中前驱key旋转下来
            x.insertKey(parent.keys[index - 1], 0);
            if (!left.leaf) {
                // b) left中最大的孩子换爹
                x.insertChild(left.removeRightmostChild(), 0);
            }
            // c) left中最大的key旋转上去
            parent.keys[index - 1] = left.removeRightmostKey();
            return;
        }

        // case 5-2 右边富裕，左旋
        if (right != null && right.keyNumber > MIN_KEY_NUMBER) {
            // a) 父节点中前驱key旋转下来
            x.insertKey(parent.keys[index], x.keyNumber);
            if (!right.leaf) {
                // b) right中最小的孩子换爹
                x.insertChild(right.removeLeftmostChild(), x.keyNumber + 1);
            }
            // c) right中最小的key旋转上去
            parent.keys[index] = right.removeLeftmostKey();
            return;
        }

        // case 5-3 两边都不富裕，向左合并
        if (left != null) {
            // 向左兄弟合并
            parent.removeChild(index);
            left.insertKey(parent.removeKey(index - 1), left.keyNumber);
            x.moveToTarget(left);
        } else {
            // 向自己合并
            parent.removeChild(index + 1);
            x.insertKey(parent.removeKey(index), x.keyNumber);
            right.moveToTarget(x);
        }

    }

    private static boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }


}
