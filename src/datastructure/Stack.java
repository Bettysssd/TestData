package datastructure;

public interface Stack<E> {

    // 压入元素
    boolean push(E value);

    // 元素弹出
    E pop();

    // 返回元素，不弹出
    E peek();

    //
    boolean isEmpty();

    boolean isFull();
}