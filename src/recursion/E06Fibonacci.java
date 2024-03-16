package recursion;

//斐波那契数列
public class E06Fibonacci {

    public static int f(int n) {

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int x = f(n - 1);
        int y = f(n - 2);

        return x + y;
    }

    public static void main(String[] args) {
        int f = f(8);
        System.out.println(f);
    }

}
