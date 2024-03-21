package leetcode;

import recursion.ListNode;

public class Leetcode141 {

    public boolean hasCycle(ListNode head) {
         ListNode h = head; // 兔
         ListNode t = head; // 龟

        while (h != null && h.next != null) {
            t = t.next;
            h = h.next.next;
            if (h == t) {
                return true;
            }
        }
        return false;
    }
}
