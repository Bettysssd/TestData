package leetcode;

import datastructure.ListNode;
import datastructure.MinHeap1;

public class E01Leetcode23 {
    public static ListNode mergeKLists(ListNode[] lists) {
        MinHeap1 heap = new MinHeap1(lists.length);
        for (ListNode h : lists) {
            //加入到小顶堆
            if (h != null) {
                heap.offer(h);
            }
        }
        // 准备一个链表 (A sentinel)
        ListNode s = new ListNode(-1, null);
        ListNode t = s;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            t.next = min;
            t = min;
            if (min.next != null) {
                heap.offer(min.next);
            }

        }
        return s.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        ListNode l1 = new ListNode(1,
                new ListNode(4,
                        new ListNode(5, null)));
        ListNode l2 = new ListNode(1,
                new ListNode(3,
                        new ListNode(4, null)));
        ListNode l3 = new ListNode(2,
                new ListNode(6,null));
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;

        System.out.println(mergeKLists(lists).toString());


    }
}