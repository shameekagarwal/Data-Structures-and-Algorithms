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
- do not forget = in <=. even though question states unique, it is useful when our search range has a single element

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

## Solution 2

- came up with this different thought process
- our answer is the first element of the second slope (if array is not at all rotated, it is the first element)
- if arr(start) < arr(end) - means range is sorted. return arr(start)
- else, range is not sorted. and it includes "two slopes". our answer is the first element of the second slope
  - calculate mid
  - if mid is smaller than left and right - return mid. this was the smallest element
  - else, if arr(m) >= arr(s) - s-m is sorted. this has to be on the first slope. we can eliminate this
  - else, m-e is sorted. this has to be on the second slope. since we already know m is also not it, we can eliminate this

```java
class Solution {

    public int findMin(int[] nums) {

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {

            if (nums[start] <= nums[end]) {
                return nums[start];
            }

            int mid = (start + end) / 2;

            boolean lessThanLeft = mid == 0 ? true : nums[mid] < nums[mid - 1];
            boolean lessThanRight = mid == nums.length - 1 ? true : nums[mid] < nums[mid + 1];

            if (lessThanLeft && lessThanRight) {
                return nums[mid];
            }

            if (nums[mid] >= nums[start]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
```
