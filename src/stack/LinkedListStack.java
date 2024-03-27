package stack;

import datastructure.Stack;

public class LinkedListStack<E> implements Stack<E>, Iterable<E> {

    static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
