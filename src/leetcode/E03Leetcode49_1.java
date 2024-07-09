package leetcode;

import java.util.*;

public class E03Leetcode49_1 {
    /*
    1. 遍历字符串数组，每个字符串的字符重新排序后作为 key
    2. 所谓分组， 其实就是准备一个集合，把这些单词加入到 key 相同的集合中
    3. 返回分组结果
     */
    static class ArrayKey {
        int[] keys = new int[26];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Objects.deepEquals(keys, arrayKey.keys);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(keys);
        }

        public ArrayKey(String str) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                keys[ch - 'a']++;
            }

           
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<ArrayKey, List<String>> map = new HashMap<>();
        for (String str : strs) {
            ArrayKey key = new ArrayKey(str);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = new E03Leetcode49_1().groupAnagrams(strs);
        System.out.println(lists);
    }
}
