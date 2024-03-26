package queue;

import datastructure.Queue;

import java.util.Iterator;

@SuppressWarnings("all")
public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {

    private final E [] array;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue3(int capacity) {
       /* //1.抛异常
        if ((capacity & capacity - 1) != 0) {
            throw new IllegalArgumentException("capacity 必须是2的幂");
        }
        array = (E[])new Object[capacity];*/
        // 2. 改成2^n   13 --> 16  22 -> 32
        capacity -= 1;
        capacity |= capacity>>1;
        capacity |= capacity>>2;
        capacity |= capacity>>4;
        capacity |= capacity>>8;
        capacity |= capacity>>16;
        capacity +=1;
        array = (E[]) new Object[capacity];
    }



    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail & (array.length - 1)] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head & (array.length - 1)];
        head++;
        return value;
    }

    // 获取头部的元素值
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head & (array.length - 1)];
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
                E value = array[p & (array.length - 1)];
                p++;
                return value;
            }
        };
    }
}
