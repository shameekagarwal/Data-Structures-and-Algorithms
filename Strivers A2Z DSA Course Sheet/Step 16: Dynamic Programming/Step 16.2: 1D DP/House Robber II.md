# House Robber II

- https://leetcode.com/problems/house-robber-ii/
- we cannot pick both first and last element
- so, answer is basically either answer(0...n-1) or answer(1...n)
- dry run to prove above

```java
class Solution {

    public int rob(int[] nums) {
 
        if (nums.length == 1) return nums[0];
 
        int[] numsExcludingFirstElement = Arrays.copyOfRange(nums, 1, nums.length);
        int[] numsExcludingLastElement = Arrays.copyOfRange(nums, 0, nums.length - 1);

        return Math.max(robLinear(numsExcludingFirstElement), robLinear(numsExcludingLastElement));
    }

    private int robLinear(int[] nums) {
 
        if (nums.length == 1) return nums[0];

        int maxUptoBeforePrevious = nums[0];
        int maxUptoPrevious = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int earlierMaxUptoPrevious = maxUptoPrevious;
            maxUptoPrevious = Math.max(maxUptoPrevious, nums[i] + maxUptoBeforePrevious);
            maxUptoBeforePrevious = earlierMaxUptoPrevious;
        }

        return maxUptoPrevious;
    }
}
```
