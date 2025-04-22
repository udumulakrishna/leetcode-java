import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        Scanner scanner = new Scanner(System.in);

        // Read the number of test cases
        int t = scanner.nextInt();

        // Process each test case
        while (t-- > 0) {
            // Read the size of the array
            int n = scanner.nextInt();

            // Create an array to hold the elements
            int[] arr = new int[n];

            // Read the array elements
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            // Find the maximum element
            int maximum = arr[0];
            for (int i = 1; i < n; i++) {
                if (arr[i] > maximum) {
                    maximum = arr[i];
                }
            }

            // Print the maximum element
            System.out.println(maximum);
        }

    }
}