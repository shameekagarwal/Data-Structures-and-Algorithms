# Shortest Subarray With OR at Least K I

- https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-i/
- calculate for all subarrays

```java
class Solution {

    public int minimumSubarrayLength(int[] nums, int k) {

        int n = nums.length;
        int result = n + 1;

        for (int start = 0; start < n; start++) {
            
            int or = 0;

            for (int end = start; end < n; end++) {

                or |= nums[end];

                if (or >= k) result = Math.min(result, end - start + 1);
            }
        }

        return result == n + 1 ? -1 : result;
    }
}
```
