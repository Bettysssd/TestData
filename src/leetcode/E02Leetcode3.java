package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class E02Leetcode3 {



    // 要点：

    /**
     * 要点
     * 1. 用begin和end表示子串的起始和结束位置
     * 2. 用 hash 检查重复字符
     * 3. 从左向右查看每个字符，如果：
     *  - 没遇到重复字符，则end++
     *  - 遇到重复字符，则begin++
     *  - 将当前字符放入hash表
     *  4. end-begin + 1就是子串长度
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int begin = 0;
        int MaxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
           char c =  s.charAt(end);
           if (map.containsKey(c)) {
               begin = Math.max(begin,map.get(c) + 1);
               map.put(c,end);
           } else {
               map.put(c, end);
           }
            System.out.println(s.substring(begin, end+1));
            MaxLength = Math.max(MaxLength, end - begin + 1);
        }
        return MaxLength;
    }

    // 元素角标
    int index = 0;
    // 当前数组中已经存入的元素个数
    int count = 0;
    /**
     * 寻找当前便利元素最先出现在数组中的角标
     * @param chars
     * @param c
     * @return
     */
   Boolean searchCharIndex(int begin, char c, char[] chars) {
       for (int i = begin; i <= count; i++) {
           if (chars[i] == c) {
               index = i + 1;
               return true;
           }
       }
       return false;
    }

    public int lengthOfLongestSubstring1(String s) {
        int begin = 0;
        int MaxLength = 0;
        char[] chars = new char[128];
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (searchCharIndex(begin, c, chars)) {
                begin = index ;
            }
               chars[end] = c;
               count++;

            /*
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = begin; i <= end; i++) {
               stringBuilder.append(chars[i]);
            }
            System.out.println(stringBuilder);
             */
            MaxLength = Math.max(MaxLength, end - begin + 1);
        }
        return MaxLength;
    }

    public static void main (String[] args) {
//        System.out.println(new E02Leetcode3().lengthOfLongestSubstring1("bbbb"));
        System.out.println(new E02Leetcode3().lengthOfLongestSubstring1("pwwkew"));

    }
}
