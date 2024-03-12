# Find First and Last Position of Element in Sorted Array

- https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
- i was trying to think if it can be done in just log2n - we cannot
- just break into three conditions - makes solving the problem much easier
- time complexity - O(2log2n)
- can do a small optimization - if first is -1, return -1, -1 immediately to save up on a single logn of complexity

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstOccurrence = findFirstOccurrence(nums, target);
        int lastOccurrence = findLastOccurrence(nums, target);
        return new int[]{firstOccurrence, lastOccurrence};
    }

    private int findFirstOccurrence(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        int firstOccurrence = -1;

        while (low <= high) {

            int mid = getMid(low, high);

            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                firstOccurrence = mid;
                high = mid - 1;
            }
        }

        return firstOccurrence;
    }

    private int findLastOccurrence(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        int lastOccurrence = -1;

        while (low <= high) {
            
            int mid = getMid(low, high);

            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                lastOccurrence = mid;
                low = mid + 1;
            }
        }

        return lastOccurrence;
    }

    private int getMid(int low, int high) {
        return low + ((high - low) / 2);
    }
}
```  

