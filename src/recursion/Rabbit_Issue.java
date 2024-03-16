//斐波那契数列
public class Rabbit_Issue {

    public static int f(int n) {


        if (n == 1 || n == 2) {
            return 1;
        }

        int x = f(n - 1);
        int y = f(n - 2);

        return x + y;
    }

    public static void main(String[] args) {
        int f = f(6);
        System.out.println(f);
    }

}
