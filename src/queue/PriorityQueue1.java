package queue;

import datastructure.Priority;
import datastructure.Queue;

//无序数组的实现
@SuppressWarnings("all")
public class PriorityQueue1<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    // O(1)
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    //反回优先级最高的索引值
    private int selectMax() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }
    private void remove(int index) {
        if (index < size - 1) {
            // 移动
            System.arraycopy(array,index+1,
                    array, index, size-index-1);
        }
        array[--size] = null;
    }

    // O(n)
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        E e = (E) array[max];
        remove(max);
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[selectMax()];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
