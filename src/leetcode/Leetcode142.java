package leetcode;

import recursion.ListNode;

public class Leetcode142 {

    public ListNode deleteCycle(ListNode head) {
         ListNode h = head; // 兔
         ListNode t = head; // 龟

        while (h != null && h.next != null) {
            t = t.next;
            h = h.next.next;
            if (h == t) {
                // 进入第二个阶段
                t = head;
                while (true) {
                    if (t == h) { // 第二次相遇
                        return t;
                    }
                    t = t.next;
                    h = h.next;

                }
            }
        }
        return null;
    }
}
