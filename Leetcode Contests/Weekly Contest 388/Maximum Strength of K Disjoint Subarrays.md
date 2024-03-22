# Maximum Strength of K Disjoint Subarrays

- https://leetcode.com/problems/maximum-strength-of-k-disjoint-subarrays/

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

### Right Solution

- odd is given - so of form `sum[1] * k - sum[2] * (k - 1) + .... + sum[k]` - will end in +
- 3 states in dp - 
  - use in last subarray if idx - 1 was picked - check if last picked is true, add contribution and compare
  - include in new subarray - check if remaining is > 0, add contribution and compare
  - do not do anything - make last picked as false, leave remaining as is
- after memoization, time complexity - O(n * k * 2)

```java
class Solution {
    public long maximumStrength(int[] nums, int k) {

        int n = nums.length;

        long[][][] memo = new long[n][k + 1][2];
        boolean[][][] seen = new boolean[n][k + 1][2];

        return recurse(0, k, false, nums, n, memo, seen);
    }

    private long recurse(int idx, int remainingSubarrays, boolean lastPicked, int[] nums, int n, long[][][] memo, boolean[][][] seen) {

        if (idx == n) {
            return remainingSubarrays == 0 ? 0 : Long.MIN_VALUE;
        }

        int lastPickedIdx = lastPicked ? 1 : 0;

        if (seen[idx][remainingSubarrays][lastPickedIdx]) {
            return memo[idx][remainingSubarrays][lastPickedIdx];
        }

        long result = Long.MIN_VALUE;

        if (lastPicked) {
            
            long possible = recurse(idx + 1, remainingSubarrays, true, nums, n, memo, seen);

            if (possible != Long.MIN_VALUE) {
                long contribution = powMinusOne(remainingSubarrays + 2) * (remainingSubarrays + 1) * 1L * nums[idx];
                result = Math.max(result, possible + contribution);
            }
        }

        if (remainingSubarrays > 0) {
        
            long possible = recurse(idx + 1, remainingSubarrays - 1, true, nums, n, memo, seen);
            
            if (possible != Long.MIN_VALUE) {
                long contribution = powMinusOne(remainingSubarrays + 1) * remainingSubarrays * 1L * nums[idx];
                result = Math.max(result, possible + contribution);
            }
        }

        long possible = recurse(idx + 1, remainingSubarrays, false, nums, n, memo, seen);
        result = Math.max(result, possible);

        seen[idx][remainingSubarrays][lastPickedIdx] = true;
        memo[idx][remainingSubarrays][lastPickedIdx] = result;

        return result;
    }

    private int powMinusOne(int x) {
        if (x % 2 == 0) return 1;
        return -1;
    }
}
```
