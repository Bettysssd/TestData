package recursion;

public class E03PascalTriangle {

    public static int element(int i, int j) {
        if (j == 0 || j == i) {
            return 1;
        }
        return element(i - 1, j - 1) + element(i - 1, j);
    }

    private static void printSpace(int n, int i) {
        int num = (n-1-i) * 2;
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }


    public static void print(int n) {
        for (int i = 0; i < n; i++) {
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d",element(i, j));
            };
            System.out.println();
        }
    }


    public static int element1(int[][] triangle, int i, int j) {
        if (triangle[i][j] > 0) {
            return triangle[i][j];
        }
        if (j == 0 || j == i) {
            triangle[i][j] = 1;
            return 1;
        }
        triangle[i][j] = element1(triangle, i - 1, j - 1) + element1(triangle,i - 1, j);
        return triangle[i][j];
    }




    public static void print1(int n) {
        int[][] triangle = new int[n][];

        for (int i = 0; i < n; i++) {
            printSpace(n,i);
            triangle[i] = new int[i+1];
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d",element1(triangle, i, j));
            };
            System.out.println();
        }
    }

    public static void print2(int n) {
        int[] row = new int[n];
        for (int i = 0; i < n; i++) {
            printSpace(n,i);
            creatRow(row,i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d",row[j]);
            };
            System.out.println();
        }
    }


    public static void creatRow(int[] row, int i) {
        if (i == 0) {
            row[0] = 1;
            return;
        }
        for (int j = i; j > 0 ; j--) {
            row[j] = row[j] + row[j-1];
        }
    }

    public static void main(String[] args) {
//        System.out.println(element(4, 3));
        print2(5);
    }
}
