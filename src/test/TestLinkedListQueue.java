package test;

import org.junit.Test;
import queue.ArrayQueue3;
import queue.LinkedListQueue;

import java.util.Iterator;

public class TestLinkedListQueue {

    @Test
    public void offer() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void peek() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        System.out.println(queue.peek());
        queue.offer(1);
        System.out.println(queue.peek());
        queue.offer(2);
        System.out.println(queue.peek());
    }


    @Test
    public void test() {
        ArrayQueue3<Integer> queue = new ArrayQueue3<Integer>(10);
        queue.head = 2147483640;
        queue.tail = queue.head;;

        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }


        for (Integer value: queue) {
            System.out.println(value);
        }
        

    }
}
