# Burst Balloons

- https://leetcode.com/problems/burst-balloons/
- we burst from end to beginning instead
- we also add 1s at ends
- now, for any range i,j
  - we can burst any balloon from i to j
  - everything from i to j has to be popped
  - i-1 and j+1 have already been popped - so they act as the left and right for the ballon we try to pop

![Burst Balloons](./Burst%20Balloons.png)

- note - this question did not split into two parts, but two parts would be solved independently - 
  ```
  1 a b c d e f 1

  // if we burst c, we solve for

  1 a b c
  c d e f 1
  ```
- time complexity - n^3 - two state changing, n loop is running

```java
class Solution {

    public int maxCoins(int[] nums) {
        
        int n = nums.length;
        
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;

        int[][] memo = new int[n + 2][n + 2];
        boolean[][] seen = new boolean[n + 2][n + 2];

        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }

        return recurse(1, n, balloons, memo, seen);
    }

    private int recurse(int start, int end, int[] balloons, int[][] memo, boolean[][] seen) {

        int result = 0;

        if (seen[start][end]) {
            return memo[start][end];
        }

        for (int burst = start; burst <= end; burst++) {

            int coinsFromLeft = recurse(start, burst - 1, balloons, memo, seen);
            int coinsObtained = balloons[burst] * balloons[start - 1] * balloons[end + 1];
            int coinsFromRight = recurse(burst + 1, end, balloons, memo, seen);

            result = Math.max(
                result,
                coinsFromLeft + coinsObtained + coinsFromRight
            );
        }

        seen[start][end] = true;
        memo[start][end] = result;

        return result;
    }
}
```

- tabulation method - 

```java
class Solution {

    public int maxCoins(int[] nums) {

        int n = nums.length;

        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        for (int rangeLen = 1; rangeLen <= n; rangeLen++) {

            for (int start = 1; start < n + 2 - rangeLen; start++) {

                int end = start + rangeLen - 1;

                for (int burst = start; burst <= end; burst++) {
                    // System.out.printf("%d to %d, %d to %d\n", start, burst, burst, end);
                    dp[start][end] = Math.max(
                        dp[start][end],
                        dp[start][burst - 1] + dp[burst + 1][end] + (balloons[start - 1] * balloons[burst] * balloons[end + 1])
                    );
                }

                // System.out.printf("%d, %d: %d\n", start, end, dp[start][end]);
                // System.out.println();
            }
        }

        return dp[1][n];
    }
}
```
