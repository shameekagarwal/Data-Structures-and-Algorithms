# Minimum Operations to Exceed Threshold Value I

- https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-i/

```java
class Solution {

    public int minOperations(int[] nums, int k) {

        int ops = 0;

        for (int i : nums) {
            if (i < k) ops += 1;
        }

        return ops;
    }
}
```
