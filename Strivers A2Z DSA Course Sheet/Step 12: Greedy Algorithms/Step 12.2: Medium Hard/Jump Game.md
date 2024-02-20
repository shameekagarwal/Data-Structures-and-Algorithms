# Jump Game

- https://leetcode.com/problems/jump-game/
- if current index is not reachable, return false
- update max reachable to `max(max, nums[i] + i)`

```java
class Solution {

    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;
    }
}
```
