# 3Sum Closest

- https://leetcode.com/problems/3sum-closest/
- initialize result with infinity
- try looking for current sums that are lesser than infinity - 

```java
class Solution {

    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {

            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {

                int currentSum = nums[i] + nums[start] + nums[end];

                if (Math.abs(result - target) > Math.abs(currentSum - target)) {
                    result = currentSum;
                }

                if (currentSum > target) {
                    end -= 1;
                } else if (currentSum < target) {
                    start += 1;
                } else {
                    return currentSum;
                }
            }
        }

        return result;
    }
}
```
