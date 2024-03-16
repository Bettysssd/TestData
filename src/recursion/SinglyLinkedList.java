package recursion;

import java.util.Iterator;
import java.util.function.Consumer;

public class SinglyLinkedList implements Iterable<Integer>{
    private Node head = null;

    @Override
    public Iterator<Integer> iterator() {
        // 匿名内部类 -> 带名字的内部类
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<Integer> {
        Node p = head;

        @Override
        public boolean hasNext() { // 是否有下一个元素
            return p != null;
        }

        @Override
        public Integer next() { // 返回当前元素的值，并且指向下一个
            int v = p .value;
            p = p.next;
            return v;
        }
    }


    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value) {
        // empty
//       head = new Node(value, null);
        // not empty
        head = new Node(value, head);

    }

    public void loop1(Consumer<Integer> consumer) {
        Node p = head;
        while (p != null) {
          consumer.accept(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    private Node findLast() {
        if (head == null) {
            return null;
        }


        Node p;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    public void addLast(int value) {
        Node last = findLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);
    }

//    public void test() {
//        int i = 0;
//        for (Node p = head; p != null; p = p.next, i++) {
//            System.out.println(p.value + ", index is :" + i);
//        }
//    }

    private Node findNode(int index) {
        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw illegalIndex(index);
        }

        return node.value;
    }

    public void insert(int index, int value) {
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node previous = findNode(index - 1);
        if (previous == null) {
            throw illegalIndex(index);
        }
        previous.next = new Node(value, previous.next);

    }

    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index));
    }

    public void removeFirst() {
        if (head == null) {
            throw illegalIndex(0);
        }
        head = head.next;
    }


    public void remove(int index) {
        if (index == 0) {
            removeFirst();
            return;
        }
        Node previous = findNode(index - 1);
        if (previous == null) {
            throw illegalIndex(index);
        }
        Node removed = previous.next;
        if (removed == null) {
            throw illegalIndex(index);
        }
        previous.next = removed.next;

    }

    public void loop3(Consumer<Integer> before,
                      Consumer<Integer> after) {
        recursion(head, before, after);
    }

    private void recursion(Node curr,
                           Consumer<Integer> before,
                           Consumer<Integer> after) { //针对某一个节点要进行的操作
        if (curr == null) {
            return;
        }
        before.accept(curr.value);
        recursion(curr.next, before, after);
        after.accept(curr.value);
    }







}
