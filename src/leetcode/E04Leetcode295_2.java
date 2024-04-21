package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E04Leetcode295_2 {

    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;

    public E04Leetcode295_2() {
        // 大
        left = new PriorityQueue<Integer>((a, b) -> Integer.compare(b, a));
        // 小
        right = new PriorityQueue<Integer>((a, b) -> Integer.compare(a, b));
    }

    public void addNum(int num) {
        if (left.size() == right.size()) {
            right.offer(num);
            int poll = right.poll();
            left.offer(poll);
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            return (right.peek() + left.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    @Override
    public String toString() {
        int size = left.size();
        int[] left = new int[size];
        for (int i = 0; i < left.length; i++) {
            left[size - 1 - i] = (Integer) this.left.toArray()[i];
        }
        Object[] right =  Arrays.copyOf(this.right.toArray(), this.right.size());
        return Arrays.toString(left) + "<->" + Arrays.toString(right);
    }

    public static void main(String[] args) {
        E04Leetcode295_2 test = new E04Leetcode295_2();
        test.addNum(1);
        test.addNum(2);
        test.addNum(3);
        test.addNum(4);
        System.out.println(test);
    }
}
