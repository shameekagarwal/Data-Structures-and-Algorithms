# Jump Game II

- https://leetcode.com/problems/jump-game-ii/
- think of it like bfs
- initially, our l and r are 0, 0
- we go from l to r, and we realize the maximum place we can reach
- then, l becomes r + 1 while r becomes the maximum point we could reach
- e.g. `[2,3,1,1,4]`
- initially, l = 0, r = 0
- after looking at all elements from l to r, we realize we can jump farthest to 2, so l = 1, r = 2
- after looking at all elements from l to r, we realize we can jump farthest to 4, so l = 3, r = 4
- since r has reached or exceeded the final length, we break out of the loop

```java
class Solution {

    public int jump(int[] nums) {

        int currentJumps = 0;
        int l = 0;
        int r = 0;
        
        while (r < nums.length - 1) {
            
            int maxPossibleDistance = r;
            for (int i = l; i <= r; i++) {
                maxPossibleDistance = Math.max(maxPossibleDistance, nums[i] + i);
            }
            l = r + 1;
            r = maxPossibleDistance;
            currentJumps += 1;
        }

        return currentJumps;
    }
}
```
