# Search Insert Position

- https://leetcode.com/problems/search-insert-position/
- just implement lower bound - question says array is distinct elements, and return position of element if found
- otherwise if it was just about inserting the element at some position, i think upper bound works as well

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        
        int low = 0;
        int high = nums.length - 1;
        int indexToInsertTargetAt = nums.length;

        while (low <= high) {
            int mid = getMid(low, high);
            if (nums[mid] >= target) {
                indexToInsertTargetAt = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return indexToInsertTargetAt;
    }

    private int getMid(int low, int high) {
        return low + ((high - low) / 2);
    }
}
```
