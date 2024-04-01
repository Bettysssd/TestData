public class E01Leetcode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        MinHeap heap = new MinHeap(lists.length);
        for (ListNode h : lists) {
            heap.offer(h);
        }
        return null;
    }
}
