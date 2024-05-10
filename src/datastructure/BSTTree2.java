package datastructure;

public class BSTTree2<K extends  Comparable<K>, V> {

    static class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    BSTNode<K, V> root;

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

    public V get(K key) {
        BSTNode<K, V> p = root;
            while (p != null) {
                int result = key.compareTo(p.key);
                // < 0 key < p.key
                // > 0 key > p.key
                // == 0 key == p.key
                if (result < 0) {
                    p = p.left;
                } else if (result > 0) {
                    p = p.right;
                } else {
                    return p.value;
                }
            }
        return null;
    }

    public Object min() {
        return null;
    }

    public Object max() {
        return null;
    }

    /**
     * 存储关键字和对应值
     * @param key
     * @param value
     */
    public void put(K key, V value) {
    }

    /**
     * 查找关键字的前驱值
     * @param key
     * @return
     */
    public Object successor(K key) {
        return null;
    }

    /**
     * 查找关键字的后驱值
     * @param key
     * @return
     */
    public Object predecessor(K key) {
        return null;
    }

    /**
     * 根据关键字删除吗，但是不会关联到这个节点的下一个
     * @param key
     * @return
     */
    public Object delete(K key) {

        return null;

    }




}
