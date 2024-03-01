# Unbounded Knapsack

- https://www.codingninjas.com/studio/problems/unbounded-knapsack_1215029
- recursive - much more than O(2^n)

```java
public class Solution {

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        return unboundedKnapsack(0, n, w, profit, weight);
    }

    private static int unboundedKnapsack(int idx, int n, int w, int[] profit, int[] weight) {
        
        if (idx == n) return 0;

        int take = Integer.MIN_VALUE;
        if (weight[idx] <= w) {
            take = unboundedKnapsack(idx, n, w - weight[idx], profit, weight) + profit[idx];
        }

        int notTake = unboundedKnapsack(idx + 1, n, w, profit, weight);

        return Math.max(take, notTake);
    }
}
```

- memoized - O(n*w)

```java
public class Solution {

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        int[][] memo = new int[n][w + 1];
        boolean[][] seen = new boolean[n][w + 1];
        return unboundedKnapsack(0, n, w, profit, weight, memo, seen);
    }

    private static int unboundedKnapsack(int idx, int n, int w, int[] profit, int[] weight, int[][] memo, boolean[][] seen) {
        
        if (idx == n) return 0;

        if (seen[idx][w]) return memo[idx][w];

        memo[idx][w] = unboundedKnapsack(idx + 1, n, w, profit, weight, memo, seen);

        if (weight[idx] <= w) {
            memo[idx][w] = Math.max(memo[idx][w], unboundedKnapsack(idx, n, w - weight[idx], profit, weight, memo, seen) + profit[idx]);
        }

        seen[idx][w] = true;

        return memo[idx][w];
    }
}
```

- tabulation + space optimized
- note how previous and current is not required - one row was enough
- this was possible because when calculating `current[j]`, only `previous[j]` was used, `previous[j - something]` was not needed

```java
public class Solution {

    public static int unboundedKnapsack(int n, int w, int[] profits, int[] weights) {

        int[] dp = new int[w + 1];

        for (int i = 0; i < n; i++) {

            for (int j = weights[i]; j <= w; j++) {
                dp[j] = Math.max(dp[j - weights[i]] + profits[i], dp[j]);
            }
        }

        return dp[w];
    }
}
```
