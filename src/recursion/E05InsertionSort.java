package recursion;

import java.util.Arrays;

public class E05InsertionSort {

    public static void sort(int[] a) {
        insertion(a, 1);
    }

    private static void insertion(int[] a, int low) {
        if (low == a.length) {
            return;
        }

        int t = a[low];
        int i = low - 1; // 已排序指针

        while (i >= 0 && a[i] > t) {  // 没有找到插入位置

            a[i+1] = a[i]; // 空出插入位置
            i--;
        }

        // 找到插入位置
        a[i+1] = t;

        insertion(a, low + 1);

    }

    public static void main(String[] args) {
        int[] a = new int[] {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));

    }


}
