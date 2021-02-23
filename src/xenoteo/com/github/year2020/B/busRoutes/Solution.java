package xenoteo.com.github.year2020.B.busRoutes;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCasesNumber = in.nextInt();
        for (int t = 0; t < testCasesNumber; t++){
            int n = in.nextInt();
            long d = in.nextLong();
            long[] x = new long[n];
            for (int i = 0; i < n; i++){
                x[i] = in.nextLong();
            }
            System.out.printf("Case #%d: %d\n", t + 1, latestStartDay(x, d));
        }
    }

    public static long latestStartDay(long[] x, long finishDay) {
        long startDay = finishDay;
        // for each bus (starting from the end)
        // finding the latest possible departure time,
        // so that that time is less or equal to the departure time of the immediately next bus's departure time
        // or less or equal to the finish day
        for (int i = x.length - 1; i >= 0; i--) {
            startDay = (startDay / x[i]) * x[i];
        }
        return startDay;
    }
}
