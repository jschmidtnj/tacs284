package week2;

import java.io.*;
import java.util.*;

public class Candies {

    // Complete the candies function below.
    static long candies(int n, int[] ratings) {
        long[] candies = new long[n];
        for(int i = 0; i < n; i++) {
            candies[i] = 1;
        }
        // to the right
        for(int i = 1; i < n; i++) {
            if(ratings[i] > ratings[i-1]) {
                candies[i] += candies[i-1];
            }
        }
        // to the left
        for(int i = n-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1] && candies[i] < candies[i+1]+1) {
                candies[i] = candies[i+1]+1;
            }
        }
        long count = 0;
        for(int i = 0; i < n; i++) {
            count += candies[i];
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
