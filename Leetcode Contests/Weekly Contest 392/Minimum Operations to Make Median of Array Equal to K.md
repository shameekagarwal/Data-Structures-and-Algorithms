# Minimum Operations to Make Median of Array Equal to K

- https://leetcode.com/problems/minimum-operations-to-make-median-of-array-equal-to-k/
- sort eh array
- set median index as k, add to operations
- now, make the array valid / median valid - 
  - whatever elements on left > whats at k, bring it to k, add to operations
  - whatever elements on right < whats at k, bring it to k, add to operations

```java
class Solution {

    public long minOperationsToMakeMedianK(int[] nums, int k) {

        Arrays.sort(nums);

        int n = nums.length;
        int medianIdx = n / 2;
        
        long result = Math.abs(nums[medianIdx] - k);
        nums[medianIdx] = k;
        
        for (int i = medianIdx - 1; i > -1; i--) {
            if (nums[i] > nums[medianIdx]) {
                result += (nums[i] - nums[medianIdx]);
                nums[i] = nums[medianIdx];
            } else {
                break;
            }
        }
        
        for (int i = medianIdx + 1; i < n; i++) {
            if (nums[i] < nums[medianIdx]) {
                result += (nums[medianIdx] - nums[i]);
                nums[i] = nums[medianIdx];
            } else {
                break;
            }
        }
 
        return result;
    }
}
```
