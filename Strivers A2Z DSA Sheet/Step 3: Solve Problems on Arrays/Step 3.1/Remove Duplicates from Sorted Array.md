# Remove Duplicates from Sorted Array

- https://leetcode.com/problems/remove-duplicates-from-sorted-array/

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int sortedArrayPointer = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[sortedArrayPointer] != nums[i]) {
                sortedArrayPointer += 1;
                nums[sortedArrayPointer] = nums[i];
            }
        }
        return sortedArrayPointer + 1;
    }
}
```
