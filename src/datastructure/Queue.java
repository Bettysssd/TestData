package datastructure;

public interface Queue<E> {

    //向队尾插入值
    boolean offer(E value);

    // 从对头中获取值，并移除  空返回null
    E poll();

    // 从对头中获取值，不移除 空返回null
    E peek();

    // 检查队列是否为空
    boolean isEmpty();

    // 检查队列是否已经满了
    boolean isFull();
}
