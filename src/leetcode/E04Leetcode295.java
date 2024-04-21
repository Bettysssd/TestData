package leetcode;

import datastructure.Heap;

import java.util.Arrays;

public class E04Leetcode295 {

   Heap left;
   Heap right;

    public E04Leetcode295() {
        left = new Heap(10, true);
        right = new Heap(10, false);
    }

    public void addNum(int num) {
        if (left.getSize() == right.getSize()) {
            right.offer(num);
            int poll = right.poll();
            left.offer(poll);
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if (left.getSize() == right.getSize()) {
            return (right.peek() + left.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    @Override
    public String toString() {
       int size = left.getSize();
       int[] left = new int[size];
        for (int i = 0; i < left.length; i++) {
            left[size - 1 - i] = this.left.getArray()[i];
        }
        int[] right = Arrays.copyOf(this.right.getArray(), this.right.getSize());
        return Arrays.toString(left) + "<->" + Arrays.toString(right);
    }

    public static void main(String[] args) {
        E04Leetcode295 test = new E04Leetcode295();
        test.addNum(1);
        test.addNum(2);
        test.addNum(3);
        test.addNum(7);
        test.addNum(8);
        test.addNum(9);
        System.out.println(test);
        test.addNum(10);
        System.out.println(test);
    }
}
