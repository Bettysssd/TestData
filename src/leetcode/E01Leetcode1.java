package leetcode;

import java.util.HashMap;

public class E01Leetcode1 {

    /*
    思路：
    1. 循环遍历数组，拿到每个数字 x
    2. 以 target-x 作为 key 到 hash 表查找
      1）若没找到，将 x 作为 key， 它的索引作为 value 放入 hash 表
      2）若找到了，返回 x 和它配对数的索引即可
     */

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            if (map.containsKey(y)) {
                return new int[] {i, map.get(y)};
            }else {
                map.put(x, i);
            }
        }
        return null;
    }
}
