package leetcode;

import datastructure.MinHeap;

public class E03Leetcode703 {

    private MinHeap heap;

    public E03Leetcode703(int k, int[] nums) {
        heap = new MinHeap(k);
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (!heap.isFull()) {
          heap.offer(val);
        } else if (val > heap.peek()) {
            heap.replace(val);
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        E03Leetcode703 test = new E03Leetcode703(3, new int[]{4, 5, 8, 2});

        System.out.println(test.add(3));
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));
    }

}
