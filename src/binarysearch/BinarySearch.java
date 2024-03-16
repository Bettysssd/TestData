package binarysearch;

public class BinarySearch {


    public static int binarySearch (int[] a, int target) {
        int i = 0, j = a.length - 1;
        // 元素在最左边是L次， 元素在最右边是2L次
        while (i <= j) {
            int m = (i + j) >>> 1;
           if (target < a[m]) {
               j = m - 1;
           } else if (a[m] < target){
               i = m + 1;
           } else {
               return m;
           }
        }
        return -1;
    }
}
