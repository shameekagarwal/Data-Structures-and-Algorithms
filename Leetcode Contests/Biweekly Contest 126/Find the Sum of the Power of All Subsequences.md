# Find the Sum of the Power of All Subsequences

- https://leetcode.com/problems/find-the-sum-of-the-power-of-all-subsequences/
- an element can be picked or not picked - typical question
- this will give us all subsequences that can be used to form the target
- but, we need to track how many elements were picked as well
- let us say we picked x elements - it can be present in 2^(n - x) subsequences - because apart from these x elements, remaining elements can be picked or not picked
- so three paces of state - idx, k, elements picked
- simply memoize the recursive approach for result

```java
class Solution {

    private static final int MOD = 1000000007;

    public int sumOfPower(int[] nums, int k) {
        int n = nums.length;
        int[][][] dp = new int[k + 1][n + 1][n + 1];
        boolean[][][] seen = new boolean[k + 1][n + 1][n + 1];
        return recurse(nums, dp, seen, k, 0, 0);
    }

    private int recurse(int[] nums, int[][][] dp, boolean[][][] seen, int k, int idx, int totalElementsPicked) {

        if (idx == nums.length) {
            return 0;
        }

        if (seen[k][idx][totalElementsPicked]) return dp[k][idx][totalElementsPicked];

        long result = 0;

        if (k == nums[idx]) {
            long possibleSubsequences = modularExponentiation(2, nums.length - totalElementsPicked - 1);
            result = (result + possibleSubsequences) % MOD;
        } else if (k > nums[idx]) {
            result = (result + recurse(nums, dp, seen, k - nums[idx], idx + 1, totalElementsPicked + 1)) % MOD;
        }

        result = (result + recurse(nums, dp, seen, k, idx + 1, totalElementsPicked)) % MOD;

        dp[k][idx][totalElementsPicked] = (int) result;
        seen[k][idx][totalElementsPicked] = true;

        return dp[k][idx][totalElementsPicked];
    }

    private long modularExponentiation(long n, int m) {

        long result = 1;

        while (m > 0) {
            if ((m & 1) == 1) {
                result = (result * n) % MOD;
                m -= 1;
            } else {
                m /= 2;
                n = (n * n) % MOD;
            }
        }

        return result;
    }
}
```
