# 0 1 Knapsack

- https://www.codingninjas.com/studio/problems/0-1-knapsack_920542
- why greedy will not work - 
  ```
  6
  2   3   5
  30  40  50
  ```
- by greedy, we pick up 5, get value 50, and cannot pick anything else
- another greedy approach - like fractional knapsack - take ones with highest value / weight - 
  ```
  11
    5    6     3
  500  600   400
  ```
- weight 3 has the highest value / weight - but we get better results by stealing 5 and 6
- but we know we can pick 2 and 3 and get 70
- so, recursive solution - 2^n

```java
import java.util.* ;
import java.io.*; 

public class Solution {

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        return knapsack(0, weight, value, n, maxWeight);
    }

    private static int knapsack(int idx, int[] weight, int[] value, int n, int maxWeight) {
        if (idx == n) return 0;
        int pick = 0;
        if (weight[idx] <= maxWeight) {
            pick = knapsack(idx + 1, weight, value, n, maxWeight - weight[idx]) + value[idx];
        }
        int nonPick = knapsack(idx + 1, weight, value, n, maxWeight);
        return Math.max(pick, nonPick);
    }
}
```

- memoized solution to prevent overlapping subproblems

```java
import java.util.*;
import java.io.*;

public class Solution {

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] memo = new int[n][maxWeight + 1];
        boolean[][] seen = new boolean[n][maxWeight + 1];
        return knapsack(0, weight, value, n, maxWeight, memo, seen);
    }

    private static int knapsack(int idx, int[] weight, int[] value, int n, int maxWeight, int[][] memo, boolean[][] seen) {

        if (idx == n) return 0;

        if (seen[idx][maxWeight]) return memo[idx][maxWeight];

        int pick = 0;
        if (weight[idx] <= maxWeight) {
            pick = knapsack(idx + 1, weight, value, n, maxWeight - weight[idx], memo, seen) + value[idx];
        }
        int nonPick = knapsack(idx + 1, weight, value, n, maxWeight, memo, seen);

        memo[idx][maxWeight] = Math.max(pick, nonPick);
        seen[idx][maxWeight] = true;

        return memo[idx][maxWeight];
    }
}
```

- tabular + space optimized 

```java
import java.util.*;
import java.io.*; 

public class Solution {

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        int[] previous = new int[maxWeight + 1];

        for (int i = 0; i < n; i++) {

            int[] current = new int[maxWeight + 1];

            for (int j = 0; j <= maxWeight; j++) {
                current[j] = previous[j];
                if (weight[i] <= j) {
                    current[j] = Math.max(current[j], previous[j - weight[i]] + value[i]);
                }
            }

            previous = current;
        }

        return previous[maxWeight];
    }
}
```
