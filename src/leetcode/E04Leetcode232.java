package leetcode;

import stack.ArrayStack;

public class E04Leetcode232 {

   ArrayStack<Integer> s1 = new ArrayStack<>(100);
   ArrayStack<Integer> s2 = new ArrayStack<>(100);

    public void push(int x) { //向队尾添加
            s2.push(x);
    }

    public int pop() { // 从队列头移除
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
         return s1.pop();
    }

    public int peek() { // 从队列头部获取
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
         return s1.peek();

    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
