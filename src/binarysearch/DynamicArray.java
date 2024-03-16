package binarysearch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer> {
    private int size = 0; // logic
    private int capacity = 8;
    private int[] array = {};


    public void addLast(int element) {
//        array[size] = element;
//        size++;
        add(size,element);
    }

    public void add(int index, int element) {
        checkAndGrow();

        if (index >=0 && index < size) {
            System.arraycopy(array, index,
                    array, index + 1, size - index);
        } else if (index < 0) {
            throw new RuntimeException("index typing wrong, retry again please!");
        }
        array[index] = element;
        size++;
    }

    private void checkAndGrow() {
        //check
        if (size == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            // just do it    1.5plus / 1.618plus / 2plus
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0,
                    newArray, 0, size);
            array = newArray;
        }
    }

    public int get(int index) {
        return array[index];
    }

    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
//            System.out.println(array[i]);
            consumer.accept(array[i]);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Integer next() {
                return array[i++];
            }
        };

    }


    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array,0,size)); //[0,size)
    }


    public int remove(int index) { //[0,size)
        int removed = array[index];
        if (index < size - 1) {
                System.arraycopy(array, index + 1,
                        array, index, size - index - 1);


            }
        size--;
        return removed;

    }


}
