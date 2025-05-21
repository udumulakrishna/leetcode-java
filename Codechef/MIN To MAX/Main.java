import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] a = new int[n];

            // Read the array elements
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Find the minimum element
            int minimum = a[0];
            for (int i = 1; i < n; i++) {
                if (a[i] < minimum) {
                    minimum = a[i];
                }
            }

            // Count the occurrences of the minimum element
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] == minimum) {
                    count++;
                }
            }

            // Print the result
            System.out.println(n - count);
        }
    }
}