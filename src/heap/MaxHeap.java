import java.util.Arrays;

// 大顶堆
public class MaxHeap {
    int[] array;
    int size;

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
    }

    //建堆
    private void heapify() {
        // 如何找到最后这个非叶子节点 size/2 - 1
        for (int i = size / 2 - 1; i >= 0 ; i--) {
            down(i);

        }

    }

    // 获取堆顶元素
    public int poll() {
        return 0;
    }

    // 堆的尾部添加元素
    public boolean offer(int offered) {
        return false;
    }

    // 将inserted 元素上浮， 直到offered小于父元素或到堆顶
    public void up(int offered) {

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

        if (left < size && array[right] > array[max]) {
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

    public int poll(int index) {
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        MaxHeap maxHeap = new MaxHeap(array);
        System.out.println(Arrays.toString(maxHeap.array));
    }
}
