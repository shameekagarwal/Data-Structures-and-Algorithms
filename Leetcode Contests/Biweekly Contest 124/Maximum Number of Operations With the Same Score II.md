# Maximum Number of Operations With the Same Score II

- https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-ii
- from (start, end), we can go to - 
  - start+1, end-1
  - start, end-2
  - start+2, end
- there are three possible starts
- however - once we start using one of those, the remaining pairs picked should evaluate to the same sum

```java
class Solution {

    public int maxOperations(int[] nums) {
        
        int n = nums.length;
        
        int a = initialCall(1, n - 2, nums, nums[0] + nums[n - 1], n);
        int b = initialCall(2, n - 1, nums, nums[0] + nums[1], n);
        int c = initialCall(0, n - 3, nums, nums[n - 1] + nums[n - 2], n);
        
        return Math.max(a, Math.max(b, c));
    }
    
    private int initialCall(int start, int end, int[] nums, int toObtain, int n) {
        
        int[][] dp = new int[n][n];
        boolean[][] seen = new boolean[n][n];
        
        return recurse(start, end, nums, toObtain, dp, seen) + 1;
    }

    private int recurse(int start, int end, int[] nums, int toObtain, int[][] dp, boolean[][] seen) {
        
        if (start >= end) return 0;
        
        if (seen[start][end]) {
            return dp[start][end];
        }
        
        int result = 0;
        
        if (nums[start] + nums[end] == toObtain) {
            result = Math.max(result, recurse(start + 1, end - 1, nums, toObtain, dp, seen) + 1);
        }
        
        if (nums[start] + nums[start + 1] == toObtain) {
            result = Math.max(result, recurse(start + 2, end, nums, toObtain, dp, seen) + 1);
        }
        
        if (nums[end] + nums[end - 1] == toObtain) {
            result = Math.max(result, recurse(start, end - 2, nums, toObtain, dp, seen) + 1);
        }
        
        seen[start][end] = true;
        dp[start][end] = result;
        
        return result;
    }
}
```
