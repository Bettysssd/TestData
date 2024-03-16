package recursion;

import org.junit.Test;

public class TestSinglyLinkedList {


    private SinglyLinkedList get() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        return list;
    }

    @Test
    public void test1() {
        SinglyLinkedListSentinel2 list = new SinglyLinkedListSentinel2();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        list.loop2((value)->{
            System.out.println(value);
        });

        list.removeFirst();

        list.loop2((value)->{
            System.out.println(value);
        });
    }

    @Test
    public void test2() {
        SinglyLinkedList list = get();

        list.loop3((value)->{
            System.out.println("before"+ value);
        }, (value)->{
            System.out.println("after" + value);
        });
    }

    @Test
    public void test3() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        int i = list.get(20);
        System.out.println(i);
    }

    @Test
    public void test4() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

       list.insert(0,10);
        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    public void test5() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

       list.removeFirst();
        for (Integer value : list) {
            System.out.println(value);
        }

        System.out.println("-----------------");
        list.removeFirst();
        for (Integer value : list) {
            System.out.println(value);
        }

        System.out.println("-----------------");
        list.removeFirst();
        for (Integer value : list) {
            System.out.println(value);
        }

        System.out.println("-----------------");
        list.removeFirst();
        for (Integer value : list) {
            System.out.println(value);
        }

        list.removeFirst();
    }

    @Test
    public void test6() {
//        SinglyLinkedList list1 = new SinglyLinkedList();
//        list1.addLast(1);
//        list1.addLast(2);
//        list1.addLast(3);
//        list1.addLast(4);
//        list1.remove(2);
//        for (Integer value : list1) {
//            System.out.println(value);
//        }

//        SinglyLinkedList list2 = new SinglyLinkedList();
//        list2.addLast(1);
//        list2.addLast(2);
//        list2.addLast(3);
//        list2.addLast(4);
//        list2.remove(0);
//        for (Integer value : list2) {
//            System.out.println(value);
//        }
//
//        SinglyLinkedList list3 = new SinglyLinkedList();
//        list3.addLast(1);
//        list3.addLast(2);
//        list3.addLast(3);
//        list3.addLast(4);
//        list3.remove(5);
//        for (Integer value : list3) {
//            System.out.println(value);
//        }

        SinglyLinkedList list4 = new SinglyLinkedList();
        list4.addLast(1);
        list4.addLast(2);
        list4.addLast(3);
        list4.addLast(4);
        list4.remove(4);
        for (Integer value : list4) {
            System.out.println(value);
        }
    }


}
