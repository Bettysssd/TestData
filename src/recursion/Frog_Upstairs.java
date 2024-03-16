package recursion;

public class Frog_Upstairs {

    public static int f(int n) {



        if (n == 2) {
            return 2;
        }

        if (n == 1) {
            return 1;
        }

        int x = f(n - 1);
        int y = f(n - 2);

        return x + y;
    }

    public static void main(String[] args) {
        int f = f(3);
        System.out.println(f);
   }
}
