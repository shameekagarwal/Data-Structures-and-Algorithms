# Maximum Subarray

- https://leetcode.com/problems/maximum-subarray/
- naive - O(n^2) all sub array's sums
- optimized - O(n) - kadane's algorithm
- my understanding - no point of discarding any sub array that has positive sum, no point including a subarray that has negative sum. boundary case to solve for to remember - all elements are negative
- extension - print the subarray
- solution - when starting the loop, if currentSum = 0, startOfCurrentSum = i; and when updating maxSum, update boundaries

```java
class Solution {
    public int maxSubArray(int[] nums) {

        long currentSum = 0;
        long maxSum = Long.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) currentSum = 0;
        }

        return (int) maxSum;
    }
}
```


