# Maximum Strength of K Disjoint Subarrays

- https://leetcode.com/problems/maximum-strength-of-k-disjoint-subarrays/
- TODO

## Initial Solution

- 779 / 802 pass, after that tle
- basically, generate all arrays starting at i up to end - only if ks are left
- then, take a non pick case
- complexity - O(k * n * n) (last n is for the loop i am running to generate sub arrays)

```java
class Solution {

    public long maximumStrength(int[] nums, int k) {
        boolean[][] seen = new boolean[nums.length][k + 1];
        long[][] memo = new long[nums.length][k + 1];
        return recurse(nums, 0, k, seen, memo);
    }
    
    long recurse(int[] nums, int start, int k, boolean[][] seen, long[][] memo) {
        
        if (start == nums.length) {
            return k == 0 ? 0 : Long.MIN_VALUE;
        }
        
        if (seen[start][k]) {
            return memo[start][k];
        }
        
        long currentSubArraySum = 0;
        long result = Long.MIN_VALUE;
        
        if (k > 0) {
            for (int i = start; i < nums.length; i++) {
                currentSubArraySum += nums[i];
                long remaining = recurse(nums, i + 1, k - 1, seen, memo);
                if (remaining != Long.MIN_VALUE) {
                    result = Math.max(result, remaining + (long) (Math.pow(-1, k + 1) * currentSubArraySum * k));
                }
            }
        }
        
        result = Math.max(result, recurse(nums, start + 1, k, seen, memo));
        
        seen[start][k] = true;
        memo[start][k] = result;
        
        return result;
    }
}
```
