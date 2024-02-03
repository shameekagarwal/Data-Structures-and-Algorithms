# Single element in a Sorted Array

- all the elements will be appearing twice, except for one element, which would appear only once
- brute - O(n) - but do remember boundary cases like what if the single element is at the end etc
- optimal - binary search
- elements in doubles would be in (even, odd) form to the left of the single element
- elements in doubles would be in (odd, even) form to the right of the single element
- note - i did not do out of bounds check for `mid - 1 >= 0` and `mid + 1 < nums.length` inside the if else of `mid % 2` because if mid was 0 or nums.length - 1, low and mid and high would all point to the same element and would have been returned in the single element check for mid just before

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        
        int low = 0;
        int high = nums.length - 1;
        
        while (low <= high) {
            
            int mid = low + ((high - low) / 2);

            if ((mid == 0 || nums[mid] != nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] != nums[mid + 1])) {
                return nums[mid];
            }
            
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}
```
