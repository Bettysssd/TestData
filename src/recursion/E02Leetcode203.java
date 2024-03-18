package leetcode;

import recursion.ListNode;

public class E02Leetcode203 {

    public ListNode removeElements1(ListNode head, int val) {
       ListNode s = new ListNode(-1, head);
       ListNode p1 = s;
       ListNode p2;

       while ((p2 = p1.next) != null){
           if (p2.val == val) {
               // 找到，删除p2，且只p2后移
               p1.next = p2.next;
           } else {
               //都没有找到，p1，p2均向后平移
               p1 = p1.next;
           }

       }
       return s.next;
    }

    public ListNode removeElements(ListNode p, int val) {
       if (p == null) {
           return null;
       }

       if (p.val == val) {
           return removeElements(p.next, val);
       } else {
           p.next = removeElements(p.next, val);
           return p;
       }
    }



    public static void main(String[] args) {

    }
}
