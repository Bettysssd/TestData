package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class E03Leetcode49 {
    /*
    1. 遍历字符串数组，每个字符串的字符重新排序后作为 key
    2. 所谓分组， 其实就是准备一个集合，把这些单词加入到 key 相同的集合中
    3. 返回分组结果
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<> (map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = new E03Leetcode49().groupAnagrams(strs);
        System.out.println(lists);
    }
}
