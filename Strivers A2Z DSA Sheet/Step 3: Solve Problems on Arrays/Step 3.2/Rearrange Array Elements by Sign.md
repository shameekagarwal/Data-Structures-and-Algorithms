# Rearrange Array Elements by Sign

- https://leetcode.com/problems/rearrange-array-elements-by-sign/
- brute 
  - maintain two separate arrays - positive and negative
  - add to them in one iteration
  - populate the actual array in the second iteration
  - O(2 * n), extra space is needed

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] ans = new int[nums.length];
        int positiveIntegerCurrentIndex = 0;
        int negativeIntegerCurrentIndex = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans[positiveIntegerCurrentIndex] = nums[i];
                positiveIntegerCurrentIndex += 2;
            } else {
                ans[negativeIntegerCurrentIndex] = nums[i];
                negativeIntegerCurrentIndex += 2;
            }
        }
        return ans;
    }
}
```

## Variation

- no guarantee that positive and negatives are equal in number
- solution - fall back to the brute force! do not worry about finding more optimal 😛
