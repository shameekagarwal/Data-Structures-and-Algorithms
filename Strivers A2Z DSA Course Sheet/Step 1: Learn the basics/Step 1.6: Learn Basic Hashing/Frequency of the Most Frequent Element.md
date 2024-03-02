# Frequency of the Most Frequent Element

- could not understand why this is a hashing problem
- https://leetcode.com/problems/frequency-of-the-most-frequent-element/
- first, sort the array
- we can only increase an element
- use two pointer approach - for every r, find the maximum possible frequency, where the sub array ends at r
- assume we want to take all elements from [l, r] to nums[r]
- all numbers that we want to make r should appear before r (we sorted the array, and we can only increase an element)
- let a = nums[r] * (r - l + 1) - number of elements in the sub array is r - l + 1, so sum of all elements if converted to nums[r] from l to r is this much
- let b = prefix[r + 1] - prefix[l] - sum of elements between l to r is this much
- we need a - b operations
- another idea used here for two pointer - let current window is b to d. if for converting to d, array can start at most at b (not before that), for converting to e, array can start at most at b as well (and not before that) because otherwise, d's window itself could have started from before
- time complexity - O(n * logN) for sorting

```java
import java.util.Arrays;

class Solution {

    public int maxFrequency(int[] nums, int k) {

        Arrays.sort(nums);

        long[] prefixSum = new long[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int l = 0;
        int result = 0;

        for (int r = 0; r < nums.length; r++) {
            while (((long) nums[r] * (r - l + 1)) - (prefixSum[r + 1] - prefixSum[l]) > k) {
                l += 1;
            }
            result = Math.max(result, r - l + 1);
        }

        return result;
    }
}
```
