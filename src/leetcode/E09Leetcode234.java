package leetcode;

import recursion.ListNode;

public class E09Leetcode234 {

    public boolean isPalindrome(ListNode head) {

        ListNode p1 = head;  // 慢
        ListNode p2 = head;  // 快
        ListNode n1 = null;  // 新头
        ListNode o1 = head;  // 旧头

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            o1.next = n1;
            n1 = o1;
            o1 = p1;
        }

        if (p2 != null) { //奇数节点
            p1 = p1.next;
        }

        while (n1 != null) {
            if (n1.val != p1.val) {
                return false;
            }
            n1 = n1.next;
            p1 = p1.next;
        }
        return true;
    }


}

