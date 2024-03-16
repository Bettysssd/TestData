import java.util.ArrayList;
import java.util.LinkedList;

public class E02HanoiTower {
    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();


    static void init(int n) {
        for (int i = n; i >= 1; i--) {
            a.addLast(i);
        }
    }

    static void print() {
        System.out.println("-----------------------------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }


    /**
     *
     * @param n 圆盘数量
     * @param a 源
     * @param b 中间（借）
     * @param c 目标
     */
    static void move(int n, LinkedList<Integer> a,
                       LinkedList<Integer> b,
                       LinkedList<Integer> c ) {
        if (n == 0) {
            return;
        }
        move(n-1, a, c, b);
        c.addLast(a.removeLast()); // 中间
        print();
        move(n-1, b, a, c);

    }

    // T(n)=2T(n-1)+c,T(1)=c O(2^64)

    public static void main(String[] args) {
        init(3);
        print();
        move(3, a, b, c);
    }
}
