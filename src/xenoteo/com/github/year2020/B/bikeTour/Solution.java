package xenoteo.com.github.year2020.B.bikeTour;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCasesNumber = in.nextInt();
        for (int t = 0; t < testCasesNumber; t++){
            int n = in.nextInt();
            int[] checkpoints = new int[n];
            for (int i = 0; i < n; i++){
                checkpoints[i] = in.nextInt();
            }
            System.out.printf("Case #%d: %d\n", t + 1, peaksNumber(checkpoints));
        }
    }

    public static int peaksNumber(int[] checkpoints){
        return (int) IntStream.range(1, checkpoints.length - 1)
                .filter(i -> checkpoints[i] > checkpoints[i - 1] && checkpoints[i] > checkpoints[i + 1])
                .count();
    }
}
