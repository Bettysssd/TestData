package queue;

import datastructure.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("all")
public class BlockingQueue1<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length;
    }


    public BlockingQueue1(int capacity) {
        array = (E[]) new Object [capacity];
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();



    @Override
    public void offer(E e) throws InterruptedException { //等待队列非空 poll
        lock.lockInterruptibly();
        try {
            while (isFull()) {
                tailWaits.await(); //进入等待 （多久）
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException { // 毫秒
        lock.lockInterruptibly();
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()) {
                if (t <= 0) {
                    return false;
                }
                t = tailWaits.awaitNanos(t);  // 最多等待多少纳秒  1s 返回值代表
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            E e = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }
            size--;
            tailWaits.signal();
            return e;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                builder.append(array[i]);
            } else{
                builder.append(array[i]).append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue1<String> queue =  new BlockingQueue1<>(3);
        queue.offer("mession1");

        new Thread(() -> {
            try {
                queue.offer("mession2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();


        new Thread(() -> {
            try {
                System.out.println(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }

}