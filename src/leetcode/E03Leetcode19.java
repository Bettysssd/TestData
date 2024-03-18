public class E03Leetcode19 {


    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return s.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s;
        for (int i = 0; i < n + 1; i++) {
            p2 = p2.next;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
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

