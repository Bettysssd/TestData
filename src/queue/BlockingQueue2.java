package queue;

import datastructure.BlockingQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@SuppressWarnings("all")
public class BlockingQueue2<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger(); // 原子变量

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();

    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }


    public BlockingQueue2(int capacity) {
        array = (E[]) new Object [capacity];
    }



    @Override
    public void offer(E e) throws InterruptedException {
        int c; // 添加前元素个数
        tailLock.lockInterruptibly();
        try {
            //队列满则等待
            if (isFull()) {
                tailWaits.await();
            }
            // 不满则入队
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            // 修改size的大小
            c = size.getAndIncrement();  // size++ 获得的是size加之前的值
            if (c + 1 < array.length) {
                tailWaits.signal();
            }

        } finally {
            tailLock.unlock();
        }

        // 如果从0变成非空，由offer这边唤醒等待的非空poll线程
        if (c == 0) {
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException { // 毫秒
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        E e;
        int c;// 取走前的元素个数

        headLock.lockInterruptibly();
        try {
            //判断是否为空
            if (isEmpty()) {
                headWaits.await();
            }
            //队列不为空
             e = array[head];
            array[head] = null; // help gc
            if (++head == array.length) {
                head = 0;
            }
            // 修改size的大小
           c = size.getAndDecrement(); // size--
           if (c > 1) {
               headWaits.signal();
           }

        } finally {
            headLock.unlock();
        }

        // 队列从满到不满时，由poll唤醒等待不满的offer线程
        if (c == array.length){
            // 唤醒等待不满的offer线程
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock(); // ctrl + alt + t
            }
        }
        return e;
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
        BlockingQueue2<String> queue =  new BlockingQueue2<>(3);
        queue.offer("元素1");
        queue.offer("元素2");


        new Thread(() -> {
            try {
                queue.offer("元素3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();


        new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();

    }
}