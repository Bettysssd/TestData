package datastructure;

import java.util.Arrays;

public class Heap {

    int[] array;
    int size;
    boolean max;

    public int getSize() {
        return size;
    }

    public Heap(int capacity, boolean max) {
        this.array = new int[capacity];
        this.max = max;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("there has none of any elements here!");
        }
        return array[0];
    }

    //建堆
    private void heapify() {
        // 如何找到最后这个非叶子节点 size/2 - 1
        for (int i = size / 2 - 1; i >= 0 ; i--) {
            down(i);
        }

    }

    public int[] getArray() {
        return array;
    }

    // 获取并删除堆顶元素
    public int poll() {

        if (size == 0) {
            throw new RuntimeException("there has none of any elements here!");
        }

        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }



    // 获取并删除指定位置的元素
    public int poll(int index) {

        if (size == 0) {
            throw new RuntimeException("there has none of any elements here!");
        }

        int selected = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return selected;
    }



    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    // 堆的尾部添加元素
    public void offer(int offered) {
        if (size == array.length) {
            // 扩容
            grow();
        }
        up(offered);
        size++;
    }

    private void grow() {
       int capacity = size + (size >> 1);
       int[] newArray = new int[capacity];
       System.arraycopy(array, 0, newArray, 0, size);
       array = newArray;
    }

    // 将inserted 元素上浮， 直到offered小于父元素或到堆顶
    public void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if ( max ? (offered > array[parent]) : (offered < array[parent])) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;

    }
    public Heap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    // 将parent 索引处的元素下潜， 与两个孩子较大者交换， 直到没孩子或孩子没有比它大
    public void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int maxOrMin = parent;
        if (left < size && (max ? (array[left] > array[maxOrMin]) : (array[left] < array[maxOrMin]))) {
            maxOrMin = left;
        }

        if (right < size && (max ? (array[right] > array[maxOrMin]) : (array[right] < array[maxOrMin]))) {
            maxOrMin = right;
        }

        if (maxOrMin != parent) { // 表示已经找到了更大的
            swap(maxOrMin, parent);
            down(maxOrMin);
        }
    }

    // 交换两个索引的元素
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Heap left = new Heap(5, true);
        for (int i = 1; i <= 10; i++) {
            left.offer(i);
            System.out.println(Arrays.toString(left.array));

        }
    }

}
