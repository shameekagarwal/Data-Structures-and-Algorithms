# Maximize Consecutive Elements in an Array After Modification

- https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification
- first, we sort the array
- then, for any value x - 
  - `dp[x] = dp[x - 1] + 1`
  - `dp[x + 1] = dp[x] + 1`
- note - we see above that the second uses dp[x]. so, we should actually write the second case first, so that our current evaluation subparts are not affected by each other

```java
class Solution {

    public int maxSelectedElements(int[] nums) {

        int n = nums.length;

        Arrays.sort(nums);

        int mx = nums[n - 1];

        int[] dp = new int[mx + 2];

        for (int i = 0; i < n; i++) {
            dp[nums[i] + 1] = dp[nums[i]] + 1;
            dp[nums[i]] = dp[nums[i] - 1] + 1;
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[nums[i]]);
            result = Math.max(result, dp[nums[i] + 1]);
        }
        
        return result;
    }
}
```
