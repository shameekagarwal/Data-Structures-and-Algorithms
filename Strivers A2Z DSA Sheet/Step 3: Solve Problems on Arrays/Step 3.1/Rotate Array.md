# Rotate Array

- https://leetcode.com/problems/rotate-array/
- [1, 2, 3, 4, 5, 6, 7], k = 3
- convert k to k % length
- step 1 - temp = [5, 6, 7]
- step 2 - nums = [_, _, _, 1, 2, 3, 4] - note - this needs to be done by runing the loop from back, i was stuck on this
- step 3 - nums = [5, 6, 7, 1, 2, 3, 4]

```java
class Solution {
    public void rotate(int[] nums, int k) {
        
        k = k % nums.length;

        int temp[] = new int[k];
        
        for (int i = nums.length - k; i < nums.length; i++) {
            temp[i - (nums.length - k)] = nums[i];
        }

        for (int i = nums.length - k - 1; i > -1; i--) {
            nums[i + k] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
}
```
