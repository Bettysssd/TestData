package leetcode;

import datastructure.ListNode;

public class E04Leetcode83 {

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p1 = head;
        ListNode p2;
        while ((p2 = p1.next) != null) {
            if (p1.val == p2.val) {
                // 删除p2
                p1.next = p2.next;
            } else {
                // 向后平移
                p1 = p1.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }

        if (p.val == p.next.val) {
            return deleteDuplicates(p.next);
        } else {
            p.next = deleteDuplicates(p.next);
            return p;
        }

    }
}
