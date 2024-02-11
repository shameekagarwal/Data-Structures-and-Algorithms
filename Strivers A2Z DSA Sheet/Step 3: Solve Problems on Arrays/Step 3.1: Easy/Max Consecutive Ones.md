# Max Consecutive Ones

- https://leetcode.com/problems/max-consecutive-ones/

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutiveOnes = 0;
        int currentCountOfConsecutiveOnes = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 1) currentCountOfConsecutiveOnes = 0;
            else currentCountOfConsecutiveOnes += 1;
            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, currentCountOfConsecutiveOnes);
        }
        return maxConsecutiveOnes;
    }
}
```
