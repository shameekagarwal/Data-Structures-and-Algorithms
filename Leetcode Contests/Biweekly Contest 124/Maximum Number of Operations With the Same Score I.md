# Maximum Number of Operations With the Same Score I

- https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-i/

```java
class Solution {

    public int maxOperations(int[] nums) {
        
        int n = nums.length;
        
        int sum = nums[0] + nums[1];
        
        for (int i = 2; i + 1 < n; i += 2) {
            if (nums[i] + nums[i + 1] != sum) return i / 2;
        }
        
        return n / 2;
    }
}
```
