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
}
