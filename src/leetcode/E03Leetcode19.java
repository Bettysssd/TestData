package leetcode;

import recursion.ListNode;

public class E03Leetcode19 {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return s.next;
    }

    private int recursion(ListNode p, int n) {
        if (p == null) {
            return 0;
        }

        int nth = recursion(p.next, n); // 下一个节点的倒数位置
        if (nth == n) {
            p.next = p.next.next;
        }
        return nth + 1;
    }
}
