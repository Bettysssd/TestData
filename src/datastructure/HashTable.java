package datastructure;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HashTable {

    // 节点类
    static class Entry {
        int hash; // 哈希码
        Object key; // 键
        Object value; // 值
        Entry next;

        public Entry(int hash, Object value, Object key) {
            this.hash = hash;
            this.value = value;
            this.key = key;
        }
    }

    Entry[] table = new Entry[16];
    int size = 0; // 元素的个数
    float loadFactor = 0.75f; // 12 阈值
    int threshold = (int) (loadFactor * table.length);

    /* 求模运算替换为位运算
            - 前提： 数组长度是 2 的 n 次方
            - hash % 数组长度 等价于 hash & （数组长度 - 1）
     */

    // 根据 hash 码获取 value
    public Object get(int hash, Object key) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            return null;
        }
        Entry p = table[index];
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    public Object get (Object key) {
        int Hash = getKeyHash(key);
        return get(Hash, key);
    }

    // 向 hash 表存入新的 key value, 如果 key 重复， 则更新 value
    public void put(int hash, Object key, Object value) {
        int index = hash & (table.length - 1);
        // 1. index 处有空位, 直接新增
        if (table[index] == null) {
            table[index] = new Entry(hash, key, value);
        } else {
            // 2. index 处无空位， 沿链表查找, 有重复key更新， 否则新增
            Entry p = table[index];
            while (true) {
                // 更新
                if (p.key.equals(key)) {
                    p.value = value;
                    return;
                }

                if (p.next == null) {
                    break;
                }
                p = p.next;
            }
            // 新增
            p.next = new Entry(hash, key, value);
        }
        size++;
        if (size > threshold) {
            resize();
        }
        
    }

    public void put (Object key, Object value) {
        int Hash = getKeyHash(key);
        put(Hash, key, value);
    }

    private void resize() {
        Entry[] newTable = new Entry[table.length << 1];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i]; // 拿到每个链表头
            if (p != null) {
                // 拆分链表,移动到新数组

                /*
                    拆分规律
                    * 一个链表最多拆成两个
                    * hash & table.length == 0 的一组
                     * hash & table.length != 0 的一组
                 */
                Entry a = null;
                Entry b = null;
                Entry ahead = null;
                Entry bhead = null;
                while (p != null) {
                    if ((p.hash & table.length) == 0) {
                        if (a != null) {
                            a.next = p;
                        } else {
                            ahead = p;
                        }
                        // 分配到a
                        a = p;
                    } else {
                        if (b != null) {
                            b.next = p;
                        } else {
                            bhead = p;
                        }
                        // 分配到b
                        b = p;
                    }
                    p = p.next;
                }
                // 规律： a 链表保持索引位置不变， b 链表索引位置+table.length
                if (a != null) {
                    a.next = null;
                    newTable[i] = ahead;
                }
                if (b != null) {
                    b.next = null;
                    newTable[i + table.length] = bhead;
                }

            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    // 根据 hash 码删除， 返回删除的 value
    public Object remove(int hash, Object key) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            return null;
        }
        Entry p = table[index];
        Entry prev = null;
        while (p != null) {
            if (p.key.equals(key)) {
                if (prev == null) {
                    table[index] = p.next;
                } else {
                    prev.next = p.next;
                }
                size--;
                return p.value;
            }
            prev = p;
            p = p.next;
        }
        return null;
    }

    public Object remove (Object key) {
        int Hash = getKeyHash(key);
        return remove(Hash, key);
    }

    private static int getKeyHash(Object key) {
        if (key instanceof String ) {
            String k = (String) key;
            return Hashing.murmur3_32().hashString(k, StandardCharsets.UTF_8).asInt();
        }
        int hash = key.hashCode();
        return hash ^ (hash >>> 16); // 与自身的高16位进行异或
    }

    public void print() {
        int[] sums = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];
            while (p != null) {
                sums[i]++;
                p = p.next;
            }
        }
        Map<Integer, Long> collect = Arrays.stream(sums).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(collect);
    }

    public static void main(String[] args) {
        HashTable table = new HashTable();
        /*
        HashTable table = new HashTable();
        for (int i = 0; i < 2000000; i++) {
            Object obj = new Object();
            table.put(obj,obj);
        }
        table.print();

         */
//        int hash = Hashing.murmur3_32().hashString("abc", StandardCharsets.UTF_8).asInt();
//        System.out.println(hash);
        table.put(2,2);
        table.put(524290,2);
        table.print();
    }

        /*
        String s1 = "abc";
        // 原则： 值相同的字符串生成相同的 hash码， 尽量让值不同的字符串生成不同对的 hash 码

          对于 abc  a * 100 * b * 10 + c
          对于 bac  b * 100 * a * 10 + c


        int hash = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            System.out.println((int) c);
            // 选比较大的质数
//            hash = hash * 31 + c;
            // 先 x 32 - hash   --> 2的5次方
             hash = (hash << 5) - hash + c;
        }
        System.out.println(hash);
    }
    */
}
