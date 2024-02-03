# Binary Search to find X

- brute force - O(n)
- split into halves - just like dictionary
- mid = (low + high) / 2
- we do high = mid + 1 and low = mid - 1, (i.e. not high = mid / low = mid)
- time - we half each time, so O(log2(n))
- boundary case to remember - what if low + high exceeds integer range?
- so, we cannot / should not add low and high directly - we can do mid = (low + (high - low) / 2)
- https://leetcode.com/problems/binary-search/
- condition of using binary search - array must be sorted

## Iterative Implementation

```java
class Solution {
    public int search(int[] nums, int target) {
        
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = getMid(low, high);
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    private int getMid(int low, int high) {
        return low + ((high - low) / 2);
    }
}
```

## Recursive Implementation

```java
class Solution {
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int left, int right) {
        if (right < left) return -1;
        int mid = left + ((right - left) / 2);
        if (nums[mid] > target) {
            return search(nums, target, left, mid - 1);
        } else if (nums[mid] < target) {
            return search(nums, target, mid + 1, right);
        } else {
            return mid;
        }
    }
}
```
