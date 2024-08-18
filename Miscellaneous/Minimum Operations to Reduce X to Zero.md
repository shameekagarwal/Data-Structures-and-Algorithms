# Minimum Operations to Reduce X to Zero

- https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/

```java
class Solution {

    public int minOperations(int[] nums, int x) {

        long sum = getSum(nums);

        int left = 0;
        long currentSum = 0;
        long toObtain = sum - x;

        int maxSubarrayLength = -1;

        for (int right = 0; right < nums.length; right++) {

            currentSum += nums[right];

            while (left <= right && currentSum > toObtain) {
                currentSum -= nums[left];
                left += 1;
            }

            if (currentSum == toObtain) {
                maxSubarrayLength = Math.max(maxSubarrayLength, right - left + 1);
            }
        }

        return maxSubarrayLength == -1 ?
            -1 :
            nums.length - maxSubarrayLength;
    }

    private long getSum(int[] nums) {

        long sum = 0;

        for (int num : nums) {
            sum += num;
        }

        return sum;
    }
}
```
