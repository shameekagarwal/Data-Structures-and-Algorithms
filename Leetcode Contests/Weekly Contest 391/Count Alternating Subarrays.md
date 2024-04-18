# Count Alternating Subarrays

- https://leetcode.com/problems/count-alternating-subarrays/
- construct all "segments" of alternating subarrays
- segment = 0s and 1s are alternating
- all subarrays of this segment are a part of the answer
- no of subarrays in a segment from start to end = x * (x + 1) / 2, where x = end - start + 1
- if `nums[i] == nums[i - 1]`, previous segment has ended and a new segment starts at i
- after the loop, an extra calculation for segment ending at n - 1

```java
class Solution {
    
    public long countAlternatingSubarrays(int[] nums) {
        
        int n = nums.length;
        int segmentStart = 0;
        long result = 0;
        
        for (int i = 1; i < n; i++) {
            
            if (nums[i] == nums[i - 1]) {
                int segmentEnd = i - 1;
                result += calculatePossibleSubArrays(segmentEnd - segmentStart + 1);
                segmentStart = i;
            }
        }
        
        result += calculatePossibleSubArrays(n - 1 - segmentStart + 1);
        
        return result;
    }
    
    long calculatePossibleSubArrays(int x) {
        return (x * (x + 1L)) / 2L;
    }
}
```
