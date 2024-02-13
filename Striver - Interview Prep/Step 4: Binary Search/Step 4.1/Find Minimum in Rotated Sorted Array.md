# Find Minimum in Rotated Sorted Array

- https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
- brute - O(n)
- optimal - binary search
- if subarray is sorted
  - min in subarray is `nums[low]`, so consider it for result
  - set low to high + 1 to break out of loop
- if left part of subarray is sorted - 
  - min in left part of subarray is `nums[low]`, so consider it for result
  - set low to mid + 1
- if right part of subarray is sorted - 
  - min in right part of subarray is `nums[mid]`, so consider it for result
  - set high to mid - 1

```java
class Solution {

    public int findMin(int[] nums) {
        
        int low = 0;
        int high = nums.length - 1;
        int result = Integer.MAX_VALUE;

        while (low <= high) {

            int mid = low + ((high - low) / 2);

            // subarray is sorted
            if (nums[low] <= nums[high]) {
                result = Math.min(result, nums[low]);
                low = high + 1;
            }

            else {

                // left part of subarray is sorted
                if (nums[low] <= nums[mid]) {
                    result = Math.min(result, nums[low]);
                    low = mid + 1;
                }

                // right part of subarray is sorted
                else {
                    result = Math.min(result, nums[mid]);
                    high = mid - 1;
                }
            }
        }

        return result;
    }
}
```
