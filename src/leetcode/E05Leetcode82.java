package leetcode;

import datastructure.ListNode;

public class E05Leetcode82 {
    public ListNode deleteDuplicates(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }

        if (p.val == p.next.val) {
            ListNode x = p.next.next;
            while (x != null && x.val == p.val) {
                x = x.next;
            }
            return deleteDuplicates(x);
        } else {
            p.next =  deleteDuplicates(p.next);
            return p;
        }
    }

      public ListNode deleteDuplicates1(ListNode head) {
            if (head == null || head.next == null) {
            return head;
        }

        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2, p3;

        while ((p2 = p1.next) != null
                && (p3 = p2.next) != null) {
            if (p2.val == p3.val) {
                while ((p3 = p3.next) != null
                && p3.val == p2.val) {
                }
                // 清理重复的元素后
                p1.next = p3;
            } else {
                p1 = p1.next;
            }
        }

        return s.next;
    }
}
