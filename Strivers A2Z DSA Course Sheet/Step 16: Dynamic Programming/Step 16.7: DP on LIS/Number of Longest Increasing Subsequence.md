# Number of Longest Increasing Subsequence

- https://leetcode.com/problems/number-of-longest-increasing-subsequence/
- [Longest Increasing Subsequence](./Longest%20Increasing%20Subsequence.md) with maintaining counts - 
  - if larger, reset count to `count[j]`
  - if equal, add `count[j]`
- for dry runs in interview, think of good examples like - 1 5 3 2 6 - for 6, we can use
  - 1 5 6
  - 1 3 6
  - 1 2 6

```java
class Solution {

    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        int[] count = new int[n];
        Arrays.fill(count, 1);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
        }

        int max = 0;
        int result = 0;

        for (int i = 0; i < n; i++) {

            if (dp[i] > max) {
                max = dp[i];
                result = count[i];
            } else if (dp[i] == max) {
                result += count[i];
            }
        }

        return result;
    }
}
```
