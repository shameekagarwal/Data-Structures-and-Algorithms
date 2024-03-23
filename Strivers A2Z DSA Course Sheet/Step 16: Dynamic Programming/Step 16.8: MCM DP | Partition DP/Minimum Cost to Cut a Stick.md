# Minimum Cost to Cut a Stick

- https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
- sorting the array helps us solve for left and right parts independently
- we append 0 and n to start and end to know the length of the part we are currently at at any recursive call
- this gives us the cost when cutting that part again, and we can cut at all parts from `cuts[start + 1]` to `cuts[end - 1]`
- base case - start == end + 1 - return the length as is

```java
class Solution {

    public int minCost(int n, int[] cuts) {

        int m = cuts.length;

        int[] appendedCuts = new int[m + 2];
        for (int i = 0; i < m; i++) {
            appendedCuts[i + 1] = cuts[i];
        }
        appendedCuts[m + 1] = n;

        Arrays.sort(appendedCuts);

        int[]

        int[][] memo = new int[m + 2][m + 2];
        boolean[][] seen = new boolean[m + 2][m + 2];

        return recurse(0, m + 1, appendedCuts, memo, seen);
    }

    private int recurse(int start, int end, int[] cuts, int[][] memo, boolean[][] seen) {

        if (seen[start][end]) {
            return memo[start][end];
        }

        if (start == end - 1) {
            return cuts[end - 1] - cuts[start];
        }

        int result = Integer.MAX_VALUE;

        for (int partition = start + 1; partition < end; partition++) {
            result = Math.min(result, recurse(start, partition, cuts, memo, seen) + recurse(partition, end, cuts, memo, seen));
        }

        result += (cuts[end] - cuts[start]);

        seen[start][end] = true;
        memo[start][end] = result;

        return result;
    }
}
```

- tabular - 

```java
class Solution {

    public int minCost(int n, int[] cuts) {

        int m = cuts.length;

        int[] appendedCuts = new int[m + 2];
        for (int i = 0; i < m; i++) {
            appendedCuts[i + 1] = cuts[i];
        }
        appendedCuts[m + 1] = n;

        Arrays.sort(appendedCuts);

        m += 2;

        int[][] dp = new int[m][m];

        // for (int start = 0; start < m - 1; start++) {
        //     dp[start][start + 1] = appendedCuts[start + 1] - appendedCuts[start];
        //     System.out.printf("%d to %d: %d\n", start, start + 1, dp[start][start + 1]);
        // }
        // System.out.println();

        for (int gap = 2; gap < m; gap++) {

            for (int start = 0; start < m - gap; start++) {

                int end = start + gap;
                dp[start][end] = Integer.MAX_VALUE;

                for (int partition = start + 1; partition < end; partition++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][partition] + dp[partition][end] + appendedCuts[end] - appendedCuts[start]);
                }
                // System.out.printf("%d to %d: %d\n", start, end, dp[start][end]);
            }
            // System.out.println();
        }

        return dp[0][m - 1];
    }
}
```
