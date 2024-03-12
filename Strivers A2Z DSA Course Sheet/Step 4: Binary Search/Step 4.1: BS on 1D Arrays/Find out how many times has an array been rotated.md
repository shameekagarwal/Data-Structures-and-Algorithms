# Find out how many times has an array been rotated

- same as [Find Minimum in Rotated Sorted Array](./Find%20Minimum%20in%20Rotated%20Sorted%20Array.md)
- index of minimum element is the answer

```java
public class Solution {
    public static int findKRotation(int []nums){
        
        int low = 0;
        int high = nums.length - 1;
        int minEleIdx = -1;

        while (low <= high) {
            
            int mid = low + ((high - low) / 2);

            // subarray is sorted
            if (nums[low] <= nums[high]) {
                if (minEleIdx == -1 || nums[minEleIdx] > nums[low]) {
                    minEleIdx = low;
                }
                low = high + 1;
            } else {

                // left part of subarray is sorted
                if (nums[low] <= nums[mid]) {
                    if (minEleIdx == -1 || nums[minEleIdx] > nums[low]) {
                        minEleIdx = low;
                    }
                    low = mid + 1;
                }
                
                // right part of subarray is sorted
                else {
                    if (minEleIdx == -1 || nums[minEleIdx] > nums[mid]) {
                        minEleIdx = mid;
                    }
                    high = mid - 1;
                }
            }
        }

        return minEleIdx;
    }
}
```
