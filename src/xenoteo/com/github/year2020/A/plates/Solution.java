package xenoteo.com.github.year2020.A.plates;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCasesNumber = in.nextInt();
        for (int i = 0; i < testCasesNumber; i++){
            int n = in.nextInt();
            int k = in.nextInt();
            int p = in.nextInt();
            int[][] stacks = new int[n][k];
            for (int stack = 0; stack < n; stack++){
                for (int plate = 0; plate < k; plate++){
                    stacks[stack][plate] = in.nextInt();
                }
            }
            System.out.printf("Case #%d: %d\n", i + 1, maxBeauty(stacks, n, k, p));
        }
    }

    public static int maxBeauty(int[][] stacks, int n, int k, int p){
        // sum[i][x] denotes the sum of first x plates from stack i
        int[][] sum = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++){
            int stackSum = 0;
            for (int x = 1; x <= k; x++){
                stackSum += stacks[i - 1][x - 1];
                sum[i][x] = stackSum;
            }
        }
        // dp[i][j] denotes the maximum sum that can be obtained using the first
        // i stacks when we need to pick j plates in total
        int[][] dp = new int[n + 1][p + 1];
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= p; j++){
                dp[i][j] = 0;
                for (int x = 0; x <= Math.min(j, k); x++){
                    dp[i][j] = Math.max(dp[i][j], sum[i][x] + dp[i - 1][j - x]);
                }
            }
        }
        return dp[n][p];
    }
}
