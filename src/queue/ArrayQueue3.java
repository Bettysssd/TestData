package queue;

import datastructure.Queue;

import java.util.Iterator;

@SuppressWarnings("all")
public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {

    private final E [] array;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue3(int capacity) {
        array = (E[])new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail % array.length] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head % array.length];
        head++;
        return value;
    }

    // 获取头部的元素值
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head % array.length];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
               return p != tail;
            }

            @Override
            public E next() {
                E value = array[p % array.length];
                p++;
                return value;
            }
        };
    }
}
