# Check if Array Is Sorted and Rotated

- https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
- make flag if rotation is found as true
- more such "deviations" if found, array is neither sorted, nor rotated
- last condition before true - handle cases like this - [5, 8, 9, 1, 2, 7] - might look sorted and rotated, but 7 is greater than 5, so it is not

```java
class Solution {
    public boolean check(int[] nums) {
        int firstElement = nums[0];
        boolean foundRotation = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (foundRotation) return false;
                foundRotation = true;
            }
        }
        if (foundRotation && nums[nums.length - 1] > firstElement) return false;
        return true;
    }
}
```
