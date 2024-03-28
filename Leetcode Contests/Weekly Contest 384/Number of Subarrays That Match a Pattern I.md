# Number of Subarrays That Match a Pattern I

- https://leetcode.com/problems/number-of-subarrays-that-match-a-pattern-i/
- "construct" sub arrays of size k + 1 from the original array
- then, see if satisfies the pattern

```java
class Solution {

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        
        int count = 0;
        
        int n = nums.length;
        int m = pattern.length;
        int result = 0;
        
        for (int i = 0; i + m < n; i++) {
            
            boolean valid = true;

            for (int j = 0; j < m; j++) {
                
                valid &= (
                    (pattern[j] == 1 && nums[i + j + 1] > nums[i + j]) || 
                    (pattern[j] == 0 && nums[i + j + 1] == nums[i + j]) || 
                    (pattern[j] == -1 && nums[i + j + 1] < nums[i + j])
                );
            }
            
            if (valid) result += 1;
        }
        
        return result;
    }
}
```
