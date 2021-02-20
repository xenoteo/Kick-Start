package xenoteo.com.github.year2020.A.allocation;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCasesNumber = in.nextInt();
        for (int i = 0; i < testCasesNumber; i++){
            int size = in.nextInt();
            int budget = in.nextInt();
            int[] costs = new int[size];
            for (int house = 0; house < size; house++){
                costs[house] = in.nextInt();
            }
            System.out.printf("Case #%d: %d\n", i + 1, maximumHousesNumber(budget, costs));
        }
    }

    /*
     * sorting houses' costs and taking as many first (cheapest) houses as possible haven provided budget
     */
    public static int maximumHousesNumber(int budget, int[] costs){
        Arrays.sort(costs);
        long totalSum = 0;
        int count = 0;
        while (totalSum < budget && count < costs.length){
            if (costs[count] + totalSum <= budget){
                totalSum += costs[count];
                count++;

            }
            else {
                totalSum += costs[count];
            }
        }
        return count;
    }
}
