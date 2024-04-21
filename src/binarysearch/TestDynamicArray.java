package binarysearch;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestDynamicArray {

    @Test
    public void test1() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
//        dynamicArray.addLast(5);

        dynamicArray.add(2,5);

        for (int i = 0; i < 5; i++) {
            System.out.println(dynamicArray.get(i));

        }
    }

    @Test
    public void test2() {
        DynamicArray dynamicArray = new DynamicArray();
        AtomicInteger sum = new AtomicInteger();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.forEach((element)->{
//            System.out.println(element);
            sum.addAndGet(element);
        });

        System.out.println(sum.get());
    }



    @Test
    public void test3() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        for (Integer element: dynamicArray) { // hasnext() next()
            System.out.println(element);
        }
    }

    @Test
    public void test4() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);

        dynamicArray.stream().forEach((element)->{
            System.out.println(element);
        });
    }

    @Test
    public void test5() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);

        int remove = dynamicArray.remove(4);
        System.out.println(remove);
        System.out.println("---------------");
        dynamicArray.foreach((element)->{
            System.out.println(element);
        });
    }

    @Test
    public void test6() {
        DynamicArray dynamicArray = new DynamicArray();
        for (int i = 0; i < 9; i++) {
            dynamicArray.addLast(i+1);
        }

        dynamicArray.stream().forEach((e)->{
            System.out.println(e);
        });
    }




}
