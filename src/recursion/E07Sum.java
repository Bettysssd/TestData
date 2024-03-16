package recursion;

public class E07Sum {
    public static long sum(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sum(n - 1);
    }

    public static void main(String[] args) {
//        System.out.println(sum(15000));
        long n = 100000000;
        long sum = 0;
        for (long i  = n; i >= 1; i--) {
            sum += i;
        }

        System.out.println(sum);
    }
}
