# Split array - Largest Sum

## Solution 1

- https://leetcode.com/problems/split-array-largest-sum/
- same as [Book Allocation Problem](./Book%20Allocation%20Problem.md)
- complexity - O(n*log k), where k = binary search space / sum of array

```java
class Solution {

    public int splitArray(int[] nums, int k) {

        int low = getMax(nums);
        int high = getSum(nums);
        int result = high;

        while (low <= high) {

            int mid = (low + high) / 2;

            int subarrays = getMinimumSubarrays(nums, mid);

            if (subarrays <= k) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private int getMinimumSubarrays(int[] nums, int sum) {

        int runningSum = 0;
        int subarrays = 1;

        for (int i = 0; i < nums.length; i++) {

            runningSum += nums[i];

            if (runningSum > sum) {
                runningSum = nums[i];
                subarrays += 1;
            }
        }

        return subarrays;
    }

    private int getMax(int[] nums) {

        int max = nums[0];

        for (int num : nums) {
            max = Math.max(max, num);
        }

        return max;
    }

    private int getSum(int[] nums) {

        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        return sum;
    }
}

```

## Solution 2

- solved using dp based on constraints
- each recursive call would be responsible for creating  the kth subarray from end
- note the use of -1 for impossible cases
  - when k reaches less than 1 i.e. we have made more than k subarrays
  - when idx reaches n, return -1 if we have made less than k subarrays
- changing parameters - idx and k, and there is a loop of n inside - so n * k * n = 5 * 10^7

```java
class Solution {

    public int splitArray(int[] nums, int k) {

        int[][] dp = new int[k + 1][nums.length];
        boolean[][] seen = new boolean[k + 1][nums.length];

        return recurse(nums, k, 0, dp, seen);
    }

    private int recurse(int[] nums, int k, int idx, int[][] dp, boolean[][] seen) {

        if (k < 0) {
            return -1;
        }
        
        if (idx == nums.length) {
            return k == 0 ? 0 : -1;
        }

        if (seen[k][idx]) {
            return dp[k][idx];
        }

        int runningSum = 0;
        int result = -1;

        for (int i = idx; i < nums.length; i++) {

            runningSum += nums[i];

            int maxSumInRemaining = recurse(nums, k - 1, i + 1, dp, seen);

            if (maxSumInRemaining == -1) continue;
            
            if (result == -1) {
                result = Math.max(maxSumInRemaining, runningSum);
            } else {
                result = Math.min(result, Math.max(maxSumInRemaining, runningSum));
            }
        }

        seen[k][idx] = true;
        dp[k][idx] = result;

        return result;
    }
}
```
