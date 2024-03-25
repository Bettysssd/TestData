package leetcode;

import datastructure.ListNode;

public class E01Leetcode206 {
    // 方法1
    public ListNode reverseList1(ListNode o1) {
        ListNode n1 = null; // 不断更新的头节点（相当于头插法)
        ListNode p = o1; //原来的头节点
        while (p != null) {
            n1 = new ListNode(p.val, n1);
            p = p.next;
        }
        return n1;
    }

    // 方法2
    public ListNode reverseList2(ListNode head) {
        List list1 = new List(head);
        List list2 = new List(null);

        while (true) {
            ListNode first = list1.removeFirst();
            if (first == null) {
                break;
            }
            list2.addFirst(first);
        }

        return list2.head;

    }
        static class List {
            ListNode head;

            public List(ListNode head) {
                this.head = head;
            }

            public void addFirst(ListNode first) {
                first.next = head;
                head = first;
            }

            public ListNode removeFirst() {
                ListNode first = head;
                if (first != null) {
                    head = first.next;
                }
                return first;
            }

        }

  /*  方法3-- 递归
   1，2，3，4，5
   */
  public ListNode reverseList3(ListNode p) {
      if (p == null || p.next == null) {
          return p; // 是为了返回最后的节点
      }

      ListNode last = reverseList3(p.next); // 同上作用

      p.next.next = p;  //重新调整指针的指向，但是不能陷入死循环的指向中去
      p.next = null;
      return last;
  }

  // 方法4
  public ListNode reverseList4(ListNode o1) {
  // 1.空链表 2.一个元素
    if (o1 == null || o1.next == null) {
        return o1;
    }
  ListNode o2 = o1.next;
  ListNode n1 = o1;
  while (o2 != null) {
        o1.next = o2.next;
        o2.next = n1;
        n1 = o2;
        o2 = o1.next;
  }
  return n1;
}

  // 方法5
  public ListNode reverseList5(ListNode o1) {
      // 1.空链表 2.一个元素
      if (o1 == null || o1.next == null) {
          return o1;
      }
      ListNode n1 = null;
      while (o1 != null) {
          ListNode o2 = o1.next;
          o1.next = n1;
          n1 = o1; // 更新o1的位置
          o1 = o2;  // 复原
      }
      return n1;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode n1 = new E01Leetcode206().reverseList5(o1);
        System.out.println(n1);
    }
}
