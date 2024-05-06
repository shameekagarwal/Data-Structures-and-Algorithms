# Split array - Largest Sum

## Solution 1

- https://leetcode.com/problems/split-array-largest-sum/
- same as [Book Allocation Problem](./Book%20Allocation%20Problem.md)

```java
class Solution {

    private int[] nums;

    private int numOfSubArrays;

    public int splitArray(int[] nums, int k) {
        this.nums = nums;
        this.numOfSubArrays = k;
        return run();
    }

    private int run() {
        
        int maxPossibleSum = getMaxPossibleSum();
        int minPossibleSum = getMinPossibleSum();
        int result = maxPossibleSum;
        
        while (minPossibleSum <= maxPossibleSum) {
            
            int possibleResult = minPossibleSum + ((maxPossibleSum - minPossibleSum) / 2);

            if (isPossible(possibleResult)) {
                result = possibleResult;
                maxPossibleSum = possibleResult - 1;
            } else {
                minPossibleSum = possibleResult + 1;
            }
        }
        
        return result;
    }

    private boolean isPossible(int possibleResult) {
        int currentNumOfSubArrays = 0;
        int currentSubArraySum = 0;
        for (int num : nums) {
            if (currentSubArraySum + num > possibleResult) {
                currentNumOfSubArrays += 1;
                currentSubArraySum = 0;
            }
            currentSubArraySum += num;
        }
        currentNumOfSubArrays += 1;
        // System.out.printf("no of subarrays for %d = %d\n", possibleResult, currentNumOfSubArrays);
        return currentNumOfSubArrays <= numOfSubArrays;
    }

    private int getMaxPossibleSum() {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max += nums[i];
        }
        return max;
    }

    private int getMinPossibleSum() {
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.max(min, nums[i]);
        }
        return min;
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
