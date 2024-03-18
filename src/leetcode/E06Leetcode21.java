public class E06Leetcode21 {
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {

        ListNode s = new ListNode(-1, null);
        ListNode p = s;

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

//        if (p1.next != null) {
//            p.next = p1;
//        }
//
//        if (p2.next != null) {
//            p.next = p2;
//        }


        p.next = (p1 == null)? p2:p1;


        return s.next;
    }


}
