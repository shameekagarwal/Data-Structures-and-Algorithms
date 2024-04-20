# Longest Strictly Increasing or Strictly Decreasing Subarray

- https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
- for every i, calculate j such that i-j is monotonic
- make i as j + 1

```java
class Solution {

    public int longestMonotonicSubarray(int[] nums) {
        
        return Math.max(
            longestIncreasingSubarray(nums),
            longestDecreasingSubarray(nums)
        );
    }
    
    private int longestIncreasingSubarray(int[] nums) {
        
        int n = nums.length;
        int result = 0;
        
        for (int i = 0; i < n;) {
            
            int j = i;
            while (j < n - 1 && nums[j] < nums[j + 1]) {
                j += 1;
            }
            
            result = Math.max(j - i + 1, result);
            
            i = j + 1;
        }
        
        return result;
    }
    
    private int longestDecreasingSubarray(int[] nums) {
        
        int n = nums.length;
        int result = 0;
        
        for (int i = 0; i < n;) {
            
            int j = i;
            while (j < n - 1 && nums[j] > nums[j + 1]) {
                j += 1;
            }
            
            result = Math.max(j - i + 1, result);
            
            i = j + 1;
        }
        
        return result;
    }
}
```
