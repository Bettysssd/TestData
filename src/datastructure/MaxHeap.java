package datastructure;

import java.util.Arrays;

// 大顶堆
public class MaxHeap {
    int[] array;
    int size;

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
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
    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        up(offered);
        size++;
        return false;
    }

    // 将inserted 元素上浮， 直到offered小于父元素或到堆顶
    public void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;

    }
    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    // 将parent 索引处的元素下潜， 与两个孩子较大者交换， 直到没孩子或孩子没有比它大
    public void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && array[left] > array[max]) {
            max = left;
        }

        if (right < size && array[right] > array[max]) {
            max = right;
        }

        if (max != parent) { // 表示已经找到了更大的
            swap(max, parent);
            down(max);
        }
    }

    // 交换两个索引的元素
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
        int[] array = {2, 3, 1, 7, 6, 4, 5};
        MaxHeap heap = new MaxHeap(array);
        System.out.println(Arrays.toString(heap.array));

        while (heap.size > 1) {
            heap.swap(0, heap.size-1);
            heap.size--;
            heap.down(0);
        }

        System.out.println(Arrays.toString(heap.array));

    }
}
