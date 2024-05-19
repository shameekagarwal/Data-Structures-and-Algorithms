# Remove Duplicates from Sorted Array II

- https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
- start from ptr = 2 and i = 2 - first two elements should not need changing
- if nums has same value at i as at ptr - 1 and at ptr - 2, just increment i
- else, put `nums[i]` at `nums[ptr]` and increment both ptr and i

```java
class Solution {

    public int removeDuplicates(int[] nums) {

        if (nums.length < 3) return nums.length;

        int ptr = 2;

        for (int i = 2; i < nums.length; i++) {

            if (nums[i] != nums[ptr - 1] || nums[i] != nums[ptr - 2]) {
                nums[ptr] = nums[i];
                ptr += 1;
            }
        }

        return ptr;
    }
}
```
