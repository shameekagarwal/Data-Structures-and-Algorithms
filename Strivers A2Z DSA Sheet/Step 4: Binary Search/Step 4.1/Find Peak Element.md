# Find Peak Element

- array can have multiple peaks, we have to return any one of them
- https://leetcode.com/problems/find-peak-element/
- if mid is a peak, return
- if mid is on an increasing slope, the right half would have some peak
- if mid is on a decreasing slope, the left half would have some peak
- in both cases above, when we discard a half, we know that the other half will at least have the mid's peak, but we might end up finding some other peak as well, we do not care sine we are able to trim down our search space
- if mid is on a trench, discard either left or right half, both will have some peak

```java
class Solution {
    public int findPeakElement(int[] nums) {
        
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = low + ((high - low) / 2);
            
            boolean greaterThanLeft = mid == 0 || nums[mid] > nums[mid - 1];
            boolean greaterThanRight = mid == nums.length - 1 || nums[mid] > nums[mid + 1];

            if (greaterThanLeft && greaterThanRight) {
                return mid;
            } else if (greaterThanLeft && !greaterThanRight) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
```
