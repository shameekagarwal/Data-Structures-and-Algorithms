# Partition Array for Maximum Sum

- https://leetcode.com/problems/partition-array-for-maximum-sum/
- recursive approach just takes index i
- try considering arrays i, i + 1, i + 2, ...i + k
- this way, we explore all possibilities

```java
class Solution {

    public int maxSumAfterPartitioning(int[] arr, int k) {

        int n = arr.length;
        int[] memo = new int[n];
        boolean[] seen = new boolean[n];

        return recurse(0, n, k, arr, memo, seen);
    }

    private int recurse(int idx, int n, int k, int[] arr, int[] memo, boolean[] seen) {

        if (idx == n) {
            return 0;
        }

        if (seen[idx]) {
            return memo[idx];
        }

        int result = 0;

        int maxTillNow = Integer.MIN_VALUE;

        for (int i = idx; i < Math.min(idx + k, n); i++) {
            maxTillNow = Math.max(maxTillNow, arr[i]);
            result = Math.max(
                result,
                ((i - idx + 1) * maxTillNow) + recurse(i + 1, n, k, arr, memo, seen)
            );
        }
        
        seen[idx] = true;
        memo[idx] = result;

        return result;
    }
}
```

- tabulation - 

```java
class Solution {

    public int maxSumAfterPartitioning(int[] arr, int k) {

        int n = arr.length;
        int[] dp = new int[n + 1];

        for (int j = n - 1; j > -1; j--) {

            int maxTillNow = Integer.MIN_VALUE;

            for (int i = j; i < Math.min(j + k, n); i++) {
                maxTillNow = Math.max(maxTillNow, arr[i]);
                dp[j] = Math.max(
                    dp[j],
                    ((i - j + 1) * maxTillNow) + dp[i + 1]
                );
            }
        }

        return dp[0];
    }
}
```
