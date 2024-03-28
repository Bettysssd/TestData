package test;

import java.util.LinkedList;

/**
 * 1. 遇到非运算符 直接拼串
 * 2. 遇到 + - * /
 *    - 它的优先级比栈顶运算符高， 入栈， 如： 栈中是+ 当前是 *
 *    - 否则把栈里优先级 >= 它 的都出栈 ，它再入栈， 如： 栈中是 +*， 当前是-
 * 3. 遍历完成， 栈里剩余的运算符依次出栈
 * 4. 带（）
 *    - 左括号直接入栈， 左括号优先设置为0
 *    - 在括号就把栈里到左括号为止的所有运算符进行出栈
 */

public class E03InfixToSuffix {

    public static void test() {
        int a = 10;
        int b = 20;
        int c = 5;
        int d = (a + b )* c; // ab+
    }


    public static void main(String[] args) {
//        test();
//        System.out.println(infixToSuffix("a+b"));
//        System.out.println(infixToSuffix("a+b-c"));
//        System.out.println(infixToSuffix("a+b*c"));
//        System.out.println(infixToSuffix("a*b-c"));
        System.out.println(infixToSuffix("(a+b)*c"));
        System.out.println(infixToSuffix("(a+b*c-d)*e"));
        System.out.println(infixToSuffix("a*(b+c)"));
    }

    static String infixToSuffix(String exp) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(exp.length());
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if (stack.isEmpty()) {
                        stack.push(c);
                        break;
                    } else {
                        if (priority(c) > priority(stack.peek())) {
                            stack.push(c);
                        } else {
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                sb.append(stack.pop());
                            }
                            stack.push(c);
                        }
                        break;
                    }
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    static int priority (char c) {
         switch (c) {
             case '*':
             case '/':
                 return 2;
             case '+':
             case '-':
                 return 1;
             case '(':
                 return 0;
             default:
                 throw new IllegalArgumentException("不合法的运算符：" + c);
        }
    }
}
