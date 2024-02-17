# Max Consecutive Ones III

- https://leetcode.com/problems/max-consecutive-ones-iii/
- increment count of 1s encountered
- while number of 1s encountered at r is more than k, keep incrementing l
- i think we couldn't do the jump discussed [here](./Longest%20Substring%20Without%20Repeating%20Characters.md) because we need to remember all occurrences of 0 - e.g. if k is 2, on encountering the "5th 0", we need to jump to "3rd 0" + 1, on encountering the "6th 0", we need to jump to the "4th 0" + 1 and so on

```java
class Solution {

    public int longestOnes(int[] nums, int k) {

        int currentZerosConsumed = 0;
        int l = 0;
        int result = 0;

        for (int r = 0; r < nums.length; r++) {

            if (nums[r] == 0) {
                currentZerosConsumed += 1;
            }

            while (currentZerosConsumed == k + 1) {
                if (nums[l] == 0) {
                    currentZerosConsumed -= 1;
                }
                l += 1;
            }

            result = Math.max(r - l + 1, result);
        }

        return result;
    }
}
```
